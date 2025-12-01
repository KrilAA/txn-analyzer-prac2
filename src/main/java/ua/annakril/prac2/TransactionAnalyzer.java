package ua.annakril.prac2;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private TransactionAnalyzer() {
        throw new IllegalStateException("Utility class");
    }

    public static double totalAmount(List<Transaction> txs) {
        return txs.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double totalForType(List<Transaction> txs, TransactionType type) {
        return txs.stream()
                .filter(t -> t.getType() == type)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static Map<TransactionType, DoubleSummaryStatistics> statsByType(List<Transaction> txs) {
        return txs.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getType,
                        Collectors.summarizingDouble(Transaction::getAmount)
                ));
    }

    public static List<Transaction> filterByDateRange(List<Transaction> txs,
                                                      LocalDate from,
                                                      LocalDate to) {
        return txs.stream()
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .collect(Collectors.toList());
    }
}
