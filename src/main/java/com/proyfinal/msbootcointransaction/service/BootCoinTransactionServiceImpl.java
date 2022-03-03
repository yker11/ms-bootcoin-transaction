package com.proyfinal.msbootcointransaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyfinal.msbootcointransaction.model.BootCoinTransaction;
import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import com.proyfinal.msbootcointransaction.repository.IBootCoinTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinTransactionServiceImpl implements IBootCoinTransactionService {

    @Autowired
    private IBootCoinTransactionRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Flux<BootCoinTransactionDto> findAll() {
        return repository.findAll()
                .flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public Mono<BootCoinTransactionDto> findById(String id) {
        return repository.findById(id)
                .flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public Mono<BootCoinTransactionDto> save(BootCoinTransaction bootCoinTransaction) {
        return repository.save(bootCoinTransaction)
                .flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public Mono<BootCoinTransactionDto> getBootCoinTransactionDto(BootCoinTransaction bootCoinTransaction) {
        return Mono.just(objectMapper.convertValue(bootCoinTransaction, BootCoinTransactionDto.class));
    }

    @Override
    public Mono<BootCoinTransaction> getBootCoinTransaction(BootCoinTransactionDto bootCoinTransactionDto) {
        return Mono.just(objectMapper.convertValue(bootCoinTransactionDto, BootCoinTransaction.class));
    }
}
