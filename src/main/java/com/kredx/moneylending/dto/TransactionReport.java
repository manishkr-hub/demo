package com.kredx.moneylending.dto;

import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReport {

    private String username;
    private List<TransactionDto> borrows;
    private List<TransactionDto> lends;

}
