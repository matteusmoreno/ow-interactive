package br.com.matteusmoreno.ow_interactive.service;

import br.com.matteusmoreno.ow_interactive.constant.TransactionType;
import br.com.matteusmoreno.ow_interactive.entity.Transaction;
import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.exception.*;
import br.com.matteusmoreno.ow_interactive.mapper.TransactionMapper;
import br.com.matteusmoreno.ow_interactive.repository.TransactionRepository;
import br.com.matteusmoreno.ow_interactive.repository.UserRepository;
import br.com.matteusmoreno.ow_interactive.request.CreateReversalTransactionRequest;
import br.com.matteusmoreno.ow_interactive.request.CreateTransactionRequest;
import br.com.matteusmoreno.ow_interactive.response.TransactionDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public Transaction creditTransaction(CreateTransactionRequest request) {

        User user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);

        Transaction transaction = transactionMapper.creditTransactionBuilder(request, user);
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

        Transaction transaction = transactionMapper.debitTransactionBuilder(request, user);
        transactionRepository.save(transaction);

        user.setBalance(user.getBalance().subtract(request.value()));
        user.getTransactions().add(transaction);
        userRepository.save(user);

        return transaction;
    }

    @Transactional
    public Transaction reversalTransaction(CreateReversalTransactionRequest request) {

        User user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);
        Transaction previousTransaction = transactionRepository.findById(request.transactionId()).orElseThrow(TransactionNotFoundException::new);

        if (previousTransaction.getUser() != user) {
            throw new TransactionOwnershipException();
        }

        if (previousTransaction.getReversal()) {
            throw new TransactionAlreadyReversedException();
        }

        previousTransaction.setReversal(true);
        transactionRepository.save(previousTransaction);
        
        Transaction transaction = transactionMapper.reversalTransactionBuilder(request, user, previousTransaction);
        transactionRepository.save(transaction);

        verifyTransactionType(transaction, user);

        user.getTransactions().add(transaction);
        userRepository.save(user);

        return transaction;
    }

    public Page<TransactionDetailsResponse> findAll(Long userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException();
        return transactionRepository.findAllByUserId(userId, pageable).map(TransactionDetailsResponse::new);
    }



    private static void verifyTransactionType(Transaction transaction, User user) {
        if (transaction.getTransactionType() == TransactionType.CREDIT) {
            user.setBalance(user.getBalance().subtract(transaction.getValue()));
        } else {
            user.setBalance(user.getBalance().add(transaction.getValue()));
        }
    }
}
