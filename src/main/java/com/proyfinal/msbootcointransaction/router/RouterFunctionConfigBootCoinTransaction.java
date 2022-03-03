package com.proyfinal.msbootcointransaction.router;

import com.proyfinal.msbootcointransaction.handler.BootCoinTransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterFunctionConfigBootCoinTransaction {

    @Bean
    public RouterFunction<ServerResponse> routes (BootCoinTransactionHandler handler) {
        return route(GET("/bootCoinTransaction"), handler::findAll)
                .andRoute(GET("/bootCoinTransaction/{id}"), handler::findById)
                .andRoute(POST("/bootCoinTransaction"),handler::create)
                .andRoute(PUT("/bootCoinTransaction/{id}/{seller}"),handler::acceptRequest);
    }
}
