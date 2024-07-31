package br.com.matteusmoreno.ow_interactive.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransactionRequest(
        @NotNull(message = "Transaction type is required")
        BigDecimal value,
        @NotNull(message = "User id is required")
        Long userId) {

}
