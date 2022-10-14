import React from 'react';
import cn from 'classnames';
import moment from 'moment';
import { Loading } from '@alifd/next';
import { Area } from '@antv/g2plot';
import * as helpers from '../helpers';
import * as services from '../services';
import classes from '../styles/app.module.css';

export interface Props {}
export interface Point {
  time: string;
  value: number;
  type: string;
}

const RealtimeNodeMonitor: React.FunctionComponent<Props> = () => {
  const ref = React.useRef(null);
  const [visible, setVisible] = React.useState(true);
  const [data, setData] = React.useState<Point[]>([]);
  const [chart, setChart] = React.useState<Area>();
  const fetchData = async () => {
    try {
      data.length > 0 && await helpers.pause(5000);
      const response = await services.workbench.getRealtimeNodeChanges();
      const time = moment().format('HH:mm:ss');
      const nextData = data.concat([
        { time, value: response.nodeChangeCreated, type: '已创建' },
        { time, value: response.nodeChangeUpdated, type: '已更新' },
        { time, value: response.nodeChangeDeleted, type: '已删除' },
      ]);
      setData(nextData);
      chart?.changeData(nextData);
    } catch (e) {
      console.error(e);
    } finally {
      visible && setVisible(false);
    }
  };
  React.useEffect(() => {
    fetchData();
  }, [
    data,
    chart,
  ]);
  React.useEffect(() => {
    if (ref.current) {
      const area = new Area(ref.current, {
        data,
        padding: 'auto',
        xField: 'time',
        yField: 'value',
        seriesField: 'type',
      });
      setChart(area);
      area.render();
      return () => {
        area.destroy();
      };
    }
  }, [
    ref.current,
  ]);
  return (
    <Loading className={cn(classes.appLoading)} visible={visible}>
      <div ref={ref} />
    </Loading>
  );
};

export default RealtimeNodeMonitor;
