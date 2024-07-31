package br.com.matteusmoreno.ow_interactive.service;

import br.com.matteusmoreno.ow_interactive.constant.TransactionType;
import br.com.matteusmoreno.ow_interactive.entity.Transaction;
import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.exception.InsufficientFundsException;
import br.com.matteusmoreno.ow_interactive.exception.UserNotFoundException;
import br.com.matteusmoreno.ow_interactive.repository.TransactionRepository;
import br.com.matteusmoreno.ow_interactive.repository.UserRepository;
import br.com.matteusmoreno.ow_interactive.request.CreateTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Transaction creditTransaction(CreateTransactionRequest request) {

        User user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);
        Transaction transaction = new Transaction();

        transaction.setValue(request.value());
        transaction.setTransactionType(TransactionType.CREDIT);
        transaction.setUser(user);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        user.setBalance(user.getBalance().add(request.value()));
        user.getTransactions().add(transaction);
        userRepository.save(user);

        return transaction;
    }

    @Transactional
    public Transaction debitTransaction(CreateTransactionRequest request) {

        User user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);

        if (user.getBalance().compareTo(request.value()) < 0) {
            throw new InsufficientFundsException();
        }

        Transaction transaction = new Transaction();

        transaction.setValue(request.value());
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setUser(user);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        user.setBalance(user.getBalance().subtract(request.value()));
        user.getTransactions().add(transaction);
        userRepository.save(user);

        return transaction;
    }
}
