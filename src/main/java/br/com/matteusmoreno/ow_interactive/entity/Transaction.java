package br.com.matteusmoreno.ow_interactive.entity;

import br.com.matteusmoreno.ow_interactive.constant.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    private User user;
    private Boolean reversal;
    private LocalDateTime createdAt;
}
