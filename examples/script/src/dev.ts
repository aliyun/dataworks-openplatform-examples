import type { Command } from 'commander';
import path from './path';
import { clearDir } from './helper';
import { dev as backendDev } from './backend';
import { dev as frontendDev } from './frontend';

export interface Options {
  backendOnly: boolean;
}

export async function run(options: Options) {
  !options.backendOnly && clearDir(path.backendHtmlRoot, ['checkpreload.htm']);
  await backendDev();
  !options.backendOnly && await frontendDev();
}
export default function (program: Command) {
  let nextProgram = program;
  nextProgram = nextProgram.command('dev').description('Start Dev Build');
  nextProgram = nextProgram.option('-b, --backendOnly', 'Build Backend Only', false);
  nextProgram.action((options: Options) => {
    run(options);
  });
  return nextProgram;
}
