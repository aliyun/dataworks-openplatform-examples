# 禁止使用MAX_PT函数示例

DataWorks 提供内置的流程检查，如任务发布前代码评审、数据治理中心治理项的内置检查项校验，此外，DataWorks 还支持您自定义校验逻辑并接入 DataWorks，实现 DataWorks 流程管控。本文以在提交与发布时校验代码中是否存在 MAX_PT 函数为例，为您介绍如何基于扩展程序实现工作空间中不允许使用特定函数。

具体操作流程可参考文章：[链接](https://openplatform.data.aliyun.com/playground/case?id=denyMAXPT)

## 如何开始

1. 在命令行中返回根目录
2. 在根目录输入以下指令：

```shell
npm run example:extension-maxpt
```
