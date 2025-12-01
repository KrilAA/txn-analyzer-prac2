package ua.annakril.prac2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Transaction> txns = TransactionCSVReader.readFromResource("transactions.csv");

        System.out.println("Усього транзакцій: " + txns.size());

        System.out.println("\nТоп 10 витрат:");
        TransactionAnalyzer.findTopExpenses(txns, 10)
                .forEach(System.out::println);

        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 30);
        TransactionAnalyzer.maxExpenseInPeriod(txns, from, to)
                .ifPresent(t -> System.out.println("\nМаксимальна витрата за період: " + t));
        TransactionAnalyzer.minExpenseInPeriod(txns, from, to)
                .ifPresent(t -> System.out.println("Мінімальна витрата за період: " + t));

        Map<String, java.math.BigDecimal> byCat = TransactionAnalyzer.totalsByCategory(txns);
        System.out.println("\n" + TransactionReportGenerator.buildCategoryReport(byCat));

        Map<java.time.YearMonth, java.math.BigDecimal> byMonth = TransactionAnalyzer.totalsByMonth(txns);
        System.out.println(TransactionReportGenerator.buildMonthlyReport(byMonth));
    }
}
