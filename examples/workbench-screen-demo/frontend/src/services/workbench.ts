import moment from 'moment';
import * as helpers from '../helpers';

export const HOST = 'http://localhost:8008';
export const META_SERVICE = `${HOST}/screen`;
export const METHOD_URL = {
  GET_REALTIME_NODE_CHANGES: `${META_SERVICE}/getRealTimeNodeChanges`,
  GET_TOP_N_INSTANCE_ELAPSED_TIME: `${META_SERVICE}/topTenElapsedTimeInstance`,
  GET_INSTANCE_AMOUNT_LIST: `${META_SERVICE}/listInstanceAmount`,
  GET_SUCCESS_INSTANCE_AMOUNT_LIST: `${META_SERVICE}/listSuccessInstanceAmount`,
  GET_TOP_N_ERROR_INSTANCE: `${META_SERVICE}/topTenErrorTimesInstance`,
  GET_INSTANCE_STATUS: `${META_SERVICE}/getInstanceStatusStatistic`,
  GET_FILE_TYPE_STATISTIC: `${META_SERVICE}/getFileTypeStatistic`,
};

export interface FileStatus {
  count: number;
  programType: string;
}
export interface ErrorInstance {
  count: number;
  nodeId: number;
  nodeName: string;
  owner: string;
  programType: number;
  projectId: number;
}
export interface SuccessInstanceAmount {
  timePoint: string;
  count: number;
}
export interface InstanceAmount {
  count: number;
  date: number;
}
export interface ConsumeTimeRank {
  businessDate: number;
  consumed: number;
  instanceId: number;
  nodeId: number;
  nodeName: string;
  owner: string;
  programType: number;
}
export interface GetRealtimeNodeChangesOutput {
  nodeChangeCreated: number;
  nodeChangeUpdated: number;
  nodeChangeDeleted: number;
  instanceWaitResourceCount: number;
  instanceFailureCount: number;
  instanceWaitTimeCount: number;
  instanceRunningCount: number;
  instanceSuccessCount: number;
}
export interface GetTopNInstanceElapsedTimeOutput {
  consumeTimeRank: ConsumeTimeRank[];
}
export interface GetSuccessInstanceAmountListOutput {
  avgTrend: SuccessInstanceAmount[];
  todayTrend: SuccessInstanceAmount[];
  yesterdayTrend: SuccessInstanceAmount[];
}
export interface GetTopNErrorInstanceOutput {
  errorRank: ErrorInstance[];
  updateTime: number;
}
export interface GetInstanceStatusOutput {
  failureCount: number;
  notRunCount: number;
  runningCount: number;
  successCount: number;
  totalCount: number;
  waitResCount: number;
  waitTimeCount: number;
}

export async function getRealtimeNodeChanges(): Promise<GetRealtimeNodeChangesOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_REALTIME_NODE_CHANGES);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function getTopNInstanceElapsedTime(signal: AbortSignal, projectId: number): Promise<GetTopNInstanceElapsedTimeOutput> {
  const  url = helpers.createFetchUrl(METHOD_URL.GET_TOP_N_INSTANCE_ELAPSED_TIME, {
    projectId,
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
export async function getInstanceAmountList(signal: AbortSignal, beginDate: string, endDate: string, projectId: number): Promise<InstanceAmount[]> {
  const  url = helpers.createFetchUrl(METHOD_URL.GET_INSTANCE_AMOUNT_LIST, {
    beginDate,
    endDate,
    projectId,
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
export async function getSuccessInstanceAmountList(signal: AbortSignal, projectId: number): Promise<GetSuccessInstanceAmountListOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_SUCCESS_INSTANCE_AMOUNT_LIST, {
    projectId,
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
export async function getTopNErrorInstance(signal: AbortSignal, projectId: number): Promise<GetTopNErrorInstanceOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TOP_N_ERROR_INSTANCE, {
    projectId,
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
export async function getInstanceStatus(signal: AbortSignal, projectId: number): Promise<GetInstanceStatusOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_INSTANCE_STATUS, {
    projectId,
    projectEnv: 'PROD',
    bizDate: moment().subtract(1, 'days').format('YYYY-MM-DD'),
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
export async function getFileTypeStatistic(signal: AbortSignal, projectId: number): Promise<FileStatus[]> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_FILE_TYPE_STATISTIC, {
    projectId,
  });
  const response = await fetch(url, { signal });
  const result = await response.json();
  return result;
}
