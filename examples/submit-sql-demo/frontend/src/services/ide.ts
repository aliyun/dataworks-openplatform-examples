import moment from 'moment';
import * as helpers from '../helpers';

export const HOST = 'http://localhost:8008';
export const IDE_SERVICE = `${HOST}/ide`;
export const METHOD_URL = {
  GET_FILE_LIST: `${IDE_SERVICE}/listFiles`,
  GET_FILE: `${IDE_SERVICE}/getFile`,
  CREATE_FILE: `${IDE_SERVICE}/createFile`,
  UPDATE_FILE: `${IDE_SERVICE}/updateFile`,
  DEPLOY_FILE: `${IDE_SERVICE}/deployFile`,
  GET_BUSINESS_LIST: `${IDE_SERVICE}/listBusinesses`,
  CREATE_BUSINESS: `${IDE_SERVICE}/createBusiness`,
  RUN_SMOKE: `${IDE_SERVICE}/runSmokeTest`,
  GET_LOG: `${IDE_SERVICE}/getLog`,
};

export interface Business {
  owner: string;
  description: string;
  projectId: number;
  businessId: number;
  businessName: string;
  useType: string;
}
export interface File {
  commitStatus: number;
  autoParsing: boolean;
  owner: string;
  createTime: number;
  fileType: number;
  currentVersion: number;
  bizId: number;
  lastEditUser: string;
  fileName: string;
  connectionName: string;
  useType: string;
  fileFolderId: string;
  fileId: number;
  parentId: number;
  createUser: string;
  isMaxCompute: boolean;
  businessId: number;
  fileDescription: string;
  lastEditTime: number;
  content: string;
  nodeId: number;
}
export interface Dag {
  baselineId: number;
  beginRunningTime: number;
  beginWaitResTime: number;
  beginWaitTimeTime: number;
  bizdate: number;
  businessId: number;
  onnection: string;
  createTime: number;
  createUser: string;
  cycTime: number;
  dagId: number;
  dagType: string;
  dqcDescription: string;
  dqcType: string;
  errorMessage: string;
  finishTime: number;
  instanceId: number;
  modifyTime: number;
  nodeId: number;
  nodeName: string;
  paramValues: string;
  priority: number;
  relatedFlowId: number;
  repeatInterval: number;
  repeatability: boolean;
  status: string;
  taskRerunTime: number;
  taskType: string;
}
export interface Instance {
  status: string;
	cycTime: number;
	beginRunningTime: number;
	finishTime: number;
	errorMessage: string;
	createTime: number;
	dagId: number;
	priority: number;
	taskType: string;
  paramValues: string;
  connection: string;
  baselineId: number;
  dqcType: number;
  dagType: string;
  businessId: number;
  taskRerunTime: number;
  modifyTime: number;
  repeatability: boolean;
  repeatInterval: number;
  instanceId: number;
  beginWaitResTime: number;
  relatedFlowId: number;
  bizdate: number;
  nodeName: string;
  beginWaitTimeTime: number;
  dqcDescription: string;
  nodeId: number;
  createUser: string;
}
export interface Log {
  instance: Instance;
  instanceLog: string;
}

export async function getBusinessList(projectId: number, keyword: string): Promise<Business[]> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_BUSINESS_LIST, {
    pageNumber: 1,
    pageSize: 1,
    projectId,
    keyword,
  });
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function createBusiness(projectId: number, businessName: string): Promise<number> {
  const url = helpers.createFetchUrl(METHOD_URL.CREATE_BUSINESS);
  const headers = new Headers();
  headers.append('content-type', 'application/json');
  const response = await fetch(url, {
    method: 'POST',
    headers,
    body: JSON.stringify({
      projectId,
      businessName,
    }),
  });
  const result = await response.json();
  return result;
}
export async function getFileList(projectId: number, fileFolderPath: string): Promise<File[]> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_FILE_LIST, {
    projectId,
    fileFolderPath,
    pageNumber: 1,
    pageSize: 100,
  });
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function createFile(projectId: number, projectName: string, fileFolderPath: string, fileName: string, content: string): Promise<number> {
  const url = helpers.createFetchUrl(METHOD_URL.CREATE_FILE);
  const headers = new Headers();
  headers.append('content-type', 'application/json');
  const response = await fetch(url, {
    headers,
    method: 'POST',
    body: JSON.stringify({
      connectionName: 'odps_first',
      projectId,
      fileFolderPath,
      fileName,
      content,
      fileType: 10,
      inputList: `${projectName}_root`,
    }),
  });
  const result = await response.json();
  return result;
}
export async function getFile(projectId: number, fileId: number): Promise<File> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_FILE, {
    projectId,
    fileId,
  });
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function updateFile(workspace: number, fileId: number, payload: Partial<File> = {}): Promise<boolean> {
  const url = helpers.createFetchUrl(METHOD_URL.UPDATE_FILE);
  const headers = new Headers();
  headers.append('content-type', 'application/json');
  const response = await fetch(url, {
    headers,
    method: 'POST',
    body: JSON.stringify({
      ...payload,
      fileId,
      projectId: workspace,
    }),
  });
  const result = await response.json();
  return result;
}
export async function deployFile(projectId: number, fileId: number): Promise<boolean> {
  const url = helpers.createFetchUrl(METHOD_URL.DEPLOY_FILE);
  const headers = new Headers();
  headers.append('content-type', 'application/json');
  const response = await fetch(url, {
    headers,
    method: 'POST',
    body: JSON.stringify({
      projectId,
      fileId,
    }),
  });
  const result = await response.json();
  return result;
}
export async function runSmoke(projectId: number, nodeId: number, businessName: string): Promise<Dag[]> {
  const url = helpers.createFetchUrl(METHOD_URL.RUN_SMOKE);
  const headers = new Headers();
  headers.append('content-type', 'application/json');
  const response = await fetch(url, {
    headers,
    method: 'PUT',
    body: JSON.stringify({
      projectId,
      nodeId,
      name: businessName,
      bizdate: moment().subtract(1, 'day').format('YYYY-MM-DD 00:00:00'),
    }),
  });
  const result = await response.json();
  return result;
}
export async function getLog(instanceId: number, projectEnv: string): Promise<Log> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_LOG, {
    instanceId,
    projectEnv,
  });
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
