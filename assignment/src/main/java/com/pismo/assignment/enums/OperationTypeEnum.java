package com.pismo.assignment.enums;

public enum OperationTypeEnum {


    CASH_PURCHASE(true),
    INSTALLMENT_PURCHASE(true),
    WITHDRAWAL(true),
    PAYMENT(false);

    private final boolean debit;
    OperationTypeEnum(boolean debit){


        this.debit=debit;
    }

    public boolean isDebit() {
        return debit;
    }


}
