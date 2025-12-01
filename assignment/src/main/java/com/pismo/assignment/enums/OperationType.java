package com.pismo.assignment.enums;

import lombok.Getter;

public enum OperationType {


    CASH_PURCHASE(1l),
    INSTALLMENT_PURCHASE(2l),
    WITHDRAWAL(3l),
    PAYMENT(4l);
    @Getter
    private final Long id;

    OperationType(Long id){
        this.id=id;
    }
}
