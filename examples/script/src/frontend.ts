import { spawn } from 'child_process';
import chalk from 'chalk';
import path from './path';
import { checkServiceStarted } from './helper';

const fePort = 8080;
const feHeartCheckUrl = `http://localhost:${fePort}`;

export function startFrontendService() {
  return new Promise(async (resolve, reject) => {
    const process = spawn('npm', ['run', 'dev', `-- --port ${fePort}`], {
      cwd: path.frontendPath,
      shell: true,
    });
    process.stdout.on('data', (data) => {
      const info = data.toString();
      console.log(chalk.blue(info));
    });
    process.stderr.on('data', (data) => {
      console.error(chalk.red(`Failed to Start Frontend: ${data}`));
    });
    process.on('error', (err) => {
      console.log(chalk.red('Failed to Start Frontend'));
      reject(err);
    });
    try {
      await checkServiceStarted(feHeartCheckUrl);
      resolve(true);
    } catch (e) {
      reject('Timeout for Start Frontend.');
    }
  });
}
export function buildFrontend() {
  return new Promise((resolve, reject) => {
    let hasError = false;
    const process = spawn('npm', ['run', 'build'], {
      cwd: path.frontendPath,
      shell: true,
    });
    process.stdout.on('data', (data) => {
      const info = data.toString();
      console.log(chalk.blue(info));
    });
    process.stderr.on('data', (data) => {
      hasError = true;
      console.error(chalk.red('Failed In Build Frontend'));
      reject(data);
    });
    process.on('exit', () => {
      if (!hasError) {
        console.log(chalk.green('Successful Build Frontend'));
        resolve(true);
      }
    });
    process.on('error', (err) => {
      hasError = true;
      console.log(chalk.red(`Failed to Build Frontend`));
      reject(err);
    });
    process.on('close', () => {
      resolve(true);
    });
  });
}
export async function dev() {
  try {
    await startFrontendService();
  } catch (e) {
    console.error(chalk.red(e));
  }
}
export async function prod() {
  try {
    await buildFrontend();
  } catch (e) {
    console.error(chalk.red(e));
  }
}
