"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
exports.clearDir = exports.checkServiceStarted = void 0;
var http_1 = __importDefault(require("http"));
var fs_extra_1 = __importDefault(require("fs-extra"));
var path_1 = __importDefault(require("path"));
var path_2 = require("./path");
var heartCheckPeriod = 500;
var queryLimit = 50;
function checkServiceStarted(host) {
    var count = 0;
    return new Promise(function (resolve, reject) {
        var timer = setInterval(function () {
            count += 1;
            var req = http_1["default"].get(host, function (response) {
                if (response) {
                    clearInterval(timer);
                    resolve(true);
                }
            });
            req.on('error', function () { });
            req.end();
            if (count >= queryLimit) {
                clearInterval(timer);
                reject('Timeout for Start Service.');
            }
        }, heartCheckPeriod);
    });
}
exports.checkServiceStarted = checkServiceStarted;
function clearDir(dir, exclude) {
    if (exclude === void 0) { exclude = []; }
    try {
        var files = fs_extra_1["default"].readdirSync(dir);
        files.forEach(function (file) {
            var fileDir = path_1["default"].resolve(path_2.backendHtmlRoot, file);
            !exclude.includes(file) && fs_extra_1["default"].removeSync(fileDir);
        });
    }
    catch (e) {
        console.error(e);
    }
}
exports.clearDir = clearDir;
