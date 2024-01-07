package com.kredx.moneylending.service;

import com.kredx.moneylending.dto.TransactionDto;
import com.kredx.moneylending.dto.TransactionReport;
import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.entity.User;
import com.kredx.moneylending.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void lend(String lenderUsername, String borrowerUsername, BigDecimal amount) {
        User lender = userService.getUserByUsername(lenderUsername);
        User borrower = userService.getUserByUsername(borrowerUsername);

        Transaction transaction = new Transaction();
        transaction.setLender(lender);
        transaction.setBorrower(borrower);
        transaction.setAmount(amount);
        transaction.setDate(Instant.now());

        transactionRepository.save(transaction);
    }

    public void borrow(String lenderUsername, String borrowerUsername, BigDecimal amount) {
        User lender = userService.getUserByUsername(lenderUsername);
        User borrower = userService.getUserByUsername(borrowerUsername);

        Transaction transaction = new Transaction();
        transaction.setLender(lender);
        transaction.setBorrower(borrower);
        transaction.setAmount(amount);
        transaction.setDate(Instant.now());

        transactionRepository.save(transaction);
    }

    public TransactionReport getTransactions(String username, String sortParameter, String sortOrder) {

        User user = userService.getUserByUsername(username);
        List<Transaction> transactions;

        if ("amount".equalsIgnoreCase(sortParameter)) {
            transactions = transactionRepository.findByLenderOrBorrowerOrderByAmount(user, user);
        } else if ("date".equalsIgnoreCase(sortParameter)) {
            transactions = transactionRepository.findByLenderOrBorrowerOrderByDate(user, user);
        } else {
            transactions = transactionRepository.findByLenderOrBorrower(user, user);
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            Collections.reverse(transactions);
        }

        return generateReports(username, transactions);
    }

    public TransactionReport generateReports(String username, List<Transaction> transactions) {
        return TransactionReport.builder()
                .username(username)
                .borrows(transactions.stream()
                        .filter(transaction -> transaction.getBorrower().getUsername().equals(username))
                        .map(transaction -> new TransactionDto(transaction.getLender().getUsername(), transaction.getAmount(), transaction.getDate()))
                        .collect(Collectors.toList()))
                .lends(transactions.stream()
                        .filter(transaction -> transaction.getLender().getUsername().equals(username))
                        .map(transaction -> new TransactionDto(transaction.getBorrower().getUsername(), transaction.getAmount(), transaction.getDate()))
                        .collect(Collectors.toList()))
                .build();
    }
}

