package com.proyfinal.msbootcointransaction.kafka;

import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BootCoinProducer {

    @Value("${custom.kafka.topic-name-bootcoin-account}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, BootCoinTransactionDto> bootCoinTranDtoKafkaTemplate;

    public void producer(BootCoinTransactionDto bootCoinAcceptMessageDto) {
        bootCoinTranDtoKafkaTemplate.send(topicName, bootCoinAcceptMessageDto);
    }
}
