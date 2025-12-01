package com.pismo.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transaction")
@Table(name="transaction")
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Column(name="transaction_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name="account_id")
    private Long accountId;


    @ManyToOne
    @JoinColumn(name="operation_type_id")
    private OperationType operationType;

    private BigDecimal amount;

    @Column(name="event_date")
    private LocalDateTime eventDate;


}
