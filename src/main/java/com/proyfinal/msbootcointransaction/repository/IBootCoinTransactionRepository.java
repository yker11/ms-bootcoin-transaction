package com.proyfinal.msbootcointransaction.repository;

import com.proyfinal.msbootcointransaction.model.BootCoinTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBootCoinTransactionRepository extends ReactiveMongoRepository<BootCoinTransaction, String> {
}

