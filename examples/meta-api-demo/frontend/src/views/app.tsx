import React from 'react';
import cn from 'classnames';
import { Table, Form, Field, Input, Button, Pagination, Dialog, Card, Message } from '@alifd/next';
import type { TableListInput, TableItem } from '../services/meta';
import * as services from '../services';
import DetailContent from './detailContent';
import LineageContent from './lineageContent';
import CreateTableDialog from './createTableDialog';
import classes from '../styles/app.module.css';

export interface Props {}

const { Column } = Table;
const { Item } = Form;
const { Header, Content } = Card;
const App: React.FunctionComponent<Props> = () => {
  const field = Field.useField();
  const [datasource, setDatasource] = React.useState<TableItem[]>([]);
  const [loading, setLoading] = React.useState<boolean>(false);
  const [total, setTotal] = React.useState<number>(0);
  const [current, setCurrent] = React.useState<number>(1);
  const [createTableDialogVisible, setCreateTableDialogVisible] = React.useState<boolean>(false);
  const onSubmit = React.useCallback((pageNumber: number = 1) => {
    field.validate(async (errors, values) => {
      if (errors) {
        return;
      }
      setLoading(true);
      try {
        const response = await services.meta.getTableList({
          pageNumber,
          ...values,
        } as TableListInput);
        setDatasource(response.tableEntityList);
        setCurrent(pageNumber);
        setTotal(response.totalCount);
      } catch (e) {
        throw e;
      } finally {
        setLoading(false);
      }
    });
  }, [field]);
  const onViewDetail = React.useCallback((record: TableItem) => {
    Dialog.show({
      title: '表详情',
      content: <DetailContent item={record} />,
      shouldUpdatePosition: true,
      footerActions: ['ok'],
    });
  }, []);
  const onViewLineage = React.useCallback((record: TableItem) => {
    Dialog.show({
      title: '表血缘关系',
      content: <LineageContent item={record} />,
      shouldUpdatePosition: true,
      footerActions: ['ok'],
    });
  }, []);
  React.useEffect(() => {
    field.setValue('dataSourceType', 'odps');
  }, []);
  return (
    <div className={cn(classes.appWrapper)}>
      <Card free hasBorder={false}>
        <Header title="元数据表管理场景 Demo" />
        <Content style={{ marginTop: 24 }}>
          <Form field={field} colon fullWidth>
            <Item
              label="MaxCompute项目ID"
              name="appGuid"
              required
            >
              <Input />
            </Item>
            <Item
              label="数据库名称"
              name="databaseName"
            >
              <Input />
            </Item>
            <div className={cn(classes.searchPanelButtonWrapper, classes.buttonGroup)}>
              <Button type="primary" onClick={() => onSubmit()}>
                查询
              </Button>
              <Button onClick={() => {
                field.validate((errors) => {
                  if (errors) {
                    return;
                  }
                  setCreateTableDialogVisible(true);
                })
              }}>
                创建表
              </Button>
            </div>
          </Form>
          <div>
            <Table
              dataSource={datasource}
              loading={loading}
              className={cn(classes.tableWrapper)}
              emptyContent={(
                <div className={cn(classes.noDataWrapper)}>暂无数据</div>
              )}
            >
              <Column title="数据库名称" dataIndex="databaseName" />
              <Column title="表GUID" dataIndex="tableGuid" />
              <Column title="表名称" dataIndex="tableName" />
              <Column title="操作" width={150} cell={(value, index, record) => (
                <div className={cn(classes.buttonGroup)}>
                  <Button
                    type="primary"
                    onClick={() => onViewDetail(record)}
                    text
                  >
                    查看详情
                  </Button>
                  <Button
                    type="primary"
                    onClick={() => onViewLineage(record)}
                    text
                  >
                    查看表血缘
                  </Button>
                </div>
              )} />
            </Table>
            <Pagination
              current={current}
              total={total}
              onChange={onSubmit}
              showJump={false}
              className={cn(classes.tablePaginationWrapper)}
            />
          </div>
        </Content>
      </Card>
      <CreateTableDialog
        appGuid={field.getValue('appGuid')}
        onOk={() => {
          setCreateTableDialogVisible(false);
          Message.success('创建成功');
          onSubmit();
        }}
        onClose={() => setCreateTableDialogVisible(false)}
        onCancel={() => setCreateTableDialogVisible(false)}
        visible={createTableDialogVisible}
      />
    </div>
  );
};

export default App;
