"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
exports.backendHtmlRoot = exports.fontendBuildTarget = exports.jarFilePath = exports.pomJson = exports.pomData = exports.backendPOMPath = exports.frontendPath = exports.backendBuildTarget = exports.backendPath = exports.root = void 0;
var fs_1 = __importDefault(require("fs"));
var xml2json_1 = __importDefault(require("xml2json"));
var path_1 = __importDefault(require("path"));
exports.root = path_1["default"].resolve(process.cwd(), './');
exports.backendPath = path_1["default"].resolve(exports.root, './backend');
exports.backendBuildTarget = path_1["default"].resolve(exports.backendPath, './target');
exports.frontendPath = path_1["default"].resolve(exports.root, './frontend');
exports.backendPOMPath = path_1["default"].resolve(exports.backendPath, './pom.xml');
exports.pomData = fs_1["default"].readFileSync(exports.backendPOMPath);
exports.pomJson = JSON.parse(xml2json_1["default"].toJson(exports.pomData));
exports.jarFilePath = path_1["default"].resolve(exports.backendBuildTarget, "./".concat(exports.pomJson.project.name, "-").concat(exports.pomJson.project.version, ".jar"));
exports.fontendBuildTarget = path_1["default"].resolve(exports.frontendPath, './dist');
exports.backendHtmlRoot = path_1["default"].resolve(exports.backendPath, './src/main/resources/static');
exports["default"] = {
    root: exports.root,
    backendPath: exports.backendPath,
    frontendPath: exports.frontendPath,
    backendPOMPath: exports.backendPOMPath,
    pomData: exports.pomData,
    pomJson: exports.pomJson,
    jarFilePath: exports.jarFilePath,
    fontendBuildTarget: exports.fontendBuildTarget,
    backendHtmlRoot: exports.backendHtmlRoot,
    backendBuildTarget: exports.backendBuildTarget
};
