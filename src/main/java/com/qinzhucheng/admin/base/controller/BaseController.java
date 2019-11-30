package com.qinzhucheng.admin.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("/")
    public String hello() {
        logger.info("测试日志");
        return "hello";
    }
}
