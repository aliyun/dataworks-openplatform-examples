import React from 'react';
import moment from 'moment';
import { Form, Grid, Table, Pagination } from '@alifd/next';
import cn from 'classnames';
import * as services from '../services';
import type { TableItem, TableDetailOutput, TableColumn, TableEntity } from '../services/meta';
import classes from '../styles/detailContent.module.css';

export interface Props {
  item: TableItem;
}

const formItemLayout = {
  labelCol: {
    fixedSpan: 6
  },
  wrapperCol: {
    span: 18
  },
  labelTextAlign: 'left' as const,
  colon: true,
  className: cn(classes.formItemWrapper),
};
const { Row, Col } = Grid;
const { Item } = Form;
const { Column } = Table;
const DetailContent: React.FunctionComponent<Props> = (props) => {
  const [detail, setDetail] = React.useState<Partial<TableDetailOutput>>({});
  const [columns, setColumns] = React.useState<Partial<TableColumn[]>>([]);
  const [partitions, setPartitions] = React.useState<Partial<TableEntity[]>>([]);
  const [columnsTotal, setColumnsTotal] = React.useState<number>(0);
  const [partitionTotal, setPartitionTotal] = React.useState<number>(0);
  const getTableDetail = React.useCallback(async () => {
    const response = await services.meta.getTableDetail({ tableGuid: props.item.tableGuid });
    setDetail(response);
  }, [props.item.tableGuid]);
  const getTableColumns = React.useCallback(async (pageNum: number = 1) => {
    const response = await services.meta.getMetaTableColumns({
      pageNum,
      tableGuid: props.item.tableGuid,
    });
    setColumns(response.columnList);
    setColumnsTotal(response.totalCount);
  }, [props.item.tableGuid]);
  const getTablePartition = React.useCallback(async (pageNumber: number = 1) => {
    const response = await services.meta.getTablePartition({
      pageNumber,
      tableGuid: props.item.tableGuid,
    });
    setPartitions(response.dataEntityList);
    setPartitionTotal(response.totalCount);
  }, []);
  const isVisible = React.useMemo(() => {
    switch (detail.isVisible) {
      case 0:
        return '对工作空间成员可见';
      case 1:
        return '对租户内成员可见';
      case 2:
        return '对租户间成员均可见';
      case 3:
        return '仅对责任人可见';
      default:
        return '';
    }
  }, [detail.isVisible]);
  React.useEffect(() => {
    if (props.item.tableGuid) {
      getTableDetail();
      getTableColumns();
      getTablePartition();
    }
  }, [props.item.tableGuid]);
  return (
    <div className={cn(classes.detailContentWrapper)}>
      <Form labelTextAlign="left">
        <Row>
          <Col>
            <Item {...formItemLayout} label="表GUID">
              <span className={cn(classes.formContentWrapper)}>{detail.tableGuid}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="表名称">
              <span className={cn(classes.formContentWrapper)}>{detail.tableName}</span>
            </Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Item {...formItemLayout} label="所属工作空间">
              <span className={cn(classes.formContentWrapper)}>{detail.projectName}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="表所有者ID">
              <span className={cn(classes.formContentWrapper)}>{detail.ownerId}</span>
            </Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Item {...formItemLayout} label="表生命周期">
              <span className={cn(classes.formContentWrapper)}>{detail.lifeCycle ? `${detail.lifeCycle} 天` : '永久'}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="备注信息">
              <span className={cn(classes.formContentWrapper)}>{detail.comment}</span>
            </Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Item {...formItemLayout} label="存储空间">
              <span className={cn(classes.formContentWrapper)}>{`${detail.dataSize} Bytes`}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="是否为分区表">
              <span className={cn(classes.formContentWrapper)}>{detail.isView ? '是' : '否'}</span>
            </Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Item {...formItemLayout} label="表的可见性">
              <span className={cn(classes.formContentWrapper)}>{isVisible}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="表的读取次数">
              <span className={cn(classes.formContentWrapper)}>{detail.readCount}</span>
            </Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Item {...formItemLayout} label="创建时间">
              <span className={cn(classes.formContentWrapper)}>{moment(detail.createTime).format('YYYY-MM-DD HH:mm:ss')}</span>
            </Item>
          </Col>
          <Col>
            <Item {...formItemLayout} label="最后修改时间">
              <span className={cn(classes.formContentWrapper)}>{moment(detail.lastModifyTime).format('YYYY-MM-DD HH:mm:ss')}</span>
            </Item>
          </Col>
        </Row>
        <Item label="字段详情" colon>
          <Table dataSource={columns}>
            <Column title="名称" dataIndex="columnName" />
            <Column title="类型" dataIndex="columnType" />
            <Column title="是否为主键" dataIndex="isPrimaryKey" />
            <Column title="是否为外键" dataIndex="isForeignKey" />
            <Column title="是否为分区" dataIndex="isPartitionColumn" />
            <Column title="说明" dataIndex="comment" />
          </Table>
          <Pagination
            total={columnsTotal}
            onChange={getTableColumns}
            showJump={false}
            className={cn(classes.tablePaginationWrapper)}
          />
        </Item>
        <Item label="分区详情" style={{ marginTop: 32, marginBottom: 32 }} colon>
         <Table
          dataSource={partitions}
          emptyContent={<span>非分区表</span>}
        >
            <Column title="名称" dataIndex="partitionName" />
            <Column title="类型" dataIndex="partitionType" />
            <Column title="分区大小" dataIndex="dataSize" cell={value => `${value} bytes`} />
            <Column title="备注" dataIndex="comment" />
          </Table>
          <Pagination
            total={partitionTotal}
            onChange={getTablePartition}
            showJump={false}
            className={cn(classes.tablePaginationWrapper)}
          />
        </Item>
      </Form>
    </div>
  );
}

export default DetailContent;
