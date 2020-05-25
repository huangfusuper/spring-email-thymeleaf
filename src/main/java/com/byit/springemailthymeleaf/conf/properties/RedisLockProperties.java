package com.byit.springemailthymeleaf.conf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangfu
 */
@Data
@ConfigurationProperties(prefix = "redisson")
public class RedisLockProperties {
    private int timeout = 60000;

    private String address;

    private String password;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize=10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;

    private int database = 2;
}
