"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
var commander_1 = require("commander");
var dev_1 = __importDefault(require("./dev"));
var build_1 = __importDefault(require("./build"));
var clean_1 = __importDefault(require("./clean"));
var program = new commander_1.Command();
program.name('dw-openplatform-script').description('Example Builder').version('0.1.0');
/**
 * Add Dev Command
 */
(0, dev_1["default"])(program);
/**
 * Add Build Command
 */
(0, build_1["default"])(program);
/**
 * Add Clean Command
 */
(0, clean_1["default"])(program);
/**
 * Parse the command
 */
program.parse();
