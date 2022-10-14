import { spawn } from 'child_process';
import chalk from 'chalk';
import path from './path';
import { checkServiceStarted } from './helper';

const bePort = 8008;
const host = `http://localhost:${bePort}`;
const beHeartCheckUrl = `${host}/checkpreload.htm`;

export function startBackendService() {
  return new Promise(async (resolve, reject) => {
    const process = spawn('java', [`-jar ${path.jarFilePath}`, `--server.port=${bePort}`], {
      cwd: path.backendPath,
      shell: true,
    });
    process.stdout.on('data', (data) => {
      const info = data.toString();
      console.log(chalk.blue(info));
    });
    process.stderr.on('data', (data) => {
      console.error(chalk.red(`Failed to Start Service: ${data}`));
    });
    process.on('error', (err) => {
      console.log(chalk.red('Failed to Start Service'));
      reject(err);
    });
    try {
      await checkServiceStarted(beHeartCheckUrl);
      console.log(chalk.green(`Successful Start Backend Service`));
      resolve(true);
    } catch (e) {
      reject('Timeout for Start Service.');
    }
  });
}
export function buildJar() {
  return new Promise((resolve, reject) => {
    let hasError = false;
    const process = spawn('mvn', ['clean', 'package', '-Dmaven.test.skip=true', 'spring-boot:repackage'], {
      cwd: path.backendPath,
      shell: true,
    });
    process.stdout.on('data', (data) => {
      const info = data.toString();
      console.log(chalk.blue(info));
    });
    process.stderr.on('data', (data) => {
      hasError = true;
      console.error(chalk.red(`Failed In Build Jar: ${data}`));
    });
    process.on('exit', () => {
      if (!hasError) {
        console.log(chalk.green(`Successful Build Jar in ${path.jarFilePath}`));
        resolve(true);
      }
    });
    process.on('error', (err) => {
      hasError = true;
      console.log(chalk.red(`Failed to Build Jar File`));
      reject(err);
    });
    process.on('close', () => {
      resolve(true);
    });
  });
}
export async function dev() {
  try {
    await buildJar();
    await startBackendService();
    return host;
  } catch (e) {
    console.error(chalk.red(e));
  }
}
export async function prod(preview) {
  try {
    await buildJar();
    preview && await startBackendService();
    return host;
  } catch (e) {
    console.error(chalk.red(e));
  }
}
