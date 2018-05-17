package com.aaronf.redisChatApp.publisher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class PublisherConfig {
    @Bean
    RedisConnectionFactory getConnectionFactory(final PublisherProperties props) {
        final RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(props.getHostname(), props.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    StringRedisTemplate getTemplate(final RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }
}
