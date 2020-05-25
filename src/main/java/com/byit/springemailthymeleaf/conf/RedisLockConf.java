package com.byit.springemailthymeleaf.conf;

import com.byit.springemailthymeleaf.components.DistributedLocker;
import com.byit.springemailthymeleaf.components.impl.RedissonDistributedLocker;
import com.byit.springemailthymeleaf.conf.properties.RedisLockProperties;
import com.byit.springemailthymeleaf.utils.RedisLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangfu
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedisLockProperties.class)
public class RedisLockConf {
    private final RedisLockProperties redisLockProperties;

    public RedisLockConf(RedisLockProperties redisLockProperties) {
        this.redisLockProperties = redisLockProperties;
    }

    @Bean
    @ConditionalOnProperty(name="redisson.master-name")
    public RedissonClient redissonSentinel(){
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers()
                .addSentinelAddress(redisLockProperties.getSentinelAddresses())
                .setMasterName(redisLockProperties.getMasterName())
                .setTimeout(redisLockProperties.getTimeout())
                .setMasterConnectionPoolSize(redisLockProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redisLockProperties.getSlaveConnectionPoolSize())
                .setDatabase(redisLockProperties.getDatabase());
        if(StringUtils.isNotBlank(redisLockProperties.getPassword())) {
            sentinelServersConfig.setPassword(redisLockProperties.getPassword());
        }
        return Redisson.create(config);
    }


    /**
     * 单机模式自动装配
     * @return
     */
    @Bean
    @ConditionalOnProperty(name="redisson.address")
    public RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redisLockProperties.getAddress())
                .setTimeout(redisLockProperties.getTimeout())
                .setConnectionPoolSize(redisLockProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redisLockProperties.getConnectionMinimumIdleSize())
                .setDatabase(redisLockProperties.getDatabase());

        if(StringUtils.isNotBlank(redisLockProperties.getPassword())) {
            serverConfig.setPassword(redisLockProperties.getPassword());
        }

        return Redisson.create(config);
    }


    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     * @return
     */
    @Bean
    public DistributedLocker distributedLocker(RedissonClient redissonSingle) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker(redissonSingle);
        RedisLockUtil.setDistributedLocker(locker);
        return locker;
    }

}
