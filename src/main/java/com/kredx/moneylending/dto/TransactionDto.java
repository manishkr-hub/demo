package com.kredx.moneylending.dto;

import com.kredx.moneylending.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String username;
    private BigDecimal amount;
    private Instant date;
}
