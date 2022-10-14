import fs from 'fs';
import parser from 'xml2json';
import path from 'path';

export const root = path.resolve(process.cwd(), './');
export const backendPath = path.resolve(root, './backend');
export const backendBuildTarget = path.resolve(backendPath, './target');
export const frontendPath = path.resolve(root, './frontend');
export const backendPOMPath = path.resolve(backendPath, './pom.xml');
export const pomData = fs.readFileSync(backendPOMPath);
export const pomJson = JSON.parse(parser.toJson(pomData));
export const jarFilePath = path.resolve(backendBuildTarget, `./${pomJson.project.name}-${pomJson.project.version}.jar`);
export const fontendBuildTarget = path.resolve(frontendPath, './dist');
export const backendHtmlRoot = path.resolve(backendPath, './src/main/resources/static');

export default {
  root,
  backendPath,
  frontendPath,
  backendPOMPath,
  pomData,
  pomJson,
  jarFilePath,
  fontendBuildTarget,
  backendHtmlRoot,
  backendBuildTarget,
};
