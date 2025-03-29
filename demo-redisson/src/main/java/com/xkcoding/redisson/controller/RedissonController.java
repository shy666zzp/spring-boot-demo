package com.xkcoding.redisson.controller;

import com.xkcoding.redisson.service.RedissonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Redisson学习", description = "Redisson学习")
@RestController
@Slf4j
@RequestMapping("/redisson")
public class RedissonController {

    @Autowired
    private RedissonService redissonService;

    @ApiOperation("使用Redisson加锁")
    @PostMapping(value = "/lock")
    public Boolean lock() {
        return redissonService.lock();
    }
}
