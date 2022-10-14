import React from 'react';
import cn from 'classnames';
import { Loading } from '@alifd/next';
import { Gauge } from '@antv/g2plot';
import * as helpers from '../helpers';
import * as services from '../services';
import classes from '../styles/app.module.css';

export interface Props {}

const ticks = [0, 1 / 2, 9 / 10, 1];
const color = ['#F4664A', '#FAAD14', '#30BF78'];
const RealtimeTaskMonitor: React.FunctionComponent<Props> = () => {
  const ref = React.useRef<HTMLDivElement>(null);
  const [percent, setPercent] = React.useState<number | undefined>(undefined);
  const [visible, setVisible] = React.useState(true);
  const [chart, setChart] = React.useState<Gauge>();
  const fetchData = async () => {
    try {
      percent !== undefined && await helpers.pause(5000);
      const response = await services.workbench.getRealtimeNodeChanges();
      let result = response.instanceSuccessCount / (response.instanceSuccessCount + response.instanceFailureCount);
      result = Number(result.toFixed(2));
      setPercent(result);
      chart?.changeData(result);
    } catch (e) {
      console.error(e);
    } finally {
      visible && setVisible(false);
    }
  };
  React.useEffect(() => {
    fetchData();
  }, [
    percent,
    chart,
  ]);
  React.useEffect(() => {
    if (ref.current) {
      const gauge = new Gauge(ref.current, {
        percent: percent || 0.5,
        range: {
          ticks,
          color: ['#F4664A', '#FAAD14', '#30BF78'],
        },
        padding: 'auto',
        indicator: {
          pointer: {
            style: {
              stroke: '#D0D0D0',
            },
          },
          pin: {
            style: {
              stroke: '#D0D0D0',
            },
          },
        },
        statistic: {
          title: {
            formatter: (datum) => {
              const nextPercent = datum?.percent;
              if (nextPercent < ticks[1]) {
                return `差（${Math.round(nextPercent * 100)}%）`;
              }
              if (nextPercent < ticks[2]) {
                return `中（${Math.round(nextPercent * 100)}%）`;
              }
              return `优（${Math.round(nextPercent * 100)}%）`;
            },
            style: ({ percent }) => {
              return {
                fontSize: '36px',
                lineHeight: 1,
                color: percent < ticks[1] ? color[0] : percent < ticks[2] ? color[1] : color[2],
              };
            },
          },
          content: {
            offsetY: 36,
            style: {
              fontSize: '24px',
              color: '#4B535E',
            },
            formatter: () => '运行成功率',
          },
        },
      });
      setChart(gauge);
      gauge.render();
      return () => {
        gauge.destroy();
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

export default RealtimeTaskMonitor;
