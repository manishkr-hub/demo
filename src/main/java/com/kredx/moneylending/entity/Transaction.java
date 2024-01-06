package com.kredx.moneylending.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private User lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    private BigDecimal amount;
    private Instant date;
}
