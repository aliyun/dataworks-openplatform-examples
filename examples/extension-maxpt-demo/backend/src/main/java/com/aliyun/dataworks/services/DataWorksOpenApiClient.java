package com.aliyun.dataworks.services;

import com.aliyun.dataworks.config.ApiParamProperties;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dataworks demo
 */
@Service
public class DataWorksOpenApiClient {

    @Autowired
    private ApiParamProperties apiParamProperties;

    /**
     * 创建一个访问DataWorks OpenAPI的 ClientProfile
     * 需要区分应用所在网络环境，如果是VPC环境需要显示的指定endpoint
     * @return
     */
    public IAcsClient createClient(){
        IClientProfile profile = DefaultProfile.getProfile(apiParamProperties.getRegionId(), apiParamProperties.getAccessKeyId(), apiParamProperties.getAccessKeySecret());
        if(apiParamProperties.isVpcEnv()){
            DefaultProfile.addEndpoint(apiParamProperties.getRegionId(), apiParamProperties.getProduct(), apiParamProperties.getEndpoint());
        }
        return new DefaultAcsClient(profile);
    }

}
