package com.shibro.nativeproducts.utils.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * RedissonFactoryBean
 * <p>
 * Usage:
 * <pre>
 *      <bean id="redissonClient" class="RedissonFactoryBean">
 *          <property name="configLocation" value="${redis.redisson.location}" />
 *      </bean>
 *  </pre>
 * <p>
 * Created by chengyao on 2016/8/10.
 */
public class RedissonFactoryBean implements FactoryBean<RedissonClient>, InitializingBean {

    private RedissonClient redisson;

    private Config config;

    private String configLocation;

    public RedissonFactoryBean() {
    }

    public RedissonFactoryBean(RedissonClient redisson, Config config) {
        this.redisson = redisson;
        this.config = config;
    }

    public RedissonFactoryBean(RedissonClient redisson, String configLocation) {
        this.redisson = redisson;
        this.configLocation = configLocation;
    }

    @Override
    public RedissonClient getObject() throws Exception {
        return redisson;
    }

    @Override
    public Class<?> getObjectType() {
        return RedissonClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (configLocation == null) {
            return;
        }

        Resource resource = new ClassPathResource(configLocation);
        try {
            this.config = Config.fromJSON(resource.getInputStream());
        } catch (IOException e) {
            // try to read yaml
            try {
                this.config = Config.fromYAML(resource.getInputStream());
            } catch (IOException e1) {
                throw new BeanDefinitionStoreException(
                        "Could not parse redisson configuration at [" + configLocation + "]", e1);
            }
        }
        redisson = Redisson.create(config);
    }

}
