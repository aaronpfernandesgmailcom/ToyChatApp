package com.aaronf.redisChatApp.publisher;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.aaronf.redis-chat-app.sender")
public class PublisherProperties {
    @Getter
    @Setter
    private String hostname;
    @Getter
    @Setter
    private int port;
    @Getter
    @Setter
    private String channel;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int numberOfMessages;
    @Getter
    @Setter
    private long delayMs;
}
