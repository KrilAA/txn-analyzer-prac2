package ua.annakril.prac2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class TransactionAnalyzer {

    public static List<Transaction> findTopExpenses(List<Transaction> txns, int limit) {
        return txns.stream()
                .filter(Transaction::isExpense)
                .sorted(Comparator.comparing(t -> t.getAmount().abs(), Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static List<Transaction> inPeriod(List<Transaction> txns,
                                             LocalDate from,
                                             LocalDate to) {
        return txns.stream()
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .collect(Collectors.toList());
    }

    public static Optional<Transaction> maxExpenseInPeriod(List<Transaction> txns,
                                                           LocalDate from,
                                                           LocalDate to) {
        return inPeriod(txns, from, to).stream()
                .filter(Transaction::isExpense)
                .max(Comparator.comparing(t -> t.getAmount().abs()));
    }

    public static Optional<Transaction> minExpenseInPeriod(List<Transaction> txns,
                                                           LocalDate from,
                                                           LocalDate to) {
        return inPeriod(txns, from, to).stream()
                .filter(Transaction::isExpense)
                .min(Comparator.comparing(t -> t.getAmount().abs()));
    }

    public static Map<String, BigDecimal> totalsByCategory(List<Transaction> txns) {
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (Transaction t : txns) {
            String cat = t.getCategory();
            result.merge(cat, t.getAmount(), BigDecimal::add);
        }
        return result;
    }

    public static Map<YearMonth, BigDecimal> totalsByMonth(List<Transaction> txns) {
        Map<YearMonth, BigDecimal> result = new TreeMap<>();
        for (Transaction t : txns) {
            YearMonth ym = YearMonth.from(t.getDate());
            result.merge(ym, t.getAmount(), BigDecimal::add);
        }
        return result;
    }
}
