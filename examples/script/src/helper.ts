import http from 'http';
import fs from 'fs-extra';
import path from 'path';
import { backendHtmlRoot } from './path';

const heartCheckPeriod = 500;
const queryLimit = 50;

export function checkServiceStarted(host) {
  let count = 0;
  return new Promise((resolve, reject) => {
    const timer = setInterval(() => {
      count += 1;
      const req = http.get(host, (response) => {
        if (response) {
          clearInterval(timer);
          resolve(true);
        }
      });
      req.on('error', () => {});
      req.end();
      if (count >= queryLimit) {
        clearInterval(timer);
        reject('Timeout for Start Service.');
      }
    }, heartCheckPeriod);
  });
}
export function clearDir(dir, exclude = []) {
  try {
    const files = fs.readdirSync(dir);
    files.forEach(file => {
      const fileDir = path.resolve(backendHtmlRoot, file);
      !exclude.includes(file) && fs.removeSync(fileDir);
    });
  } catch (e) {
    console.error(e);
  }
}
