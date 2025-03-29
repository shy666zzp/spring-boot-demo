package com.xkcoding.redisson.service.impl;

import com.xkcoding.redisson.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedissonServiceImpl implements RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Boolean lock() {
        RLock lock = redissonClient.getLock("该锁的Key");
        try {
            //加锁
            lock.lock();
            //执行业务代码
            return true;
        }catch (Exception e){
            return false;
        }finally {
            //解锁
            lock.unlock();
        }
    }
}
