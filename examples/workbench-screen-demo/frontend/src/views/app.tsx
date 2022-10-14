import React from 'react';
import { Card, Grid, Form, Field, Input } from '@alifd/next';
import cn from 'classnames';
import RealtimeTaskMonitor from './realtimeTaskMonitor';
import RealtimeNodeMonitor from './realtimeNodeMonitor';
import TopNInstanceElapsedTime from './topNInstanceElapsedTime';
import InstanceAmountList from './instnaceAmountList';
import SuccessInstanceAmountList from './successInstanceAmountList';
import TopNErrorInstance from './topNErrorInstance';
import InstanceStatus from './instanceStatus';
import TaskStatus from './taskStatus';
import classes from '../styles/app.module.css';

export interface Props {}

const { Row, Col } = Grid;
const { Header, Content, Divider } = Card;
const { Item } = Form;
const App: React.FunctionComponent<Props> = () => {
  const field = Field.useField();
  const projectId = Number(field.getValue('projectId'));
  return (
    <div className={cn(classes.appWrapper)}>
      <Card className={cn(classes.appCardTitle)} free hasBorder={false}>
        <Header title="运维大屏场景 Demo" />
        <Content style={{ marginTop: 24, marginBottom: 24 }}>
          <Row>
            <Form field={field} colon inline>
              <Item name="projectId" label="项目空间ID">
                <Input />
              </Item>
            </Form>
          </Row>
          <Row>
            <Col span={12}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="实时任务运行监控" />
                <Divider />
                <Content>
                  <RealtimeTaskMonitor />
                </Content>
              </Card>
            </Col>
            <Col offset={1} span={11}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="实时节点状态监控" />
                <Divider />
                <Content>
                  <RealtimeNodeMonitor />
                </Content>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="一周调度任务数量趋势" />
                <Divider />
                <Content>
                  <InstanceAmountList projectId={projectId} />
                </Content>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="任务完成情况" />
                <Divider />
                <Content>
                  <SuccessInstanceAmountList projectId={projectId} />
                </Content>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="近一个月运行时长排行" />
                <Divider />
                <Content>
                  <TopNInstanceElapsedTime projectId={projectId} />
                </Content>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="近一个月任务出错排行" />
                <Divider />
                <Content>
                  <TopNErrorInstance projectId={projectId} />
                </Content>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col span={11}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="昨日任务类型分布" />
                <Divider />
                <Content>
                  <TaskStatus projectId={projectId} />
                </Content>
              </Card>
            </Col>
            <Col offset={2} span={11}>
              <Card free>
                <Header className={cn(classes.appCardTitle)} title="昨日运行状态分布" />
                <Divider />
                <Content>
                  <InstanceStatus projectId={projectId} />
                </Content>
              </Card>
            </Col>
          </Row>
        </Content>
      </Card>
    </div>
  );
};

export default App;
