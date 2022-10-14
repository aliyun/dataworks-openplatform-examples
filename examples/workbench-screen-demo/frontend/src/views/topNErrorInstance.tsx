import React from 'react';
import { Table } from '@alifd/next';
import * as services from '../services';
import type { ErrorInstance } from '../services/workbench';

export interface Props {
  projectId: number;
}

const { Column } = Table;
const TopNErrorInstance: React.FunctionComponent<Props> = (props) => {
  const [visible, setVisible] = React.useState(false);
  const [dataSource, setDataSource] = React.useState<ErrorInstance[]>([]);
  const fetchData = async (signal: AbortSignal, projectId: number) => {
    try {
      setVisible(true);
      const response = await services.workbench.getTopNErrorInstance(signal, projectId);
      setDataSource(response.errorRank);
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
      <Column dataIndex="owner" title="负责人" />
      <Column
        dataIndex="programType"
        title="任务类型"
        cell={(value: number) => {
          switch (value) {
            case 6:
              return 'Shell';
            case 10:
              return 'ODPS SQL';
            case 11:
              return 'ODPS MR';
            case 23:
              return '数据集成';
            case 24:
              return 'ODPS Script';
            case 99:
              return '虚拟节点';
            case 221:
              return 'PyODPS 2';
            case 225:
              return 'ODPS Spark';
            case 227:
              return 'EMR Hive';
            case 228:
              return 'EMR Spark';
            case 229:
              return 'EMR Spark SQL';
            case 230:
              return 'EMR MR';
            case 239:
              return 'OSS对象检查';
            case 257:
              return 'EMR Shell';
            case 258:
              return 'EMR Spark Shell';
            case 259:
              return 'EMR Presto';
            case 260:
              return 'EMR Impala';
            case 900:
              return '实时同步';
            case 1089:
              return '跨租户节点';
            case 1091:
              return 'Hologres开发';
            case 1093:
              return 'Hologres SQL';
            case 1100:
              return '赋值节点';
            case 1221:
              return 'PyODPS 3';
          }
        }}
      />
      <Column dataIndex="count" title="出错次数" />
    </Table>
  );
};

export default TopNErrorInstance;
