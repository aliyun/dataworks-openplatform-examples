import React from 'react';
import cn from 'classnames';
import { Loading } from '@alifd/next';
import { Line } from '@antv/g2plot';
import * as services from '../services';
import type { SuccessInstanceAmount } from '../services/workbench';
import classes from '../styles/app.module.css';

export interface Props {
  projectId: number;
}
export interface Point extends SuccessInstanceAmount {
  type?: string;
}

const SuccessInstanceAmountList: React.FunctionComponent<Props> = (props) => {
  const ref = React.useRef(null);
  const [visible, setVisible] = React.useState(false);
  const [data, setData] = React.useState<Point[]>([]);
  const [chart, setChart] = React.useState<Line>();
  const fetchData = async (signal: AbortSignal, projectId: number) => {
    try {
      setVisible(true);
      const response = await services.workbench.getSuccessInstanceAmountList(signal, projectId);
      let collection: Point[] = [];
      collection = collection.concat(response.avgTrend.map(i => ({ ...i, type: '平均值' })));
      collection = collection.concat(response.todayTrend.map(i => ({ ...i, type: '今日' })));
      collection = collection.concat(response.yesterdayTrend.map(i => ({ ...i, type: '昨日' })));
      const max = Math.max(...collection.map(i => i.count)) + 1;
      chart?.changeData(collection);
      chart?.update({ yAxis: { max } });
      setData(collection);
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
  React.useEffect(() => {
    if (ref.current) {
      const line = new Line(ref.current, {
        data,
        xField: 'timePoint',
        yField: 'count',
        seriesField: 'type',
        height: 300,
        yAxis: {
          tickInterval: 1,
        },
      });
      setChart(line);
      line.render();
      return () => {
        line.destroy();
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

export default SuccessInstanceAmountList;
