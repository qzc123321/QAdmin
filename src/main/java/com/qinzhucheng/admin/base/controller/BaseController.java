package com.qinzhucheng.admin.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ApiOperation("hello")
    @RequestMapping("/")
    public String hello() {
        logger.info("测试日志");
        return "hello";
    }
}
