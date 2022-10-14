"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
exports.prod = exports.dev = exports.buildJar = exports.startBackendService = void 0;
var child_process_1 = require("child_process");
var chalk_1 = __importDefault(require("chalk"));
var path_1 = __importDefault(require("./path"));
var helper_1 = require("./helper");
var bePort = 8008;
var host = "http://localhost:".concat(bePort);
var beHeartCheckUrl = "".concat(host, "/checkpreload.htm");
function startBackendService() {
    var _this = this;
    return new Promise(function (resolve, reject) { return __awaiter(_this, void 0, void 0, function () {
        var process, e_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    process = (0, child_process_1.spawn)('java', ["-jar ".concat(path_1["default"].jarFilePath), "--server.port=".concat(bePort)], {
                        cwd: path_1["default"].backendPath,
                        shell: true
                    });
                    process.stdout.on('data', function (data) {
                        var info = data.toString();
                        console.log(chalk_1["default"].blue(info));
                    });
                    process.stderr.on('data', function (data) {
                        console.error(chalk_1["default"].red("Failed to Start Service: ".concat(data)));
                    });
                    process.on('error', function (err) {
                        console.log(chalk_1["default"].red('Failed to Start Service'));
                        reject(err);
                    });
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 3, , 4]);
                    return [4 /*yield*/, (0, helper_1.checkServiceStarted)(beHeartCheckUrl)];
                case 2:
                    _a.sent();
                    console.log(chalk_1["default"].green("Successful Start Backend Service"));
                    resolve(true);
                    return [3 /*break*/, 4];
                case 3:
                    e_1 = _a.sent();
                    reject('Timeout for Start Service.');
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    }); });
}
exports.startBackendService = startBackendService;
function buildJar() {
    return new Promise(function (resolve, reject) {
        var hasError = false;
        var process = (0, child_process_1.spawn)('mvn', ['clean', 'package', '-Dmaven.test.skip=true', 'spring-boot:repackage'], {
            cwd: path_1["default"].backendPath,
            shell: true
        });
        process.stdout.on('data', function (data) {
            var info = data.toString();
            console.log(chalk_1["default"].blue(info));
        });
        process.stderr.on('data', function (data) {
            hasError = true;
            console.error(chalk_1["default"].red("Failed In Build Jar: ".concat(data)));
        });
        process.on('exit', function () {
            if (!hasError) {
                console.log(chalk_1["default"].green("Successful Build Jar in ".concat(path_1["default"].jarFilePath)));
                resolve(true);
            }
        });
        process.on('error', function (err) {
            hasError = true;
            console.log(chalk_1["default"].red("Failed to Build Jar File"));
            reject(err);
        });
        process.on('close', function () {
            resolve(true);
        });
    });
}
exports.buildJar = buildJar;
function dev() {
    return __awaiter(this, void 0, void 0, function () {
        var e_2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, buildJar()];
                case 1:
                    _a.sent();
                    return [4 /*yield*/, startBackendService()];
                case 2:
                    _a.sent();
                    return [2 /*return*/, host];
                case 3:
                    e_2 = _a.sent();
                    console.error(chalk_1["default"].red(e_2));
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
exports.dev = dev;
function prod(preview) {
    return __awaiter(this, void 0, void 0, function () {
        var _a, e_3;
        return __generator(this, function (_b) {
            switch (_b.label) {
                case 0:
                    _b.trys.push([0, 4, , 5]);
                    return [4 /*yield*/, buildJar()];
                case 1:
                    _b.sent();
                    _a = preview;
                    if (!_a) return [3 /*break*/, 3];
                    return [4 /*yield*/, startBackendService()];
                case 2:
                    _a = (_b.sent());
                    _b.label = 3;
                case 3:
                    _a;
                    return [2 /*return*/, host];
                case 4:
                    e_3 = _b.sent();
                    console.error(chalk_1["default"].red(e_3));
                    return [3 /*break*/, 5];
                case 5: return [2 /*return*/];
            }
        });
    });
}
exports.prod = prod;
