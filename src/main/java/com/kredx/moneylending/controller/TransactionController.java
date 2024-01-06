package com.kredx.moneylending.controller;

import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.service.TransactionService;
import com.kredx.moneylending.util.Contants;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(Contants.API_VERSION + Contants.TRANSACTION_ENDPOINT)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/lend")
    public ResponseEntity<String> lend(
            @RequestParam String lenderUsername,
            @RequestParam String borrowerUsername,
            @RequestParam BigDecimal amount,
            @RequestParam Instant date
    ) {
        try {
            transactionService.lend(lenderUsername, borrowerUsername, amount, date);
            return ResponseEntity.ok("Lending successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to lend: " + e.getMessage());
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(
            @RequestParam String borrowerUsername,
            @RequestParam String lenderUsername,
            @RequestParam BigDecimal amount,
            @RequestParam Instant date
    ) {
        try {
            transactionService.borrow(borrowerUsername, lenderUsername, amount, date);
            return ResponseEntity.ok("Borrowing successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to borrow: " + e.getMessage());
        }
    }

    @GetMapping("/user/{username}/report")
    public ResponseEntity<List<Transaction>> getReport(
            @PathParam("username") String username,
            @RequestParam String sortParameter,
            @RequestParam String sortOrder
    ) {
        try {
            List<Transaction> transactions = transactionService.getTransactions(username, sortParameter, sortOrder);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}


