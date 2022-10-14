package com.aliyun.dataworks.services;

import com.aliyun.dataworks.dto.GetInstanceStatusStatisticDto;
import com.aliyun.dataworks.enums.DagType;
import com.aliyun.dataworks.enums.SchedulerType;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.utils.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dataworks demo
 */
@Service
public class WorkbenchOpenApiService {

  @Autowired
  private DataWorksOpenApiClient dataWorksOpenApiClient;

  /**
   * 帮助文档 https://help.aliyun.com/document_detail/212622.html
   * 提供projectId入参，DataWorks OpenAPI会做相应的权限校验
   *
   * @param projectId
   * @return {@link ListSuccessInstanceAmountResponse.InstanceStatusTrend}
   */
  public ListSuccessInstanceAmountResponse.InstanceStatusTrend listSuccessInstanceAmount(Long projectId) {
    Assert.assertNotNull(projectId);
    ListSuccessInstanceAmountRequest request = new ListSuccessInstanceAmountRequest();
    // 提供projectId入参，DataWorks OpenAPI会做相应的权限校验
    request.setProjectId(projectId);
    try {
      ListSuccessInstanceAmountResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getInstanceStatusTrend();
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
   * 帮忙文档 https://help.aliyun.com/document_detail/212602.html
   *
   * @param beginDate
   * @param endDate
   * @param projectId
   * @return {@link ListInstanceAmountResponse.IntanceCounts}
   */
  public List<ListInstanceAmountResponse.IntanceCounts> listInstanceAmount(String beginDate, String endDate,
      Long projectId) {
    Assert.assertNotNull(beginDate);
    Assert.assertNotNull(endDate);
    Assert.assertNotNull(projectId);
    ListInstanceAmountRequest request = new ListInstanceAmountRequest();
    // 开始业务日期，精确到天。该参数需要配置为yyyy-MM-dd'T'HH:mm:ssZ的UTC格式。
    request.setBeginDate(beginDate);
    // 结束业务日期，精确到天。该参数需要配置为yyyy-MM-dd'T'HH:mm:ssZ的UTC格式。
    request.setEndDate(endDate);
    // 提供projectId入参，DataWorks OpenAPI会做相应的权限校验
    request.setProjectId(projectId);
    try {
      ListInstanceAmountResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getInstanceCounts();
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
   * 帮助文档 https://help.aliyun.com/document_detail/212579.html
   *
   * @param projectId
   * @return {@link TopTenElapsedTimeInstanceResponse.InstanceConsumeTimeRank}
   */
  public TopTenElapsedTimeInstanceResponse.InstanceConsumeTimeRank topTenElapsedTimeInstance(Long projectId) {
    Assert.assertNotNull(projectId);
    TopTenElapsedTimeInstanceRequest request = new TopTenElapsedTimeInstanceRequest();
    // 提供projectId入参，DataWorks OpenAPI会做相应的权限校验
    request.setProjectId(projectId);
    try {
      TopTenElapsedTimeInstanceResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getInstanceConsumeTimeRank();
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
   * 帮助文档 https://help.aliyun.com/document_detail/212587.html
   *
   * @param projectId
   * @return {@link TopTenErrorTimesInstanceResponse.InstanceErrorRank}
   */
  public TopTenErrorTimesInstanceResponse.InstanceErrorRank topTenErrorTimesInstance(Long projectId) {
    TopTenErrorTimesInstanceRequest request = new TopTenErrorTimesInstanceRequest();
    request.setProjectId(projectId);
    try {
      TopTenErrorTimesInstanceResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getInstanceErrorRank();
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
   * 帮助文档 https://help.aliyun.com/document_detail/212717.html
   *
   * @param projectId
   * @param projectEnv
   * @return Array of {@link GetFileTypeStatisticResponse.ProgramTypeAndCount}
   */
  public List<GetFileTypeStatisticResponse.ProgramTypeAndCount> getFileTypeStatistic(Long projectId,
      String projectEnv) {
    Assert.assertNotNull(projectId);
    projectEnv = StringUtils.isEmpty(projectEnv) ? "PROD" : projectEnv;
    GetFileTypeStatisticRequest request = new GetFileTypeStatisticRequest();
    request.setProjectId(projectId);
    request.setProjectEnv(projectEnv);
    try {
      GetFileTypeStatisticResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getProgramTypeAndCounts();
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
   * 帮助文档 https://help.aliyun.com/document_detail/212637.html
   *
   * @param getInstanceStatusStatisticDto
   * @return
   */
  public GetInstanceStatusStatisticResponse.StatusCount getInstanceStatusStatistic(
      GetInstanceStatusStatisticDto getInstanceStatusStatisticDto) {
    Assert.assertNotNull(getInstanceStatusStatisticDto.getBizDate());
    Assert.assertNotNull(getInstanceStatusStatisticDto.getProjectId());
    String projectEnv = StringUtils.isEmpty(getInstanceStatusStatisticDto.getProjectEnv())
        ? "PROD"
        : getInstanceStatusStatisticDto.getProjectEnv();
    GetInstanceStatusStatisticRequest request = new GetInstanceStatusStatisticRequest();
    request.setBizDate(getInstanceStatusStatisticDto.getBizDate());
    if (getInstanceStatusStatisticDto.getDagType() != null) {
      DagType dagType = DagType.valueOf(getInstanceStatusStatisticDto.getDagType());
      if (dagType != null) {
        request.setDagType(dagType.name());
      }
    }

    request.setProjectEnv(projectEnv);
    if (getInstanceStatusStatisticDto.getSchedulerType() != null) {
      SchedulerType schedulerType = SchedulerType.valueOf(getInstanceStatusStatisticDto.getSchedulerType());
      if (schedulerType != null) {
        request.setSchedulerType(schedulerType.name());
      }
    }
    request.setProjectId(getInstanceStatusStatisticDto.getProjectId());
    try {
      GetInstanceStatusStatisticResponse response = dataWorksOpenApiClient.createClient().getAcsResponse(request);
      // 打印出requestId，方便排查问题
      System.out.println(response.getRequestId());
      return response.getStatusCount();
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
