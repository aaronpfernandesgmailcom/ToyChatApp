package com.aaronf.RedisChatApp.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class ReceiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverService.class);
    private final ReceiverProperties properties;

    @Autowired
    ReceiverService(final ReceiverProperties properties) {
        this.properties = properties;
    }

    public void receiveMessage(final String message) {
        final String logMessage = String.format("Receiver[%s]: %s", properties.getName(), message);
        LOGGER.info(logMessage);
    }
}
