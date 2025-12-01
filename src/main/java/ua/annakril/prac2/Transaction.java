package ua.annakril.prac2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private long id;
    private LocalDate date;
    private BigDecimal amount;
    private String currency;
    private String category;
    private String description;

    public boolean isExpense() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) < 0;
    }
}
