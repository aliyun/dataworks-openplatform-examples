"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
exports.run = void 0;
var fs_extra_1 = __importDefault(require("fs-extra"));
var path_1 = __importDefault(require("./path"));
var helper_1 = require("./helper");
function run() {
    fs_extra_1["default"].removeSync(path_1["default"].backendBuildTarget);
    fs_extra_1["default"].removeSync(path_1["default"].fontendBuildTarget);
    (0, helper_1.clearDir)(path_1["default"].backendHtmlRoot, ['checkpreload.htm']);
}
exports.run = run;
function default_1(program) {
    var nextProgram = program;
    nextProgram = nextProgram.command('dev').description('Clean Build Result');
    nextProgram.action(run);
    return nextProgram;
}
exports["default"] = default_1;
