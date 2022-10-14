package com.aliyun.dataworks.test;

import com.aliyun.dataworks.config.ApiParamProperties;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author guangzhen.zk
 * @create 2022/8/2 下午5:21
 */
public class ApiParamPropertiesTest extends DemoTester {

    @Autowired
    private ApiParamProperties apiParamProperties;

    @Test
    public void checkProperties(){
        Assert.assertNotNull(apiParamProperties);
        Assert.assertEquals(apiParamProperties.getRegionId(),"cn-shanghai");
        Assert.assertEquals(apiParamProperties.getEndpoint(),"dataworks-vpc.cn-shanghai.aliyuncs.com");
        Assert.assertEquals(apiParamProperties.getProduct(),"dataworks-public");
        Assert.assertEquals(apiParamProperties.getAccessKeyId(),"akId");
        Assert.assertEquals(apiParamProperties.getAccessKeySecret(),"akSecret");
        Assert.assertEquals(apiParamProperties.isVpcEnv(),true);
    }
}
