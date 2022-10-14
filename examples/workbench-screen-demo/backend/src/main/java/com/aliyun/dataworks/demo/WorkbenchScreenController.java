package com.aliyun.dataworks.demo;

import com.aliyun.dataworks.dto.GetInstanceStatusStatisticDto;
import com.aliyun.dataworks.services.EventService;
import com.aliyun.dataworks.services.WorkbenchOpenApiService;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author dataworks demo
 */
@RestController
@RequestMapping("/screen")
public class WorkbenchScreenController {

  @Autowired
  private WorkbenchOpenApiService workbenchOpenApiService;

  @Autowired
  private EventService eventService;

  /**
   * 获取任务成功量的趋势图数据
   *
   * @param projectId
   * @return {@link ListSuccessInstanceAmountResponse.InstanceStatusTrend}
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/listSuccessInstanceAmount")
  public ListSuccessInstanceAmountResponse.InstanceStatusTrend listSuccessInstanceAmount(Long projectId) {
    return workbenchOpenApiService.listSuccessInstanceAmount(projectId);
  }

  /**
   * 获取项目空间实例总量
   *
   * @param beginDate
   * @param endDate
   * @param projectId
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/listInstanceAmount")
  public List<ListInstanceAmountResponse.IntanceCounts> listInstanceAmount(String beginDate, String endDate,
      Long projectId) {
    return workbenchOpenApiService.listInstanceAmount(beginDate, endDate, projectId);
  }

  /**
   * 获取近一个月运行时长topN排行
   *
   * @param projectId
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/topTenElapsedTimeInstance")
  public TopTenElapsedTimeInstanceResponse.InstanceConsumeTimeRank topTenElapsedTimeInstance(Long projectId) {
    return workbenchOpenApiService.topTenElapsedTimeInstance(projectId);
  }

  /**
   * 获取任务运行错误次数topN排行
   *
   * @param projectId
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/topTenErrorTimesInstance")
  public TopTenErrorTimesInstanceResponse.InstanceErrorRank topTenErrorTimesInstance(Long projectId) {
    return workbenchOpenApiService.topTenErrorTimesInstance(projectId);
  }

  /**
   * 获取项目空间下文件类型分布情况
   *
   * @param projectId
   * @param projectEnv
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/getFileTypeStatistic")
  public List<GetFileTypeStatisticResponse.ProgramTypeAndCount> getFileTypeStatistic(Long projectId,
      String projectEnv) {
    return workbenchOpenApiService.getFileTypeStatistic(projectId, projectEnv);
  }

  /**
   * 获取项目空间下实例状态分布
   *
   * @param getInstanceStatusStatisticDto
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/getInstanceStatusStatistic")
  public GetInstanceStatusStatisticResponse.StatusCount getInstanceStatusStatistic(
      GetInstanceStatusStatisticDto getInstanceStatusStatisticDto) {
    return workbenchOpenApiService.getInstanceStatusStatistic(getInstanceStatusStatisticDto);
  }

  /**
   * 获取节点和任务实时变更数据
   *
   * @return
   */
  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping("/getRealTimeNodeChanges")
  public Map<String, Integer> getRealTimeNodeChanges() {
    return eventService.getCurrentStatus();
  }

}
