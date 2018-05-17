package com.aaronf.RedisChatApp.subscriber;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "com.aaronf.redis-chat-app.receiver")
public class ReceiverProperties {
    @Getter
    @Setter
    private String hostname;
    @Getter
    @Setter
    private int port;
    @Getter @Setter
    private String channel;
    @Getter @Setter
    private String name;
}
