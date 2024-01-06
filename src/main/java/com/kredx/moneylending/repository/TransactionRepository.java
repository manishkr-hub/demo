package com.kredx.moneylending.repository;

import com.kredx.moneylending.entity.Transaction;
import com.kredx.moneylending.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLenderOrBorrower(User lender, User borrower);

    List<Transaction> findByLenderOrBorrowerOrderByAmount(User lender, User borrower);

    List<Transaction> findByLenderOrBorrowerOrderByDate(User lender, User borrower);
}
