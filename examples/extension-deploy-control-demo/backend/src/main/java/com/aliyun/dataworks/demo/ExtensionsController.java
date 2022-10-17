package com.aliyun.dataworks.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dataworks.config.Constants;
import com.aliyun.dataworks.config.EventCheckEnum;
import com.aliyun.dataworks.config.ExtensionParamProperties;
import com.aliyun.dataworks.services.DataWorksOpenApiClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dataworks_public.model.v20200518.UpdateIDEEventResultRequest;
import com.aliyuncs.dataworks_public.model.v20200518.UpdateIDEEventResultResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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
        if(Constants.COMMIT_FILE_EVENT_CODE.equals(eventCode)){
            //初始化client
            IAcsClient client = dataWorksOpenApiClient.createClient();
            //获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY_MM_DD);
            String now = sdf.format(System.currentTimeMillis());
            //获取2022年节假日及其封网管控日期
            List<String> holidayList = Arrays.asList(extensionParamProperties.getHolidayList().split(","));
            //判断当前时间是否是管控日
            boolean isExists = holidayList.stream().anyMatch(day -> day.equals(now));
            //回调方法
            UpdateIDEEventResultRequest updateIDEEventResultRequest = new UpdateIDEEventResultRequest();
            updateIDEEventResultRequest.setMessageId(jsonObj.getString("id"));
            updateIDEEventResultRequest.setExtensionCode(extensionParamProperties.getExtensionCode());
            //是管控日 检查不通过
            if(isExists){
                updateIDEEventResultRequest.setCheckResult(EventCheckEnum.FAIL.getCode());
                updateIDEEventResultRequest.setCheckResultTip("管控日不可作提交文件操作");
            }else{
                //非管控日 检查通过
                updateIDEEventResultRequest.setCheckResult(EventCheckEnum.OK.getCode());
                updateIDEEventResultRequest.setCheckResultTip(EventCheckEnum.OK.getName());
            }
            try {
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
