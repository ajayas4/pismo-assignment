package com.pismo.assignment.constant;



import com.pismo.assignment.enums.OperationType;

import java.util.List;

public class AppConstants {
    public static final List<Long> debitTransactionTypes=List.of(OperationType.CASH_PURCHASE.getId(),OperationType.WITHDRAWAL.getId(),
            OperationType.INSTALLMENT_PURCHASE.getId());
    public static final List<String> allowedSortByInAccount=List.of("accountId","documentNumber");
    public static final List<String> allowedSortDirections=List.of("desc","asc");

    public static final List<String> allowedSortByInTransaction=List.of("transactionId","accountId","operationTypeId",
    "amount","eventDate");


}
