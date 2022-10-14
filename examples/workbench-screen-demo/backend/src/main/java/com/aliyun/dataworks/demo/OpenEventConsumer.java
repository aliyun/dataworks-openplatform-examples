package com.aliyun.dataworks.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dataworks.services.DataWorksOpenApiClient;
import com.aliyun.dataworks.services.EventService;
import com.aliyun.dataworks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dataworks demo
 */
@RestController
@RequestMapping("/eventBridge")
public class OpenEventConsumer {

  @Autowired(required = false)
  private DataWorksOpenApiClient dataWorksOpenApiClient;

  @Autowired
  private EventService eventService;


  /**
   * 接收eventBridge推送过来的消息
   *
   * @param messageContent
   */
  @PostMapping("/consume")
  public void consume(@RequestBody String messageContent) {
    JSONObject jsonObj = JSON.parseObject(messageContent);
    String eventCode = jsonObj.getString(Constants.EVENT_CODE_KEY);
    String messageId = jsonObj.getString(Constants.EVENT_MESSAGE_ID);
    JSONObject jsonObject = jsonObj.getJSONObject(Constants.EVENT_DATA_KEY);
    System.out.println(messageId + "," + eventCode);
    eventService.consumeEventBridge(messageId, eventCode, jsonObject);
  }
}
