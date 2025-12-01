package com.pismo.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "operation_type")
@Table(name = "operation_type")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OperationType {

    @Id
    @Column(name="operation_type_id")
    private Long operationTypeId;

    private String code;
    private String description;


}
