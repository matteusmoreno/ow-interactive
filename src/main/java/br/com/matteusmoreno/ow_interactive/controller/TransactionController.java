package br.com.matteusmoreno.ow_interactive.controller;

import br.com.matteusmoreno.ow_interactive.entity.Transaction;
import br.com.matteusmoreno.ow_interactive.request.CreateReversalTransactionRequest;
import br.com.matteusmoreno.ow_interactive.request.CreateTransactionRequest;
import br.com.matteusmoreno.ow_interactive.response.TransactionDetailsResponse;
import br.com.matteusmoreno.ow_interactive.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public ResponseEntity<TransactionDetailsResponse> creditTransaction(@RequestBody @Valid CreateTransactionRequest request, UriComponentsBuilder uriBuilder) {
        Transaction transaction = transactionService.creditTransaction(request);
        URI uri = uriBuilder.path("/transactions/credit/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionDetailsResponse(transaction));
    }

    @PostMapping("/debit")
    public ResponseEntity<TransactionDetailsResponse> debitTransaction(@RequestBody @Valid CreateTransactionRequest request, UriComponentsBuilder uriBuilder) {
        Transaction transaction = transactionService.debitTransaction(request);
        URI uri = uriBuilder.path("/transactions/debit/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionDetailsResponse(transaction));
    }

    @PostMapping("/reversal")
    public ResponseEntity<TransactionDetailsResponse> reversalTransaction(@RequestBody @Valid CreateReversalTransactionRequest request, UriComponentsBuilder uriBuilder) {
        Transaction transaction = transactionService.reversalTransaction(request);
        URI uri = uriBuilder.path("/transactions/reversal/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionDetailsResponse(transaction));
    }

    @GetMapping("/find-all-by-user/{id}")
    public ResponseEntity<Page<TransactionDetailsResponse>> findAll(@PathVariable Long id, @PageableDefault(sort = "createdAt", size = 10) Pageable pageable) {
        var page = transactionService.findAll(id, pageable);

        return ResponseEntity.ok(page);
    }
}
