import React from 'react';
import { Loading } from '@alifd/next';
import { Pie } from '@antv/g2plot';
import cn from 'classnames';
import * as services from '../services';
import classes from '../styles/app.module.css';

export interface Props {
  projectId: number;
}
export interface Point {
  type: string;
  value: number;
}

const InstanceStatus: React.FunctionComponent<Props> = (props) => {
  const ref = React.useRef(null);
  const [visible, setVisible] = React.useState(false);
  const [data, setData] = React.useState<Point[]>([]);
  const [chart, setChart] = React.useState<Pie>();
  const fetchData = async (signal: AbortSignal) => {
    try {
      setVisible(true);
      const nextData: Point[] = [];
      const response = await services.workbench.getInstanceStatus(signal, props.projectId);
      nextData.push({ type: '未运行', value: response.notRunCount });
      nextData.push({ type: '等待中', value: response.waitTimeCount + response.waitResCount });
      nextData.push({ type: '运行中', value: response.runningCount });
      nextData.push({ type: '运行成功', value: response.successCount });
      nextData.push({ type: '运行失败', value: response.failureCount });
      chart?.changeData(nextData);
      setData(nextData);
    } catch (e) {
      console.error(e);
    } finally {
      setVisible(false);
    }
  };
  React.useEffect(() => {
    const controller = new AbortController();
    props.projectId && fetchData(controller.signal);
    return () => {
      controller.abort();
    };
  }, [
    props.projectId,
  ]);
  React.useEffect(() => {
    if (ref.current) {
      const pie = new Pie(ref.current, {
        data,
        angleField: 'value',
        colorField: 'type',
        innerRadius: 0.6,
        label: {
          type: 'inner',
          offset: '-50%',
          content: '{value}',
          style: {
            textAlign: 'center',
            fontSize: 18,
          },
        },
        statistic: {
          title: false,
        },
      });
      setChart(pie);
      pie.render();
      return () => {
        pie.destroy();
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

export default InstanceStatus;
