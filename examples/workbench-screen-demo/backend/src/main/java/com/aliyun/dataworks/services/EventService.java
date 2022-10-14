package com.aliyun.dataworks.services;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dataworks.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author guangzhen.zk
 * @create 2022/8/25 上午11:18
 */
@Service
public class EventService {

  /**
   * 大屏实时数据，DEMO代码存放到内存中，在实际生产环境应该持久化存储
   */
  private Map<String, AtomicInteger> screenMap = new ConcurrentHashMap();

  /**
   * @param messageId
   * @param eventCode
   * @param messageBody
   * @return
   */
  public boolean consumeEventBridge(String messageId, String eventCode, JSONObject messageBody) {
    if (!isWorkbenchEvent(eventCode)) {
      return false;
    }
    AtomicInteger currentStatus = screenMap.get(eventCode);
    if (currentStatus == null) {
      addZero(eventCode);
      currentStatus = screenMap.get(eventCode);
    }
    //DEMO代码存放到内存中，在实际生产环境应该持久化存储
    currentStatus.incrementAndGet();
    System.out.println("messageId=" + messageId);
    System.out.println("messageBody=" + messageBody.toJSONString());
    return true;
  }

  /**
   * @return
   */
  public Map<String, Integer> getCurrentStatus() {
    AtomicInteger number = screenMap.get("nodeChangeCreated");
    if (number == null) {
      screenMap.put("nodeChangeCreated", new AtomicInteger(12));
      screenMap.put("nodeChangeUpdated", new AtomicInteger(10));
      screenMap.put("nodeChangeDeleted", new AtomicInteger(5));
      screenMap.put("instanceSuccessCount", new AtomicInteger(8412));
      screenMap.put("instanceFailureCount", new AtomicInteger(415));
      screenMap.put("instanceWaitTimeCount", new AtomicInteger(2333));
      screenMap.put("instanceWaitResourceCount", new AtomicInteger(98));
      screenMap.put("instanceRunningCount", new AtomicInteger(2007));
    } else {
      screenMap.get("nodeChangeCreated").addAndGet((int)(Math.random() * 10));
      screenMap.get("nodeChangeUpdated").addAndGet((int)(Math.random() * 10));
      screenMap.get("nodeChangeDeleted").addAndGet((int)(Math.random() * 10));
      screenMap.get("instanceSuccessCount").addAndGet((int)(Math.random() * 10));
      screenMap.get("instanceFailureCount").addAndGet((int)(Math.random() * 10));
      screenMap.get("instanceWaitTimeCount").addAndGet((int)(Math.random() * 10));
      screenMap.get("instanceWaitResourceCount").addAndGet((int)(Math.random() * 10));
      screenMap.get("instanceRunningCount").addAndGet((int)(Math.random() * 10));

    }
    Map<String, Integer> result = new HashMap<>();
    screenMap.keySet().forEach(key -> result.put(key, screenMap.get(key).intValue()));
    return result;
  }

  private boolean isWorkbenchEvent(String eventCode) {
    return Constants.INSTANCE_STATUS_CHANGES.equals(eventCode)
      || Constants.NODE_CHANGE_CREATED.equals(eventCode)
      || Constants.NODE_CHANGE_DELETED.equals(eventCode)
      || Constants.NODE_CHANGE_CREATED.equals(eventCode);
  }

  private synchronized void addZero(String eventCode) {
    AtomicInteger currentStatus = screenMap.get(eventCode);
    if (currentStatus == null) {
      screenMap.put(eventCode, new AtomicInteger(0));
    }
  }
}
