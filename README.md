# DataWorks 开放平台示例代码库

DataWorks开放平台是DataWorks对外提供数据和能力的开放通道。DataWorks开放平台提供开放API（OpenAPI）、开放事件（OpenEvent）、扩展程序（Extensions）的能力，可以帮助您快速实现各类应用系统对接DataWorks、方便快捷的进行数据流程管控、数据治理和运维，及时响应应用系统对接DataWorks的业务状态变化。

本示例库提供场景化示例代码，让您快速了解如何使用DataWorks 开放API、开放事件（OpenEvent）、扩展程序（Extensions）等开放能力实现各种业务场景。目前我们提供了以下场景的示例代码：

* [最佳实践：通过OpenAPI查询表列表、表详情等](examples/meta-api-demo/README.md)
* [最佳实践：运维大屏搭建](examples/workbench-screen-demo/README.md)
* [最佳实践：订阅实例状态变更消息](examples/event-instance-status-demo/README.md)
* [最佳实践：禁止使用MAX_PT函数](examples/extension-maxpt-demo/README.md)
* [最佳实践：任务发布封网管控](examples/extension-deploy-control-demo/README.md)
* [最佳实践：数据开发、提交与运行](examples/submit-sql-demo/README.md)

# 工程初始化

## 环境准备

1. 安装JAVA JDK

JAVA JDK可在Oracle[官网下载](https://www.oracle.com/java/technologies/downloads/)，根据您的电脑版本选择合适的JAVA JDK即可，本范例限制使用JAVA 1.8以上版本。

2. 安装Maven

Maven是JAVA的运行环境工具，您可以在Maven官网中[下载](https://maven.apache.org/download.cgi)，根据您的电脑版本选择合适的Maven下载包即可。下载完成后，可以参考[以下文档](https://maven.apache.org/install.html)来解压并安装Maven。

3. 安装Node

因本范例库涉及前端介面演示，因此需要客户端也安装Node环境。Node可以在官网中[下载](https://nodejs.org/en/)。本范例限制使用Node版本为14以上。

4. 安装pnpm

pnpm是一个库管理工具，因为本范例库使用了工作空间模式（避免一些公用组件发包到npm上），所以需要安装此工具。pnpm可以在npm中下载[链接](https://pnpm.io/installation)，若您已经安装了Node，可以直接执行以下命令：

```shell
npm install -g pnpm
```

5. 检查环境是否准备好了

当您完成上述操作后，您可以执行以下命令行来查看本示例工程所需的环境是否都已准备好：

```shell
java -version // 如果JAVA JDK已安装成功，执行命令将展示版本号，否则会报没有命令错误
mvn -v // 如果Maven已安装成功，执行命令将展示版本号，否则会报没有命令错误
npm -v // 如果Node已安装成功，执行命令将展示版本号，否则会报没有命令错误
pnpm -v // 如果pnpm已安装成功，执行命令将展示版本号，否则会报没有命令错误
```

## 如何使用

1. 安装并关连依赖

完成环境准备后，您可以下载本示例工程到本地，然后执行初次安装操作：

```shell
pnpm install
```

2. 输入您的AKSK

完成安装后，您可以在根目录下找到application.properties文件，当中可以填入您的AK、SK、Region信息与endpoint等信息，这些信息会在工程启动时同步到各个应用示例当中，并在您运行示例时以此信息访问您的DataWorks开放能力。

```text
## 调用子账号的AccessKey Id
api.access-key-id={access-key}

## 调用账号的AccessKey Secret
api.access-key-secret={secret-key}

## dataworks服务所在的reigonId cn-shanghai/cn-hangzhou
# api.region-id=cn-hangzhou
api.region-id={regionId}

## dataworks openapi的product 默认是dataworks-public
api.product=dataworks-public

## dataworks openapi的endpoint地址
## 默认公网访问dataworks.${regionId}.aliyuncs.com
## VPC访问dataworks-vpc.${regionId}.aliyuncs.com
# api.endpoint=dataworks.cn-hangzhou.aliyuncs.com
api.endpoint={endpoint}

## 指定网络环境 公网访问设置为false，vpc则为true
api.vpc-env=false
```

3. 运行示例

完成上述配置后，就可以开始运行示例了，您可以透过执行以下命令行来直接启动。

```shell
npm run example:workbench-screen // 运行运维中心示例
npm run example:meta-api // 运行元数据示例
npm run example:event-instance-status // 运行订阅实例状态变更消息的示例
npm run example:extension-maxpt // 运行禁止使用MAX_PT函数的示例
npm run example:extension-deploy-control // 运行任务发布封网管控的示例
```

4. 查看结果

完成运行后，您可以到浏览器上查看运行结果，带前端交互页面的示例可以访问以下地址： 

```shell
https://localhost:8080
```

纯后端的例子可以访问以下地址：

```shell
http://localhost:8008
```

## 更多DataWorks能力

您可以访问以下页面来获取更多强大的大数据解决方案：[链接](https://www.aliyun.com/product/bigdata/ide?spm=5176.19720258.J_3207526240.14.e93976f4fSmZlv&scm=20140722.S_function@@product@@89892._.ID_function@@product@@89892-RL_dataworks-LOC_bar-OR_ser-V_2-P0_0)
