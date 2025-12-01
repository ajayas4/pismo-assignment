package com.pismo.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="account")
@Table(name="account")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long accountId;

    @Column(name="document_number")
    private Long documentNumber;




}
