package ua.annakril.prac2;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionAnalyzerTest {

    @Test
    public void topExpensesShouldContainOnlyExpenses() {
        List<Transaction> txns = TransactionCSVReader.readFromResource("transactions.csv");

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(txns, 5);

        assertFalse(top.isEmpty());
        assertTrue(top.stream().allMatch(Transaction::isExpense));
    }

    @Test
    public void maxAndMinExpenseInPeriodShouldBePresent() {
        List<Transaction> txns = TransactionCSVReader.readFromResource("transactions.csv");

        var from = LocalDate.of(2025, 9, 1);
        var to = LocalDate.of(2025, 9, 30);

        assertTrue(TransactionAnalyzer.maxExpenseInPeriod(txns, from, to).isPresent());
        assertTrue(TransactionAnalyzer.minExpenseInPeriod(txns, from, to).isPresent());
    }
}
