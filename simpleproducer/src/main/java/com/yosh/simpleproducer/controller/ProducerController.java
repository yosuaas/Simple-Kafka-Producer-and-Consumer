package com.yosh.simpleproducer.controller;

import com.yosh.simpleproducer.configuration.KafkaProducer;
import com.yosh.simpleproducer.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produce")
public class ProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/book")
    public String produce(@RequestBody BookDto bookDto) {

        kafkaProducer.produce(bookDto);

        return "Success";
    }
}
