package com.aliyun.dataworks.test;

import com.aliyun.dataworks.config.ExtensionParamProperties;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author guangzhen.zk
 * @create 2022/8/2 下午5:08
 */
public class ExtensionParamPropertiesTest extends DemoTester{

    @Autowired
    private ExtensionParamProperties extensionParamProperties;

    @Test
    public void checkProperties(){
        Assert.assertNotNull(extensionParamProperties);
        Assert.assertEquals(extensionParamProperties.getExtensionCode(),"demo");
    }
}
