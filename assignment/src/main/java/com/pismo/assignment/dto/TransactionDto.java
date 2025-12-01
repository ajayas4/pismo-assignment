package com.pismo.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

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
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", inclusive = false, message = "Amount must be greater than zero")
        @Digits(integer = 10, fraction = 2, message = "Amount must have max 10 digits and 2 decimals")
        BigDecimal amount,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("event_date")
        LocalDateTime eventDate) {
}
