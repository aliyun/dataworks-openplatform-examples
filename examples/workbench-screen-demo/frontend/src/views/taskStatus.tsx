import React from 'react';
import { Loading } from '@alifd/next';
import { Pie } from '@antv/g2plot';
import cn from 'classnames';
import * as services from '../services';
import type { FileStatus } from '../services/workbench';
import classes from '../styles/app.module.css';

export interface Props {
  projectId: number;
}

const TaskStatus: React.FunctionComponent<Props> = (props) => {
  const ref = React.useRef(null);
  const [visible, setVisible] = React.useState(false);
  const [data, setData] = React.useState<FileStatus[]>([]);
  const [chart, setChart] = React.useState<Pie>();
  const fetchData = async (signal: AbortSignal, projectId: number) => {
    try {
      setVisible(true);
      const response = await services.workbench.getFileTypeStatistic(signal, projectId);
      chart?.changeData(response);
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
      const pie = new Pie(ref.current, {
        data,
        angleField: 'count',
        colorField: 'programType',
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

export default TaskStatus;
