package ua.annakril.prac2;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    private TransactionReportGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String buildSummaryReport(List<Transaction> txs) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Звіт по транзакціях ===\n");
        sb.append("Кількість транзакцій: ").append(txs.size()).append("\n");
        sb.append("Загальна сума: ").append(TransactionAnalyzer.totalAmount(txs)).append("\n\n");

        Map<TransactionType, DoubleSummaryStatistics> stats =
                TransactionAnalyzer.statsByType(txs);

        for (var entry : stats.entrySet()) {
            TransactionType type = entry.getKey();
            DoubleSummaryStatistics s = entry.getValue();
            sb.append("Тип: ").append(type).append("\n");
            sb.append("  Кількість: ").append(s.getCount()).append("\n");
            sb.append("  Сума: ").append(s.getSum()).append("\n");
            sb.append("  Мінімум: ").append(s.getMin()).append("\n");
            sb.append("  Максимум: ").append(s.getMax()).append("\n\n");
        }

        return sb.toString();
    }
}
