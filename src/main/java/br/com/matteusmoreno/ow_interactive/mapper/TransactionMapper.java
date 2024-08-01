package br.com.matteusmoreno.ow_interactive.mapper;

import br.com.matteusmoreno.ow_interactive.constant.TransactionType;
import br.com.matteusmoreno.ow_interactive.entity.Transaction;
import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.request.CreateReversalTransactionRequest;
import br.com.matteusmoreno.ow_interactive.request.CreateTransactionRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public Transaction creditTransactionBuilder(CreateTransactionRequest request, User user) {

        return Transaction.builder()
                .value(request.value())
                .transactionType(TransactionType.CREDIT)
                .user(user)
                .createdAt(LocalDateTime.now())
                .reversal(false)
                .build();
    }

    public Transaction debitTransactionBuilder(CreateTransactionRequest request, User user) {

        return Transaction.builder()
                .value(request.value())
                .transactionType(TransactionType.DEBIT)
                .user(user)
                .createdAt(LocalDateTime.now())
                .reversal(false)
                .build();
    }

    public Transaction reversalTransactionBuilder(CreateReversalTransactionRequest request, User user, Transaction previousTransaction) {

        return Transaction.builder()
                .value(previousTransaction.getValue())
                .transactionType(TransactionType.REVERSAL)
                .user(user)
                .createdAt(LocalDateTime.now())
                .reversal(false)
                .build();
    }
}
