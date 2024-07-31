package br.com.matteusmoreno.ow_interactive.repository;

import br.com.matteusmoreno.ow_interactive.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
