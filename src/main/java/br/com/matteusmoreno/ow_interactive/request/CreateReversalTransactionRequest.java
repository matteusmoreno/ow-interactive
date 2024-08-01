package br.com.matteusmoreno.ow_interactive.request;

import jakarta.validation.constraints.NotNull;

public record CreateReversalTransactionRequest(
        @NotNull(message = "Transaction id is required")
        Long transactionId,
        @NotNull(message = "User id is required")
        Long userId) {
}
