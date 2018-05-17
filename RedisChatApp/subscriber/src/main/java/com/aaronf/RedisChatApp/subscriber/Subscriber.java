package com.aaronf.RedisChatApp.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Subscriber {
    private static Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    public static void main(String[] args) {
        SpringApplication.run(Subscriber.class, args);
        stayAlive();
    }

    private static void stayAlive() {
        LOGGER.info("Staying alive.");
        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            LOGGER.error("Problem while keeping alive.", e);
        }
    }
}
