package com.aliyun.dataworks.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dataworks.config.Constants;
import com.aliyun.dataworks.config.EventCheckEnum;
import com.aliyun.dataworks.config.ExtensionParamProperties;
import com.aliyun.dataworks.services.DataWorksOpenApiClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dataworks demo
 */
@RestController
@RequestMapping("/extensions")
public class ExtensionsController {

    @Autowired(required = false)
    private DataWorksOpenApiClient dataWorksOpenApiClient;

    @Autowired
    private ExtensionParamProperties extensionParamProperties;

    /**
     * 接收eventBridge推送过来的消息
     * @param jsonParam
     */
    @PostMapping("/consumer")
    public void consumerEventBridge(@RequestBody String jsonParam){
        JSONObject jsonObj = JSON.parseObject(jsonParam);
        String eventCode = jsonObj.getString(Constants.EVENT_CODE_FILED);
        if(Constants.COMMIT_FILE_EVENT_CODE.equals(eventCode) || Constants.DEPLOY_FILE_EVENT_CODE.equals(eventCode)){
            //初始化client
            IAcsClient client = dataWorksOpenApiClient.createClient();
            try {
                //当前事件参数信息
                String messageId = jsonObj.getString("id");
                JSONObject data = jsonObj.getObject("data", JSONObject.class);
                Long projectId = data.getLong("projectId");

                //初始化事件回调
                UpdateIDEEventResultRequest updateIDEEventResultRequest = new UpdateIDEEventResultRequest();
                updateIDEEventResultRequest.setMessageId(messageId);
                updateIDEEventResultRequest.setExtensionCode(extensionParamProperties.getExtensionCode());

                //查询触发扩展点事件时的扩展点数据快照
                GetIDEEventDetailRequest getIDEEventDetailRequest = new GetIDEEventDetailRequest();
                getIDEEventDetailRequest.setMessageId(messageId);
                getIDEEventDetailRequest.setProjectId(projectId);
                GetIDEEventDetailResponse getIDEEventDetailResponse = client.getAcsResponse(getIDEEventDetailRequest);
                String content = getIDEEventDetailResponse.getEventDetail().getCommittedFile().getContent();

                //判断代码是否包含限制函数
                if(content.contains(Constants.CHECK_CODE)){
                    //获取扩展程序选项配置在项目空间下的配置
                    GetOptionValueForProjectRequest getOptionValueForProjectRequest = new GetOptionValueForProjectRequest();
                    getOptionValueForProjectRequest.setProjectId(String.valueOf(projectId));
                    getOptionValueForProjectRequest.setExtensionCode(extensionParamProperties.getExtensionCode());
                    GetOptionValueForProjectResponse getOptionValueForProjectResponse = client.getAcsResponse(getOptionValueForProjectRequest);
                    JSONObject jsonObject = JSON.parseObject(getOptionValueForProjectResponse.getOptionValue());
                    //注意 这里需根据在DataWorks上实际设置格式来填写
                    String checkStatus = jsonObject.getString("checkStatus");
                    updateIDEEventResultRequest.setCheckResult(checkStatus);
                    updateIDEEventResultRequest.setCheckResultTip("代码中存在限制函数");
                }else{//成功回调
                    updateIDEEventResultRequest.setCheckResult(EventCheckEnum.OK.getCode());
                    updateIDEEventResultRequest.setCheckResultTip(EventCheckEnum.OK.getName());
                }
                //回调DataWorks
                UpdateIDEEventResultResponse acsResponse = client.getAcsResponse(updateIDEEventResultRequest);
                //请求的唯一标识，用于后续错误排查使用
                System.out.println("acsResponse:" + acsResponse.getRequestId());
            } catch (ClientException e) {
                //请求的唯一标识，用于后续错误排查使用
                System.out.println("RequestId:" + e.getRequestId());
                //错误状态码
                System.out.println("ErrCode:" + e.getErrCode());
                //错误描述信息
                System.out.println("ErrMsg:" + e.getErrMsg());
            }
        }else{
            System.out.println("未能过滤其他事件,请检查配置步骤");
        }
    }
}
