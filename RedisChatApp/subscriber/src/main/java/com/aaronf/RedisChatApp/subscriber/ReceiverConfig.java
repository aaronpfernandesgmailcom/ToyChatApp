package com.aaronf.RedisChatApp.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class ReceiverConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverConfig.class);

    @Bean
    RedisConnectionFactory getConnectionFactory(final ReceiverProperties props) {
        final RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(props.getHostname(), props.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    StringRedisTemplate getTemplate(final RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }

    @Bean
    MessageListenerAdapter getListenerAdapter(final ReceiverService receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    RedisMessageListenerContainer getContainer(final RedisConnectionFactory connectionFactory,
                                               final MessageListenerAdapter listenerAdapter,
                                               final ReceiverProperties props) {
        final RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        redisMessageListenerContainer.addMessageListener(listenerAdapter, new PatternTopic(props.getChannel()));
        LOGGER.info("Registered receiver {} on channel {}.",
                props.getName(), props.getChannel());
        return redisMessageListenerContainer;
    }
}
