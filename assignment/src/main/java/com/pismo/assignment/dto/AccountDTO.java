package com.pismo.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record AccountDTO(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("account_id")
        Long accountId,
        @JsonProperty("document_number")
        @NotBlank(message = "documentNumber is mandatory")
        @Size(min = 5, max = 30, message = "documentNumber must be between 5 and 30 characters")
        String documentNumber) {
}
