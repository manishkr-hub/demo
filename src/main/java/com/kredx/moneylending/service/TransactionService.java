package com.kredx.moneylending.service;

import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.entity.User;
import com.kredx.moneylending.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void lend(String lenderUsername, String borrowerUsername, BigDecimal amount, Instant date) {
        User lender = userService.getUserByUsername(lenderUsername);
        User borrower = userService.getUserByUsername(borrowerUsername);

        Transaction transaction = new Transaction();
        transaction.setLender(lender);
        transaction.setBorrower(borrower);
        transaction.setAmount(amount);
        transaction.setDate(date);

        transactionRepository.save(transaction);
    }

    public void borrow(String lenderUsername, String borrowerUsername, BigDecimal amount, Instant date) {
        User lender = userService.getUserByUsername(lenderUsername);
        User borrower = userService.getUserByUsername(borrowerUsername);

        Transaction transaction = new Transaction();
        transaction.setLender(lender);
        transaction.setBorrower(borrower);
        transaction.setAmount(amount);
        transaction.setDate(date);

        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(String username, String sortParameter, String sortOrder) {
        List<Transaction> transactions;
        User user = userService.getUserByUsername(username);

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

        return transactions;
    }
}

