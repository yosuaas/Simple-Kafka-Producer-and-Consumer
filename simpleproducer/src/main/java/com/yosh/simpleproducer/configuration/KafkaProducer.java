package com.yosh.simpleproducer.configuration;

import com.yosh.simpleproducer.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class KafkaProducer {

    @Value("${kafka.topic.book}")
    private String kafkaTopicBook;

    @Autowired
    private KafkaTemplate<String, BookDto> kafkaTemplate;

    public void produce(BookDto bookDto) {

        ListenableFuture<SendResult<String, BookDto>> future =
                kafkaTemplate.send(kafkaTopicBook, bookDto);

        future.addCallback(new ListenableFutureCallback<SendResult<String, BookDto>>() {

            @Override
            public void onSuccess(SendResult<String, BookDto> result) {
                log.info("Sent book with title [" + bookDto.getTitle() + "] with offset=["
                        + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.warn("Unable to send book with title [" + bookDto.getTitle() + "] due to : "
                        + ex.getMessage());
            }
        });
    }

}
