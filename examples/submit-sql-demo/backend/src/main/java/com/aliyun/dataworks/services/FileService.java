package com.aliyun.dataworks.services;

import com.aliyun.dataworks.dto.*;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * the ide files manager service
 *
 * @author dataworks demo
 */
@Service
public class FileService {

    @Autowired
    private DataWorksOpenApiClient dataWorksOpenApiClient;

    public static final int CYCLE_NUM = 10;

    /**
     * 分页查询
     * 参考文档https://help.aliyun.com/document_detail/173942.html?spm=a2c4g.11186623.0.0.549b6cf0blqGsm
     *
     * @param listFilesDTO
     * @return
     */
    public List<ListFilesResponse.Data.File> listFiles(ListFilesDTO listFilesDTO) {
        try {
            ListFilesRequest listFilesRequest = new ListFilesRequest();
            // 文件路径： “业务流程/” + 目标业务流程名 + 目录名 + 最新文件夹名
            // 业务流程/我的第一个业务流程/MaxCompute/ods层，不要加"数据开发"
            listFilesRequest.setFileFolderPath(listFilesDTO.getFileFolderPath());
            // 文件的代码类型。支持多个，以逗号分隔，例子：10,23
            listFilesRequest.setFileTypes(listFilesDTO.getFileTypes());
            // 文件名称的关键字。支持模糊匹配
            listFilesRequest.setKeyword(listFilesDTO.getKeyword());
            // 调度节点的ID
            listFilesRequest.setNodeId(listFilesDTO.getNodeId());
            // 文件责任人
            listFilesRequest.setOwner(listFilesDTO.getOwner());
            // 请求的数据页数
            listFilesRequest.setPageNumber(listFilesDTO.getPageNumber() <= 0 ? 1 : listFilesDTO.getPageNumber());
            // 每页显示的条数
            listFilesRequest.setPageSize(listFilesDTO.getPageSize() <= 10 ? 10 : listFilesDTO.getPageSize());
            // DataWorks工作空间的ID
            listFilesRequest.setProjectId(listFilesDTO.getProjectId());
            // 文件所属的功能模块
            listFilesRequest.setUseType(listFilesDTO.getUseType());
            ListFilesResponse listFilesResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(listFilesRequest);
            ListFilesResponse.Data fileData = listFilesResponse.getData();
            if (fileData.getFiles() != null && !fileData.getFiles().isEmpty()) {
                for (ListFilesResponse.Data.File file : fileData.getFiles()) {
                    // 业务流程ID
                    System.out.println(file.getBusinessId());
                    // 文件ID
                    System.out.println(file.getFileId());
                    // 文件名称
                    System.out.println(file.getFileName());
                    // 文件类型 10
                    System.out.println(file.getFileType());
                    // 节点ID
                    System.out.println(file.getNodeId());
                    // 文件夹ID
                    System.out.println(file.getFileFolderId());
                }
            }
            return fileData.getFiles();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }

    /**
     * 新增文件
     * 参考文档：https://help.aliyun.com/document_detail/173937.html
     *
     * @param createFileDTO
     */
    public Long createFile(CreateFileDTO createFileDTO) {
        try {
            CreateFileRequest createFileRequest = new CreateFileRequest();
            // 任务的高级配置
            createFileRequest.setAdvancedSettings(createFileDTO.getAdvancedSettings());
            // 文件是否开启自动解析功能 必填
            createFileRequest.setAutoParsing(createFileDTO.getAutoParsing());
            // 出错自动重跑时间间隔，单位为毫秒。最大为1800000毫秒（30分钟）
            createFileRequest.setAutoRerunIntervalMillis(createFileDTO.getAutoRerunIntervalMillis());
            // 自动重试次数
            createFileRequest.setAutoRerunTimes(createFileDTO.getAutoRerunTimes());
            // 文件发布成任务后，任务执行时连接的数据源。 必填
            createFileRequest.setConnectionName(createFileDTO.getConnectionName());
            // 文件代码内容 必填
            createFileRequest.setContent(createFileDTO.getContent());
            // 周期调度的cron表达式 必填
            createFileRequest.setCronExpress(createFileDTO.getCronExpress());
            // 调度周期的类型 必填
            createFileRequest.setCycleType(createFileDTO.getCycleType());
            // 依赖上一周期的节点列表
            createFileRequest.setDependentNodeIdList(createFileDTO.getDependentNodeIdList());
            // 依赖上一周期的方式 必填
            createFileRequest.setDependentType(createFileDTO.getDependentType());
            // 停止自动调度的时间戳，单位为毫秒。
            createFileRequest.setEndEffectDate(createFileDTO.getEndEffectDate());
            // 文件描述
            createFileRequest.setFileDescription(createFileDTO.getFileDescription());
            // 文件的路径 必填
            createFileRequest.setFileFolderPath(createFileDTO.getFileFolderPath());
            // 文件的名称 必填
            createFileRequest.setFileName(createFileDTO.getFileName());
            // 文件的代码类型 必填
            createFileRequest.setFileType(createFileDTO.getFileType());
            // 文件依赖的上游文件的输出名称，多个输出使用英文逗号（,）分隔。必填
            createFileRequest.setInputList(createFileDTO.getInputList());
            // 文件责任人的阿里云用户ID。如果该参数为空，则默认使用调用者的阿里云用户ID。 必填
            createFileRequest.setOwner(createFileDTO.getOwner());
            // 调度参数。
            createFileRequest.setParaValue(createFileDTO.getParaValue());
            // 项目空间ID 必填
            createFileRequest.setProjectId(createFileDTO.getProjectId());
            // 重跑属性
            createFileRequest.setRerunMode(createFileDTO.getRerunMode());
            // 任务的资源组 必填
            createFileRequest.setResourceGroupIdentifier(createFileDTO.getResourceGroupIdentifier());
            // 调度的类型
            createFileRequest.setSchedulerType(createFileDTO.getSchedulerType());
            // 开始自动调度的毫秒时间戳
            createFileRequest.setStartEffectDate(createFileDTO.getStartEffectDate());
            // 是否暂停调度
            createFileRequest.setStop(createFileDTO.getStop());
            CreateFileResponse createFileResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(createFileRequest);
            // requestId
            System.out.println(createFileResponse.getRequestId());
            // fileId
            System.out.println(createFileResponse.getData());
            return createFileResponse.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }

    /**
     * 更改文件
     * https://help.aliyun.com/document_detail/173951.html
     *
     * @param updateFileDTO
     */
    public boolean updateFile(UpdateFileDTO updateFileDTO) {
        try {
            UpdateFileRequest updateFileRequest = new UpdateFileRequest();
            // 任务的高级配置，具体格式参考文档
            updateFileRequest.setAdvancedSettings(updateFileDTO.getAdvancedSettings());
            // 文件是否开启自动解析功能
            updateFileRequest.setAutoParsing(updateFileDTO.getAutoParsing());
            // 出错自动重跑时间间隔，单位为毫秒。最大为1800000毫秒（30分钟）。
            updateFileRequest.setAutoRerunIntervalMillis(updateFileDTO.getAutoRerunIntervalMillis());
            // 文件出错后，自动重跑的次数
            updateFileRequest.setAutoRerunTimes(updateFileDTO.getAutoRerunTimes());
            // 文件对应任务执行时，任务使用的数据源标识符
            updateFileRequest.setConnectionName(updateFileDTO.getConnectionName());
            // 文件代码内容
            updateFileRequest.setContent(updateFileDTO.getContent());
            // 周期调度的cron表达式，
            updateFileRequest.setCronExpress(updateFileDTO.getCronExpress());
            // 调度周期的类型，包括NOT_DAY（分钟、小时）和DAY（日、周、月）
            updateFileRequest.setCycleType(updateFileDTO.getCycleType());
            // 当DependentType参数配置为USER_DEFINE时，用于设置当前文件具体依赖的节点ID。依赖多个节点时，使用英文逗号（,）分隔
            updateFileRequest.setDependentNodeIdList(updateFileDTO.getDependentNodeIdList());
            // 依赖上一周期的方式
            updateFileRequest.setDependentType(updateFileDTO.getDependentType());
            // 停止自动调度的时间戳，单位为毫秒。
            updateFileRequest.setEndEffectDate(updateFileDTO.getEndEffectDate());
            // 文件的描述
            updateFileRequest.setFileDescription(updateFileDTO.getFileDescription());
            // 文件所在的路径
            updateFileRequest.setFileFolderPath(updateFileDTO.getFileFolderPath());
            // 文件的ID
            updateFileRequest.setFileId(updateFileDTO.getFileId());
            // 文件的名称
            updateFileRequest.setFileName(updateFileDTO.getFileName());
            // 文件依赖的上游文件的输出名称。依赖多个输出时，使用英文逗号（,）分隔
            updateFileRequest.setInputList(updateFileDTO.getInputList());
            // 文件的输出
            updateFileRequest.setOutputList(updateFileDTO.getOutputList());
            // 文件所有者的用户ID
            updateFileRequest.setOwner(updateFileDTO.getOwner());
            // 调度参数
            updateFileRequest.setParaValue(updateFileDTO.getParaValue());
            // DataWorks工作空间的ID
            updateFileRequest.setProjectId(updateFileDTO.getProjectId());
            // 重跑属性 ALL_ALLOWED
            updateFileRequest.setRerunMode(updateFileDTO.getRerunMode());
            // 文件发布成任务后，任务执行时对应的资源组
            updateFileRequest.setResourceGroupIdentifier(updateFileDTO.getResourceGroupIdentifier());
            // 调度的类型 NORMAL
            updateFileRequest.setSchedulerType(updateFileDTO.getSchedulerType());
            // 开始自动调度的毫秒时间戳
            updateFileRequest.setStartEffectDate(updateFileDTO.getStartEffectDate());
            // 发布后是否立即启动
            updateFileRequest.setStartImmediately(updateFileDTO.getStartImmediately());
            // 是否暂停调度
            updateFileRequest.setStop(updateFileDTO.getStop());
            UpdateFileResponse updateFileResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(updateFileRequest);
            // requestId
            System.out.println(updateFileResponse.getRequestId());
            // 成功与否
            System.out.println(updateFileResponse.getSuccess());
            return updateFileResponse.getSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;
    }

    /**
     * 删除一个文件
     * 参考文件：https://help.aliyun.com/document_detail/173949.html
     *
     * @param deleteFileDTO
     * @return
     * @throws InterruptedException
     */
    public boolean deleteFile(DeleteFileDTO deleteFileDTO) throws InterruptedException {
        try {

            DeleteFileRequest deleteFileRequest = new DeleteFileRequest();
            deleteFileRequest.setFileId(deleteFileDTO.getFileId());
            deleteFileRequest.setProjectId(deleteFileDTO.getProjectId());
            DeleteFileResponse deleteFileResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(deleteFileRequest);
            System.out.println(deleteFileResponse.getRequestId());
            System.out.println(deleteFileResponse.getDeploymentId());

            GetDeploymentRequest getDeploymentRequest = new GetDeploymentRequest();
            getDeploymentRequest.setProjectId(deleteFileDTO.getProjectId());
            getDeploymentRequest.setDeploymentId(deleteFileResponse.getDeploymentId());
            for (int i = 0; i < CYCLE_NUM; i++) {
                GetDeploymentResponse getDeploymentResponse = dataWorksOpenApiClient.createClient()
                        .getAcsResponse(getDeploymentRequest);
                // 发布包当前的状态，包括0（就绪）、1（成功）和2（失败）。
                Integer deleteStatus = getDeploymentResponse.getData().getDeployment().getStatus();
                // 此处可以循环判断删除状态
                if (deleteStatus == 1) {
                    System.out.println("成功删除文件。");
                    break;
                } else {
                    System.out.println("正在删除文件");
                    Thread.sleep(1000L);
                }
            }

            GetProjectRequest getProjectRequest = new GetProjectRequest();
            getProjectRequest.setProjectId(deleteFileDTO.getProjectId());
            GetProjectResponse getProjectResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(getProjectRequest);
            // 标准模式有DEV和PROD，简单模式只有PROD
            Boolean standardMode = getProjectResponse.getData().getEnvTypes().size() == 2;
            if (standardMode) {
                // 标准模式需要把删除发布到线上
                DeployFileRequest deployFileRequest = new DeployFileRequest();
                deployFileRequest.setProjectId(deleteFileDTO.getProjectId());
                deployFileRequest.setFileId(deleteFileDTO.getFileId());
                DeployFileResponse deployFileResponse = dataWorksOpenApiClient.createClient()
                        .getAcsResponse(deployFileRequest);
                getDeploymentRequest.setDeploymentId(deployFileResponse.getData());
                for (int i = 0; i < CYCLE_NUM; i++) {
                    GetDeploymentResponse getDeploymentResponse = dataWorksOpenApiClient.createClient()
                            .getAcsResponse(getDeploymentRequest);
                    // 发布包当前的状态，包括0（就绪）、1（成功）和2（失败）。
                    Integer deleteStatus = getDeploymentResponse.getData().getDeployment().getStatus();
                    // 此处可以循环判断删除状态
                    if (deleteStatus == 1) {
                        System.out.println("成功删除文件。");
                        break;
                    } else {
                        System.out.println("正在删除文件");
                        Thread.sleep(1000L);
                    }
                }
            }
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;
    }

    /**
     * 查询文件
     * 参考文档：https://help.aliyun.com/document_detail/173954.html
     *
     * @param getFileDTO
     */
    public GetFileResponse.Data.File getFile(GetFileDTO getFileDTO) {
        try {
            GetFileRequest getFileRequest = new GetFileRequest();
            getFileRequest.setFileId(getFileDTO.getFileId());
            getFileRequest.setProjectId(getFileDTO.getProjectId());
            getFileRequest.setNodeId(getFileDTO.getNodeId());
            GetFileResponse getFileResponse = dataWorksOpenApiClient.createClient().getAcsResponse(getFileRequest);
            System.out.println(getFileResponse.getRequestId());
            GetFileResponse.Data.File file = getFileResponse.getData().getFile();
            System.out.println(file.getFileName());
            System.out.println(file.getFileType());
            System.out.println(file.getNodeId());
            System.out.println(file.getCreateUser());
            return file;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }

    /**
     * @param deployFileDTO
     * @return
     * @throws InterruptedException
     */
    public Boolean deployFile(DeployFileDTO deployFileDTO) throws InterruptedException {
        try {
            GetProjectRequest getProjectRequest = new GetProjectRequest();
            getProjectRequest.setProjectId(deployFileDTO.getProjectId());
            GetProjectResponse getProjectResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(getProjectRequest);
            // 标准模式有DEV和PROD，简单模式只有PROD
            Boolean standardMode = getProjectResponse.getData().getEnvTypes().size() == 2;
            if (standardMode) {
                SubmitFileRequest submitFileRequest = new SubmitFileRequest();
                submitFileRequest.setFileId(deployFileDTO.getFileId());
                submitFileRequest.setProjectId(deployFileDTO.getProjectId());
                SubmitFileResponse submitFileResponse = dataWorksOpenApiClient.createClient()
                        .getAcsResponse(submitFileRequest);
                System.out.println("submit file requestId:" + submitFileResponse.getRequestId());
                System.out.println("submit file deploymentId:" + submitFileResponse.getData());
                for (int i = 0; i < CYCLE_NUM; i++) {
                    GetDeploymentRequest getDeploymentRequest = new GetDeploymentRequest();
                    getDeploymentRequest.setProjectId(deployFileDTO.getProjectId());
                    getDeploymentRequest.setDeploymentId(submitFileResponse.getData());
                    GetDeploymentResponse getDeploymentResponse = dataWorksOpenApiClient.createClient()
                            .getAcsResponse(getDeploymentRequest);
                    // 发布包当前的状态，包括0（就绪）、1（成功）和2（失败）。
                    Integer deleteStatus = getDeploymentResponse.getData().getDeployment().getStatus();
                    // 此处可以循环判断删除状态
                    if (deleteStatus == 1) {
                        System.out.println("成功提交文件。");
                        break;
                    } else {
                        System.out.println("正在提交文件...");
                        Thread.sleep(1000L);
                    }
                }
            }
            DeployFileRequest deployFileRequest = new DeployFileRequest();
            deployFileRequest.setFileId(deployFileDTO.getFileId());
            deployFileRequest.setProjectId(deployFileDTO.getProjectId());
            DeployFileResponse deployFileResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(deployFileRequest);
            System.out.println("deploy file requestId:" + deployFileResponse.getRequestId());
            System.out.println("deploy file deploymentId:" + deployFileResponse.getData());
            for (int i = 0; i < CYCLE_NUM; i++) {
                GetDeploymentRequest getDeploymentRequest = new GetDeploymentRequest();
                getDeploymentRequest.setProjectId(deployFileDTO.getProjectId());
                getDeploymentRequest.setDeploymentId(deployFileResponse.getData());
                GetDeploymentResponse getDeploymentResponse = dataWorksOpenApiClient.createClient()
                        .getAcsResponse(getDeploymentRequest);
                // 发布包当前的状态，包括0（就绪）、1（成功）和2（失败）。
                Integer deleteStatus = getDeploymentResponse.getData().getDeployment().getStatus();
                // 此处可以循环判断删除状态
                if (deleteStatus == 1) {
                    System.out.println("成功发布文件。");
                    break;
                } else {
                    System.out.println("正在发布文件...");
                    Thread.sleep(1000L);
                }
            }
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;

    }

    public List<ListInstancesResponse.Data.Instance> runSmokeTest(RunSmokeTestDTO runSmokeTestDTO) {
        try {
            RunSmokeTestRequest runSmokeTestRequest = new RunSmokeTestRequest();
            runSmokeTestRequest.setBizdate(runSmokeTestDTO.getBizdate());
            runSmokeTestRequest.setNodeId(runSmokeTestDTO.getNodeId());
            runSmokeTestRequest.setNodeParams(runSmokeTestDTO.getNodeParams());
            runSmokeTestRequest.setName(runSmokeTestDTO.getName());
            runSmokeTestRequest.setProjectEnv(runSmokeTestDTO.getProjectEnv());
            RunSmokeTestResponse runSmokeTestResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(runSmokeTestRequest);
            System.out.println(runSmokeTestResponse.getRequestId());
            // DAGID
            System.out.println(runSmokeTestResponse.getData());

            ListInstancesRequest listInstancesRequest = new ListInstancesRequest();
            listInstancesRequest.setDagId(runSmokeTestResponse.getData());
            listInstancesRequest.setProjectId(runSmokeTestDTO.getProjectId());
            listInstancesRequest.setProjectEnv(runSmokeTestDTO.getProjectEnv());
            listInstancesRequest.setNodeId(runSmokeTestDTO.getNodeId());
            listInstancesRequest.setPageNumber(1);
            listInstancesRequest.setPageSize(10);
            ListInstancesResponse listInstancesResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(listInstancesRequest);
            System.out.println(listInstancesResponse.getRequestId());
            List<ListInstancesResponse.Data.Instance> instances = listInstancesResponse.getData().getInstances();
            if (CollectionUtils.isEmpty(instances)) {
                return null;
            }
            return instances;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }

    public InstanceDetail getInstanceLog(Long instanceId, String projectEnv) {
        try {
            GetInstanceLogRequest getInstanceLogRequest = new GetInstanceLogRequest();
            getInstanceLogRequest.setInstanceId(instanceId);
            getInstanceLogRequest.setProjectEnv(projectEnv);
            GetInstanceLogResponse getInstanceLogResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(getInstanceLogRequest);
            System.out.println(getInstanceLogResponse.getRequestId());

            GetInstanceRequest getInstanceRequest = new GetInstanceRequest();
            getInstanceRequest.setInstanceId(instanceId);
            getInstanceRequest.setProjectEnv(projectEnv);
            GetInstanceResponse getInstanceResponse = dataWorksOpenApiClient.createClient()
                    .getAcsResponse(getInstanceRequest);
            System.out.println(getInstanceResponse.getRequestId());
            System.out.println(getInstanceResponse.getData());

            InstanceDetail instanceDetail = new InstanceDetail();
            instanceDetail.setInstance(getInstanceResponse.getData());
            instanceDetail.setInstanceLog(getInstanceLogResponse.getData());
            return instanceDetail;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }
}
