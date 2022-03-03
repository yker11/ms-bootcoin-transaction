package com.proyfinal.msbootcointransaction.model.dto;

import com.proyfinal.msbootcointransaction.model.BootCoinTransaction;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BootCoinTransactionDto {

    @Id
    private String id;
    private String recepId;
    private String buyId;
    private TypeTransaction typeTransaction;
    private Double amountSoles;
    private Double amountBootCoins;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Status status;

    public enum TypeTransaction {
        BUY,
        TRANSFER
    }

    public enum Status {
        SUCCESS,
        PENDING,
        REJECT
    }
}
