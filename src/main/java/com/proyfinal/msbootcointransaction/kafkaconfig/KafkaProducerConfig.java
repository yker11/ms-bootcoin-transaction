package com.proyfinal.msbootcointransaction.kafkaconfig;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

    @Value("${custom.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, BootCoinTransactionDto> bootCoinTranDtoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, BootCoinTransactionDto> bootCoinKafkaTemplate() {

        KafkaTemplate<String, BootCoinTransactionDto> kafkaTemplate = new KafkaTemplate<>(bootCoinTranDtoProducerFactory());

        kafkaTemplate.setProducerListener(new ProducerListener<String, BootCoinTransactionDto>() {
            @Override
            public void onSuccess(ProducerRecord<String, BootCoinTransactionDto> producerRecord, RecordMetadata recordMetadata) {
                log.info("Success message: {}", producerRecord.value());
                ProducerListener.super.onSuccess(producerRecord, recordMetadata);
            }

            @Override
            public void onError(ProducerRecord<String, BootCoinTransactionDto> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                log.warn("Error message: {}", producerRecord.value());
                ProducerListener.super.onError(producerRecord, recordMetadata, exception);
            }
        });
        return kafkaTemplate;
    }
}
