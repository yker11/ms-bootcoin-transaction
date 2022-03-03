package com.proyfinal.msbootcointransaction.service;

import com.proyfinal.msbootcointransaction.model.BootCoinTransaction;
import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootCoinTransactionService {

    Flux<BootCoinTransactionDto> findAll();

    Mono<BootCoinTransactionDto> findById(String id);

    Mono<BootCoinTransactionDto> save(BootCoinTransaction bootCoinTransaction);

    Mono<BootCoinTransactionDto> getBootCoinTransactionDto(BootCoinTransaction bootCoinTransaction);

    Mono<BootCoinTransaction> getBootCoinTransaction(BootCoinTransactionDto bootCoinTransactionDto);

    BootCoinTransactionDto getBootTransactionDto(BootCoinTransaction bootCoinTransaction);

    BootCoinTransaction getBootTransaction(BootCoinTransactionDto bootCoinTransactionDto);

}
