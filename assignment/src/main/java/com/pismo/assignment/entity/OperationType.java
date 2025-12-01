package com.pismo.assignment.entity;

import com.pismo.assignment.enums.OperationTypeEnum;
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


    @Enumerated(EnumType.STRING)
    private OperationTypeEnum code;
    private String description;


}
