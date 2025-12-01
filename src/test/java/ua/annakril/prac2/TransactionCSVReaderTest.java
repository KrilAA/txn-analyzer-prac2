package ua.annakril.prac2;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionCSVReaderTest {

    @Test
    public void shouldReadAllTransactionsFromCsv() {
        List<Transaction> txns = TransactionCSVReader.readFromResource("transactions.csv");
        assertEquals(10, txns.size());

        Transaction first = txns.get(0);
        assertEquals(1L, first.getId());
        assertEquals(LocalDate.of(2025, 9, 1), first.getDate());
        assertEquals("UAH", first.getCurrency());
    }
}
