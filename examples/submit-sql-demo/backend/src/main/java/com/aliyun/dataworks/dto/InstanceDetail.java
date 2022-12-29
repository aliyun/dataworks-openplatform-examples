package com.aliyun.dataworks.dto;

import com.aliyuncs.dataworks_public.model.v20200518.GetInstanceResponse;

/**
 * @Author guangzhen.zk
 * @create 2022/12/5 下午3:44
 */
public class InstanceDetail {

    private GetInstanceResponse.Data instance;

    private String instanceLog;

    public GetInstanceResponse.Data getInstance() {
        return instance;
    }

    public void setInstance(GetInstanceResponse.Data instance) {
        this.instance = instance;
    }

    public String getInstanceLog() {
        return instanceLog;
    }

    public void setInstanceLog(String instanceLog) {
        this.instanceLog = instanceLog;
    }
}
