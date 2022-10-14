import React from 'react';
import Graphin, { Behaviors } from '@antv/graphin';
import type { IUserNode, IUserEdge, GraphinData, GraphEvent } from '@antv/graphin';
import cn from 'classnames';
import * as services from '../services';
import type { TableItem, GetTableLineageOutput, LineageEntity } from '../services/meta';
import classes from '../styles/lineageContent.module.css';
import '@antv/graphin/dist/index.css';

export interface Props {
  item: TableItem;
}

function transNode(entity: LineageEntity | TableItem, direction?: string): IUserNode {
  return {
    id: entity.tableGuid,
    label: entity.tableName,
    data: {
      ...entity,
      direction,
    },
    style: {
      label: {
        value: entity.tableName,
      }
    },
  }
}
function transEdge(source: LineageEntity | TableItem, target: LineageEntity | TableItem): IUserEdge {
  return {
    source: source.tableGuid,
    target: target.tableGuid,
  };
}
function parse(
  source: LineageEntity | TableItem,
  data: GetTableLineageOutput,
  direction: string,
): [IUserNode[], IUserEdge[]] {
  const nodes: IUserNode[] = [];
  const edges: IUserEdge[] = [];
  data.dataEntityList.forEach((entity) => {
    nodes.push(transNode(entity, direction));
    if (direction === 'down') {
      edges.push(transEdge(source, entity));
    } else {
      edges.push(transEdge(entity, source));
    }
  });
  return [nodes, edges];
}
function mergeNodes(prev: IUserNode[], next: IUserNode[]) {
  const result: IUserNode[] = prev.slice();
  next.forEach((item) => {
    const hasValue = prev.findIndex(i => i.id === item.id) >= 0;
    !hasValue && result.push(item);
  });
  return result;
}
function mergeEdges(source: IUserEdge[], target: IUserEdge[]) {
  const result: IUserEdge[] = source.slice();
  target.forEach((item) => {
    const hasValue = source.findIndex(i => i.target === item.target && i.source === item.source) >= 0;
    !hasValue && result.push(item);
  });
  return result;
}

const { ActivateRelations, DragNode, ZoomCanvas } = Behaviors;
const LineageContent: React.FunctionComponent<Props> = (props) => {
  const ref = React.useRef<Graphin>();
  const [data, setData] = React.useState<GraphinData>({ nodes: [], edges: [] });
  const getTableLineage = async (
    collection: GraphinData,
    target: TableItem | LineageEntity,
    direction: string,
  ) => {
    console.log('source', direction);
    if (!direction) {
      return collection;
    }
    const response = await services.meta.getTableLineage({
      direction,
      tableGuid: target.tableGuid,
    });
    const [nodes, edges] = parse(target, response, direction);
    collection.nodes = mergeNodes(collection.nodes!, nodes);
    collection.edges = mergeEdges(collection.edges!, edges);
    return collection;
  };
  const init = async () => {
    let nextData = Object.assign({}, data, { nodes: [transNode(props.item)] });
    nextData = await getTableLineage(nextData, props.item, 'up');
    nextData = await getTableLineage(nextData, props.item, 'down');
    setData(nextData);
    ref.current!.graph.fitCenter();
  };
  React.useEffect(() => {
    ref.current?.graph && init();
  }, [
    ref.current?.graph,
  ]);
  React.useEffect(() => {
    const graph = ref.current?.graph;
    const event = async (event: GraphEvent) => {
      const source = event.item?.get('model').data;
      let nextData = Object.assign({}, data);
      nextData = await getTableLineage(nextData, source, source.direction);
      setData(nextData);
    };
    graph?.on('node:click', event);
    return () => {
      graph?.off('node:click', event);
    };
  }, [
    data,
    ref.current?.graph,
  ]);
  return (
    <div className={cn(classes.lineageContentWrapper)}>
      <Graphin
        data={data}
        ref={ref as React.LegacyRef<Graphin>}
        layout={{
          type: 'dagre',
          rankdir: 'LR',
          align: 'DL',
          nodesep: 10,
          ranksep: 40,
        }}
      >
        <ActivateRelations />
        <DragNode disabled />
        <ZoomCanvas enableOptimize />
      </Graphin>
    </div>
  );
};

export default LineageContent;
