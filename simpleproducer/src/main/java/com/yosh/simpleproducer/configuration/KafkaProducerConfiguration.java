package com.yosh.simpleproducer.configuration;

import java.util.HashMap;
import java.util.Map;
import com.yosh.simpleproducer.dto.BookDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBroker;

    @Bean
    public Map<String, Object> producerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();

        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return configurations;
    }

    @Bean
    public ProducerFactory<String, BookDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    @Bean
    public KafkaTemplate<String, BookDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
