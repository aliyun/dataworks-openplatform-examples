# DataWorks Open-Platform Sample Library

DataWorks Open-Platform is the open channel through which DataWorks provides data and capabilities to external parties. The DataWorks Open-Platform offers capabilities such as OpenAPI, OpenEvent, and Extensions, which can help you quickly integrate various application systems with DataWorks. It facilitates efficient data flow control, data governance, operations and maintenance, allowing timely response to changes in business status when integrating with DataWorks.
This sample library provides scenario-based example code to help you understand how to use DataWorks OpenAPI, OpenEvent, Extensions, and other open capabilities to implement various business scenarios. Currently, we provide sample code for the following scenarios:

* [Best Practices: query table lists and table details through OpenAPI](examples/meta-api-demo/README-EN.md)
* [Best Practice: Operation and Maintenance Large Screen Building](examples/workbench-screen-demo/README-EN.md)
* [Best Practice: Subscribe to Instance Status Change Messages](examples/event-instance-status-demo/README-EN.md)
* [Best Practice: Prohibit MAX_PT Function](examples/extension-maxpt-demo/README-EN.md)
* [Best Practice: Task Release Network Control](examples/extension-deploy-control-demo/README-EN.md)
* [Best Practices: Material Development, Submission and Operation](examples/submit-sql-demo/README-EN.md)

# Project Initialization

## Environment Preparation

1. Installing Java JDK

Java JDK (Java Development Kit) is required for developing and running Java applications. To install Java JDK, please follow the steps below:
Visit the official Oracle website (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) to download the latest version of Java JDK compatible with your operating system.
Once the download is complete, run the installer and follow the on-screen instructions to install Java JDK.
After the installation, open a command prompt or terminal and type the following command to verify the installation:

```shell
java -version
```

If the installation is successful, the command prompt or terminal will display the installed Java JDK version. With Java JDK successfully installed, you are now ready to develop and run Java applications on your computer.

2. Installing Maven

Maven is a build tool and dependency management tool for Java applications. To install Maven, please follow the steps below:
Visit the official Apache Maven website (https://maven.apache.org/download.cgi) and download the appropriate Maven distribution for your computer's operating system.
Once the download is complete, extract the Maven distribution package to a directory of your choice.
Set up the Maven environment variables:

On Windows:
Right-click on "My Computer" and select "Properties".
Click on "Advanced System Settings" and then click on the "Environment Variables" button.
In the "User variables" or "System variables" section, locate the "Path" variable and add the path to the Maven bin directory (e.g., C:\apache-maven\bin).

On macOS or Linux:
Open a terminal and navigate to your home directory.
Open the .bash_profile or .bashrc file using a text editor.
Add the following lines to the file:

```shell
export M2_HOME=/path/to/maven  # Replace /path/to/maven with the actual path to Maven installation
export PATH=$PATH:$M2_HOME/bin
```

Save and close the file. To verify that Maven is installed successfully, open a new terminal or command prompt and run the following command:

```shell
mvn -v
```

If Maven is installed properly, the command will display the Maven version information. After the installation is complete, you can use Maven to build and manage your Java projects.

3. Installing Node

Node.js is a JavaScript runtime environment that allows you to run JavaScript code outside of a web browser. To install Node.js, please follow the steps below:
Visit the official Node.js website (https://nodejs.org) to download the latest version of Node.js compatible with your operating system.
Once the download is complete, run the installer and follow the on-screen instructions to install Node.js.
After the installation, open a command prompt or terminal and type the following command to verify the installation:

```shell
node -v
```

If the installation is successful, the command prompt or terminal will display the installed Node.js version.
Additionally, Node.js comes with npm (Node Package Manager) pre-installed, which allows you to install, manage, and update packages for your Node.js projects.
With Node.js successfully installed, you are now ready to develop and run Node.js applications on your computer.

4. Installing pnpm

pnpm is a package manager for JavaScript projects. To install pnpm, follow the steps below:
Open a command prompt or terminal.
Run the following command to install pnpm globally:

```shell
npm install -g pnpm
```

This command will download and install pnpm from the npm registry.
After the installation is complete, you can verify if pnpm is installed correctly by running the following command:

```shell
pnpm -v
```

If pnpm is installed successfully, the command prompt or terminal will display the version number of pnpm.
Now you have pnpm installed on your system and you can use it to manage packages for your JavaScript projects.

5. Check for Environment

To check if the environment is ready, you can execute the following command lines to see if the required components for this example project are properly installed:

```shell
java -version
```

This command will display the version number of Java JDK if it is installed successfully; otherwise, it will show a "command not found" error.

```shell
mvn -v
```

This command will display the version number of Maven if it is installed successfully; otherwise, it will show a "command not found" error.

```shell
npm -v
```

This command will display the version number of Node.js if it is installed successfully; otherwise, it will show a "command not found" error.

```shell
pnpm -v
```

This command will display the version number of pnpm if it is installed successfully; otherwise, it will show a "command not found" error.
By running these commands, you can verify if Java JDK, Maven, Node.js, and pnpm are properly installed and ready for use in this example project.

## How to Use

1. Installation and Related to Dependencies

After installing and setting up the environment, you can download the example project to your local machine and perform the initial installation by executing the following command:

```shell
pnpm install
```

2. Enter your AK/SK (Access Key and Secret Key)

After the installation is complete, you can find the application.properties file in the root directory. In this file, you can fill in your AK (Access Key), SK (Secret Key), Region information, endpoint, and other details. These details will be synchronized to each application example when the project starts, and will be used to access your DataWorks open capabilities when running the examples.

```text
## Use the Access Key ID of the sub-account
api.access-key-id={access-key}

## Use the Access Key Secret of the account
api.access-key-secret={secret-key}

## Region ID where DataWorks service is located, e.g., cn-shanghai/cn-hangzhou
# api.region-id=cn-hangzhou
api.region-id={regionId}

## DataWorks OpenAPI product, default is dataworks-public
api.product=dataworks-public

## DataWorks OpenAPI endpoint address
## Default for public network access: dataworks.${regionId}.aliyuncs.com
## For VPC access: dataworks-vpc.${regionId}.aliyuncs.com
# api.endpoint=dataworks.cn-hangzhou.aliyuncs.com
api.endpoint={endpoint}

Specify the network environment: false for public network access, true for VPC
api.vpc-env=false
```

3. Running the Examples.

After completing the configuration above, you can start running the examples. You can directly start by executing the following command:

```shell
npm run example:workbench-screen // Run the Workbench screen example
npm run example:meta-api // Run the metadata example
npm run example:event-instance-status // Run the example for subscribing to instance status change events
npm run example:extension-maxpt // Run the example that restricts the use of the MAX_PT function
npm run example:extension-deploy-control // Run the example for task deployment access control
```

Viewing the Results
After running the examples, you can check the results in your web browser. For examples with interactive front-end pages, you can access the following URL:

```shell
https://localhost:8080
```

For back-end only examples, you can access the following URL:

```shell
http://localhost:8008
```

## More DataWorks Capabilities

You can visit the following page to explore more powerful big data solutions: [Link](https://www.alibabacloud.com/product/ide)
