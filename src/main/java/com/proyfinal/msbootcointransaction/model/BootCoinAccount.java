package com.proyfinal.msbootcointransaction.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BootCoinAccount {

    @Id
    private String id;
    private DocumentType documentType;
    private String documentNumber;
    private int phoneNumber;
    private String email;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Double amount;

    public enum DocumentType {
        DNI,
        CEX,
        PASSPORT
    }
}
