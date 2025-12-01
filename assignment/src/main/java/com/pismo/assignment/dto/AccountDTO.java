package com.pismo.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record AccountDTO(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("account_id")
        Long accountId,
        @JsonProperty("document_number")
        Long documentNumber) {
}
