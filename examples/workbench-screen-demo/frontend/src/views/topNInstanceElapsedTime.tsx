import React from 'react';
import moment from 'moment';
import { Table } from '@alifd/next';
import * as services from '../services';
import type { ConsumeTimeRank } from '../services/workbench';

export interface Props {
  projectId: number;
}

const { Column } = Table;
const TopNInstanceElapsedTime: React.FunctionComponent<Props> = (props) => {
  const [visible, setVisible] = React.useState(false);
  const [dataSource, setDataSource] = React.useState<ConsumeTimeRank[]>([]);
  const fetchData = async (signal: AbortSignal, projectId: number) => {
    try {
      setVisible(true);
      const response = await services.workbench.getTopNInstanceElapsedTime(signal, projectId);
      setDataSource(response.consumeTimeRank);
    } catch (e) {
      console.error(e);
    } finally {
      setVisible(false);
    }
  };
  React.useEffect(() => {
    const controller = new AbortController();
    props.projectId && fetchData(controller.signal, props.projectId);
    return () => {
      controller.abort();
    };
  }, [
    props.projectId,
  ]);
  return (
    <Table
      loading={visible}
      dataSource={dataSource}
      maxBodyHeight={400}
      fixedHeader
    >
      <Column dataIndex="nodeId" title="节点ID" />
      <Column dataIndex="nodeName" title="节点名称" />
      <Column dataIndex="instanceId" title="实例ID" />
      <Column dataIndex="owner" title="负责人" />
      <Column dataIndex="businessDate" cell={value => moment(value).format('YYYY-MM-DD')} title="业务日期" />
      <Column dataIndex="consumed" title="运行时长" />
    </Table>
  );
};

export default TopNInstanceElapsedTime;
