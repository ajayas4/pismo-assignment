package com.pismo.assignment.repository;

import com.pismo.assignment.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    Page<Transaction> findAll(Pageable pageableO);
    Page<Transaction>  findByAccountId(Long accountId,Pageable pageable);
}
