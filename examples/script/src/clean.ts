import type { Command } from 'commander';
import fs from 'fs-extra';
import path from './path';
import { clearDir } from './helper';

export function run() {
  fs.removeSync(path.backendBuildTarget);
  fs.removeSync(path.fontendBuildTarget);
  clearDir(path.backendHtmlRoot, ['checkpreload.htm']);
}
export default function (program: Command) {
  let nextProgram = program;
  nextProgram = nextProgram.command('dev').description('Clean Build Result');
  nextProgram.action(run);
  return nextProgram;
}
