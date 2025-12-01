package com.pismo.assignment.constant;



import com.pismo.assignment.enums.OperationTypeEnum;

import java.util.List;

public class AppConstants {
    public static final List<String> allowedSortByInAccount=List.of("accountId","documentNumber");
    public static final List<String> allowedSortDirections=List.of("desc","asc");

    public static final List<String> allowedSortByInTransaction=List.of("transactionId","accountId","operationTypeId",
    "amount","eventDate");


}
