import React from 'react';
import { Form, Input, Field, NumberPicker, Radio, Dialog, Transfer, Message } from '@alifd/next';
import type { DialogProps } from '@alifd/next/types/dialog';
import type { CreateTableInput } from '../services/meta';
import * as services from '../services';

export interface Props extends DialogProps {
  appGuid: string;
}

const formItemLayout = {
  labelCol: {
    fixedSpan: 6
  },
  wrapperCol: {
    span: 18
  },
};
const columnsDataSource = [
  { label: '性别', value: 'sex', type: 'string', comment: '性别' },
  { label: '年龄', value: 'age', type: 'bigint', comment: '年龄' },
  { label: '星座', value: 'constellation', type: 'string', comment: '星座' },
  { label: '职业', value: 'job', type: 'string', comment: '职业' },
  { label: '是否为学生', value: 'isStudent', type: 'boolean', comment: '是否为学生' },
];
const { TextArea } = Input;
const { Item } = Form;
const { Group } = Radio;
const CreateTableDialog: React.FunctionComponent<Props> = (props) => {
  const [loading, setLoading] = React.useState<boolean>(false);
  const field = Field.useField();
  const createTable = (event: React.MouseEvent<Element, MouseEvent>) => {
    field.validate(async (errors, values) => {
      if (errors) {
        return;
      }
      try {
        setLoading(true);
        const columns = (values as any).columns.map((column: any) => {
          const result = columnsDataSource.find(i => i.value === column);
          return {
            columnNameCn: result!.label,
            columnName: result!.value,
            comment: result!.comment,
            columnType: result!.type,
          };
        });
        const nextValues = Object.assign({}, values, { columns });
        const response = await services.meta.createTable(nextValues as CreateTableInput);
        response ? props.onOk?.(event) : Message.error('创建失败');
      } catch (e) {
        throw e;
      } finally {
        setLoading(false);
      }
    });
  };
  React.useEffect(() => {
    field.setValue('appGuid', props.appGuid);
  }, [
    props.appGuid,
  ]);
  return (
    <Dialog
      title="创建表"
      visible={props.visible}
      onOk={createTable}
      onCancel={props.onCancel}
      onClose={props.onClose}
      okProps={{
        loading,
        disabled: loading,
      }}
      cancelProps={{
        disabled: loading,
      }}
      width={614}
      v2
    >
      <Form {...formItemLayout} field={field} labelTextAlign="left" colon>
        <Item name="tableName" label="表名称" required>
          <Input disabled={loading} />
        </Item>
        <Item name="lifeCycle" label="生命周期">
          <NumberPicker style={{ width: '100%' }} disabled={loading} />
        </Item>
        <Item name="envType" label="环境">
          <Group disabled={loading} defaultValue={1}>
            <Radio label="开发" value={0} />
            <Radio label="生产" value={1} />
          </Group>
        </Item>
        <Item name="comment" label="备注">
          <TextArea disabled={loading} />
        </Item>
        <Item name="columns" label="表字段">
          <Transfer
            mode="simple"
            dataSource={columnsDataSource}
          />
        </Item>
      </Form>
    </Dialog>
  );
};

export default CreateTableDialog;
