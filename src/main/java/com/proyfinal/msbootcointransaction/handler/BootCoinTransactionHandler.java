package com.proyfinal.msbootcointransaction.handler;

import com.proyfinal.msbootcointransaction.kafka.BootCoinProducer;
import com.proyfinal.msbootcointransaction.model.BootCoinTransaction;
import com.proyfinal.msbootcointransaction.model.dto.BootCoinTransactionDto;
import com.proyfinal.msbootcointransaction.service.IBootCoinTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BootCoinTransactionHandler {

    @Autowired
    private IBootCoinTransactionService serviceBCT;

    @Autowired
    private BootCoinProducer producer;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceBCT.findAll(), BootCoinTransactionDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest){

        String id = serverRequest.pathVariable("id");

        return serviceBCT.findById(id)
                .flatMap(fby -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(fby))
                .onErrorResume(e -> Mono.just("Error de ID " + e.getMessage())
                        .flatMap(fby -> ServerResponse.status(HttpStatus.FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(fby)));
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest){

        Mono<BootCoinTransaction> bootCoinTransaction = serverRequest.bodyToMono(BootCoinTransaction.class);

        return bootCoinTransaction.flatMap(c -> {
                    ServerResponse.status(HttpStatus.FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(c);
                    return serviceBCT.save(c)
                            .flatMap(s -> ServerResponse
                            .status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(s));

        });
    }

    public Mono<ServerResponse> acceptRequest(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        String bId = serverRequest.pathVariable("buyerId");

        serviceBCT.findById(id).flatMap(ar -> {
                ar.setBuyId(bId);
                ar.setStatus(BootCoinTransactionDto.Status.SUCCESS);
                producer.producer(ar);
                return serviceBCT.save(serviceBCT.getBootTransaction(ar));
                }).subscribe();

        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
