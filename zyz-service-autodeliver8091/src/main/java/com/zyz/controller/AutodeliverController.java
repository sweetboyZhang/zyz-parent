package com.zyz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zyz.handler.SentinelHandler;
import com.zyz.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author zhangyuzhen
 * @Since JDK 1.8
 * @Version V1.0
 * @Date 2022/4/15 19:12
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {


    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    // @HystrixCommand
    @SentinelResource(value = "findResumeOpenStatus", blockHandlerClass = SentinelHandler.class, blockHandler = "handleException", fallbackClass = SentinelHandler.class, fallback = "handleJavaException")
    @GetMapping("/findResumeOpenStatus/{userId}")
    public String findResumeOpenStatus(@PathVariable("userId") Integer userId) {
        // int num = 1 / 0;
        return resumeServiceFeignClient.getResumeStatusByUserId(userId);
    }

}
