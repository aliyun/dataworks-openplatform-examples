import type { Command } from 'commander';
import fs from 'fs-extra';
import open from 'open';
import path from './path';
import { clearDir } from './helper';
import { prod as backendProd } from './backend';
import { prod as frontendProd } from './frontend';

export interface Options {
  preview: boolean;
  backendOnly: boolean;
}

export async function run(options: Options) {
  if (!options.backendOnly) {
    await frontendProd();
    clearDir(path.backendHtmlRoot, ['checkpreload.htm']);
    fs.copySync(path.fontendBuildTarget, path.backendHtmlRoot);
  }
  const host = await backendProd(options.preview);
  !options.backendOnly && options.preview && open(host);
}
export default function (program: Command) {
  let nextProgram = program;
  nextProgram = nextProgram.command('build').description('Start Prod Build');
  nextProgram = nextProgram.option('-p, --preview', 'Preview Build Result', true);
  nextProgram = nextProgram.option('-b, --backendOnly', 'Build Backend Only', false);
  nextProgram.action((options: Options) => {
    run(options);
  });
  return nextProgram;
}
