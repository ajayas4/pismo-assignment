package com.pismo.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("transaction_id")
        Long transactionId,
        @JsonProperty("account_id")
        Long accountId,
        @JsonProperty("operation_type_id")
        Long transactionType,
        BigDecimal amount,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("event_date")
        LocalDateTime eventDate) {
}
