package com.kredx.moneylending.controller;

import com.kredx.moneylending.dto.TransactionReport;
import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.service.TransactionService;
import com.kredx.moneylending.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseEntity<String> lend(@RequestParam String lenderUsername,
                                       @RequestParam String borrowerUsername,
                                       @RequestParam BigDecimal amount) {
        try {
            transactionService.lend(lenderUsername, borrowerUsername, amount);
            return ResponseEntity.ok("Lending successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to lend: " + e.getMessage());
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestParam String borrowerUsername,
                                         @RequestParam String lenderUsername,
                                         @RequestParam BigDecimal amount) {
        try {
            transactionService.borrow(borrowerUsername, lenderUsername, amount);
            return ResponseEntity.ok("Borrowing successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to borrow: " + e.getMessage());
        }
    }

    @GetMapping("/report")
    public ResponseEntity<?> getReport(@RequestParam String username,
                                                       @RequestParam(required = false) String sortParameter,
                                                       @RequestParam(required = false) String sortOrder) {
        try {
            TransactionReport report = transactionService.getTransactions(username, sortParameter, sortOrder);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to fetch report: " + e.getMessage());
        }
    }
}


