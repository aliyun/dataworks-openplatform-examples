import { Command } from 'commander';
import dev from './dev';
import build from './build';
import clean from './clean';

const program = new Command();

program.name('dw-openplatform-script').description('Example Builder').version('0.1.0');
/**
 * Add Dev Command
 */
dev(program);
/**
 * Add Build Command
 */
build(program);
/**
 * Add Clean Command
 */
clean(program);
/**
 * Parse the command
 */
 program.parse();
