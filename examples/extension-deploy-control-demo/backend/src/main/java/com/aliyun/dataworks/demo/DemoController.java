package com.aliyun.dataworks.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dataworks demo
 */
@RestController
public class DemoController {

    /**
     *
     * @return
     */
    @GetMapping("/index")
    public String demo(){
        return "hello world!";
    }
}
