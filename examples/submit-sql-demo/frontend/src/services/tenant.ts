import * as helpers from '../helpers';
export const HOST = 'http://localhost:8008';
export const IDE_SERVICE = `${HOST}/ide`;
export const METHOD_URL = {
  GET_PROJECT_LIST: `${IDE_SERVICE}/listProjects`,
};

export interface Project {
  projectStatusCode: string;
  projectStatus: number;
  projectName: string;
  projectIdentifier: number;
  projectId: number;
  projectDescription: string;
  projectOwnerBaseId: string;
}
export interface ProjectList {
  pageNumber: number;
  pageSize: number;
  totalCount: number;
  projectList: Project[];
}

export async function getProjectList(): Promise<ProjectList> {
  const url = helpers.createFetchUrl(METHOD_URL.GET_PROJECT_LIST, {
    pageNumber: 1,
    pageSize: 100,
  });
  const response = await fetch(url);
  const result = await response.json();
  return result;
}
