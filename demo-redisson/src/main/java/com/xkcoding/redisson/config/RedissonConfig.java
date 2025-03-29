package com.xkcoding.redisson.config;

import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 其他导入保持不变

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}") // 改为注入 host
    private String host;
    @Value("${spring.redis.port}") // 改为注入 port
    private String port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private String database;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        // 构建 Redis 单机地址（需包含协议头）
        String address = "redis://" + host + ":" + port;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(address);

        // 设置密码和数据库
        if (StringUtils.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }
        if (StringUtils.isNotBlank(database)) {
            serverConfig.setDatabase(Integer.parseInt(database));
        }

        return Redisson.create(config);
    }
}