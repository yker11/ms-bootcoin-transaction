package com.proyfinal.msbootcointransaction.kafka;

import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BootCoinConsumer {

    @KafkaListener(
            topics = "${custom.kafka.topic-name-transaction}",
            groupId = "${custom.kafka.group-id}",
            containerFactory = "bootCoinKafkaListenerContainerFactory")

    public void consumer(BootCoinTransactionDto bootCoinTransactionDto) {
        log.info("Consume Message {}", bootCoinTransactionDto);
    }
}
