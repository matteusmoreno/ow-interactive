package br.com.matteusmoreno.ow_interactive.response;

import br.com.matteusmoreno.ow_interactive.constant.TransactionType;
import br.com.matteusmoreno.ow_interactive.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDetailsResponse(
        Long id,
        BigDecimal value,
        TransactionType transactionType,
        String userName,
        BigDecimal balance,
        LocalDateTime createdAt) {

    public TransactionDetailsResponse(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getValue(),
                transaction.getTransactionType(),
                transaction.getUser().getName(),
                transaction.getUser().getBalance(),
                transaction.getCreatedAt()
        );
    }
}
