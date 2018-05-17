package com.aaronf.redisChatApp.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class PublisherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);
    private final PublisherProperties properties;
    private StringRedisTemplate redisTemplate;

    @Autowired
    PublisherService(final PublisherProperties properties,
                     final StringRedisTemplate redisTemplate) {
        this.properties = properties;
        this.redisTemplate = redisTemplate;
        this.start();
    }

    private void start() {
        LOGGER.info("Giving a receiver a chance to start.");
        try {
            Thread.sleep(properties.getDelayMs());
        } catch (InterruptedException e) {
            LOGGER.error("Issue!", e);
        }

        LOGGER.info("Publishing {} messages to {} with {} ms between each message.",
                properties.getNumberOfMessages(),
                properties.getChannel(),
                properties.getDelayMs());

        for (int i = 0; i < properties.getNumberOfMessages(); i++) {
            publish(i);
        }

        LOGGER.info("Giving a receiver a chance to catch those publishes.");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            LOGGER.error("Issue!", e);
        }
    }

    private void publish(final int n) {
        final String message = String.format("Hello %d from %s!",
                n, properties.getName());
        redisTemplate.convertAndSend(properties.getChannel(), message);
        LOGGER.info("Sender [{}] sent: {}", properties.getName(), message);

        try {
            Thread.sleep(properties.getDelayMs());
        } catch (final InterruptedException e) {
            LOGGER.error("Exception when sleeping.", e);
        }
    }

}
