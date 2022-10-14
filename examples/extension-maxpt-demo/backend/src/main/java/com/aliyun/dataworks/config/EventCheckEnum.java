package com.aliyun.dataworks.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventCheckEnum {

    OK("OK","检查成功"),
    FAIL("FAIL","检查失败");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
