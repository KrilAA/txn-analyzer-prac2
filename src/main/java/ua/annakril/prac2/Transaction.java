package ua.annakril.prac2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private LocalDate date;
    private String account;
    private String category;
    private String description;
    private BigDecimal amount;
    private TransactionType type;

}
