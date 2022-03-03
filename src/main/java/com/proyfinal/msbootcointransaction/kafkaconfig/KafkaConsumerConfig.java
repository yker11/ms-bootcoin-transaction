package com.proyfinal.msbootcointransaction.kafkaconfig;

import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaConsumerConfig {

    @Value("${custom.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${custom.kafka.group-id}")
    private String groupId;

    public ConsumerFactory<String, BootCoinTransactionDto> bootCoinTranDtoConsumerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
                new JsonDeserializer<>(BootCoinTransactionDto.class));
    }

    public ConcurrentKafkaListenerContainerFactory<String, BootCoinTransactionDto> bootCoinTranDtoKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BootCoinTransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bootCoinTranDtoConsumerFactory());
        return factory;
    }
}
