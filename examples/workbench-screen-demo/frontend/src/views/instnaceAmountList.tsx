import React from 'react';
import cn from 'classnames';
import moment from 'moment';
import { Loading } from '@alifd/next';
import { Bar } from '@antv/g2plot';
import type { InstanceAmount } from '../services/workbench';
import * as services from '../services';
import classes from '../styles/app.module.css';

export interface Props {
  projectId: number;
}

const InstanceAmountList: React.FunctionComponent<Props> = (props) => {
  const ref = React.useRef(null);
  const [visible, setVisible] = React.useState(false);
  const [data, setData] = React.useState<InstanceAmount[]>([]);
  const [chart, setChart] = React.useState<Bar>();
  const fetchData = async (signal: AbortSignal, projectId: number) => {
    try {
      setVisible(true);
      const beginDate = moment().subtract(7, 'days').format('yyyy-MM-DDTHH:mm:ssZZ');
      const endDate = moment().format('yyyy-MM-DDTHH:mm:ssZZ');
      const response = await services.workbench.getInstanceAmountList(signal, beginDate, endDate, projectId);
      chart?.changeData(response.map(i => ({ date: moment(i.date).format('YYYY-MM-DD'), count: i.count })));
      setData(response);
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
      const bar = new Bar(ref.current, {
        data,
        xField: 'count',
        yField: 'date',
        barBackground: {
          style: {
            fill: 'rgba(0,0,0,0.1)',
          },
        },
        height: 200,
        maxBarWidth: 10,
      });
      bar.render();
      setChart(bar);
      return () => {
        bar.destroy();
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

export default InstanceAmountList;
