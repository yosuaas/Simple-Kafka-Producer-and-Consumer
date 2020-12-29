package com.yosh.simpleconsumer.configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topic.book}", groupId = "BookConsumer")
    public void consume(String msg) {
        log.info("Message consumed : " + msg);
    }
}
