import * as helpers from '../helpers';

export const HOST = 'http://localhost:8008';
export const META_SERVICE = `${HOST}/meta`;
export const METHOD_URL = {
  GET_TABLE_LIST: `${META_SERVICE}/listTables`,
  GET_TABLE_DETAIL: `${META_SERVICE}/getTable`,
  GET_TABLE_COLUMNS: `${META_SERVICE}/getTableColumn`,
  GET_TABLE_PARTITION: `${META_SERVICE}/getTablePartition`,
  GET_TABLE_LINEAGE: `${META_SERVICE}/getTableLineage`,
  CREATE_TABLE: `${META_SERVICE}/createTable`,
};

export interface Theme {
  themeId?: number;
  themeLevel?: number;
}
export interface LineageEntity {
  createTimestamp: number;
  tableGuid: string;
  tableName: string;
}
export interface TableEntity {
  partitionPath: string;
  dataSize: number;
  partitionName: string;
  comment: string;
  modifiedTime: number;
  createTime: number;
  recordCount: number;
  partitionType: string;
  partitionGuid: string;
  partitionLocation: string;
  tableGuid: string;
}
export interface TableColumn {
  caption: string;
  columnGuid: string;
  columnName: string;
  columnType: string;
  comment: string;
  isForeignKey: boolean;
  isPartitionColumn: boolean;
  isPrimaryKey: boolean;
  position: number;
  relationCount: number;
}
export interface CreateTableColumn {
  columnNameCn?: string;
  columnName: string;
  comment?: string;
  columnType: string;
  seqNumber?: number;
  length?: number;
  isPartitionCol?: boolean;
}
export interface TableItem {
  databaseName: string;
  tableGuid: string;
  tableName: string;
}
export interface TableListInput {
  dataSourceType: string;
  appGuid?: string;
  clusterId?: string;
  databaseName?: string;
  pageNumber: number;
}
export interface TableListOutput {
  tableEntityList: TableItem[];
  pageSize: number;
  pageNumber: number;
  totalCount: number;
}
export interface TableDetailInput {
  dataSourceType?: string;
  extension?: boolean;
  tableGuid: string;
  databaseName?: string;
  clusterId?: string;
  tableName?: string;
}
export interface TableDetailOutput {
  caption: string;
  clusterId: string;
  columnCount: number;
  comment: string;
  createTime: number;
  dataSize: number;
  databaseName:string;
  envType: number;
  favoriteCount: number;
  isPartitionTable: boolean;
  isView: boolean;
  isVisible: number;
  lastAccessTime: number;
  lastDdlTime: number;
  lastModifyTime: number;
  lifeCycle: number;
  location: string;
  ownerId: string;
  partitionKeys: any;
  projectId: number;
  projectName: string;
  readCount: number;
  tableGuid: string;
  tableName: string;
  tenantId: number;
  viewCount: number;
}
export interface GetTableColumnsInput {
  dataSourceType?: string;
  clusterId?: string;
  pageNum: number;
  tableGuid: string;
  databaseName?: string;
  pageSize?: number;
  tableName?: string;
}
export interface GetTableColumnsOutput {
  columnList: TableColumn[];
  pageNum: number;
  pageSize: number;
  totalCount: number;
}
export interface GetTablePartitionInput {
  dataSourceType?: string;
  clusterId?: string;
  pageNumber: number;
  tableGuid: string;
  databaseName?: string;
  pageSize?: number;
  tableName?: string;
}
export interface GetTablePartitionOutput {
  dataEntityList: TableEntity[];
  pageNumber: number;
  pageSize: number;
  totalCount: number;
}
export interface GetTableLineageInput {
  dataSourceType?: string;
  clusterId?: string;
  tableGuid: string;
  nextPrimaryKey?: string;
  databaseName?: string;
  pageSize?: number;
  tableName?: string;
  direction: string;
}
export interface GetTableLineageOutput {
  dataEntityList: LineageEntity[];
  hasNext: boolean;
  nextPrimaryKey: string;
}
export interface CreateTableInput {
  clientToken?: string;
  columns: CreateTableColumn[];
  lifeCycle?: number;
  themes?: Theme[];
  logicalLevelId?: number;
  endpoint?: string;
  envType?: number;
  TableName: string;
  appGuid?: string;
  projectId?: number;
  categoryId?: number;
  visibility?: number;
  physicsLevelId?: number;
  ownerId?: string;
  isView?: number;
  externalTableType?: string;
  location?: string;
  comment?: string;
}

export async function getTableList(params: TableListInput): Promise<TableListOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TABLE_LIST, params);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function getTableDetail(params: TableDetailInput): Promise<TableDetailOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TABLE_DETAIL, params);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function getMetaTableColumns(params: GetTableColumnsInput): Promise<GetTableColumnsOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TABLE_COLUMNS, params);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function getTablePartition(params: GetTablePartitionInput): Promise<GetTablePartitionOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TABLE_PARTITION, params);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function getTableLineage(params: GetTableLineageInput): Promise<GetTableLineageOutput> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_TABLE_LINEAGE, params);
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
export async function createTable(params: CreateTableInput) {
  const formData = new FormData();
  Object.keys(params).forEach(key => {
    if (Array.isArray((params as any)[key])) {
      (params as any)[key].forEach((e: any, idx: number) => {
        const keys = Object.keys(e);
        keys.forEach((k) => {
          formData.append(`${key}[${idx}].${k}`, e[k]);
        });
      });
    } else {
      formData.append(key, JSON.stringify((params as any)[key]).replace(/"/g, ''));
    }
  });
  const response = await fetch(METHOD_URL.CREATE_TABLE, {
    body: formData,
    method: 'POST',
    mode: 'cors',
  });
  const result = await response.json();
  return result;
}
