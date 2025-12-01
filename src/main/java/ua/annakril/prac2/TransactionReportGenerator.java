package ua.annakril.prac2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class TransactionReportGenerator {

    private static final BigDecimal DEFAULT_UNIT = BigDecimal.valueOf(1000);

    public static String buildCategoryReport(Map<String, BigDecimal> totalsByCategory) {
        return buildCategoryReport(totalsByCategory, DEFAULT_UNIT);
    }

    public static String buildCategoryReport(Map<String, BigDecimal> totalsByCategory,
                                             BigDecimal oneStarAmount) {
        StringBuilder sb = new StringBuilder("Витрати по категоріях:\n");
        for (Map.Entry<String, BigDecimal> e : totalsByCategory.entrySet()) {
            String cat = e.getKey();
            BigDecimal total = e.getValue();
            int stars = total.abs().divide(oneStarAmount, 0, BigDecimal.ROUND_HALF_UP).intValue();
            sb.append(String.format("%-15s | %s (%.2f)\n",
                    cat,
                    "*".repeat(Math.max(1, stars)),
                    total));
        }
        return sb.toString();
    }

    public static String buildMonthlyReport(Map<YearMonth, BigDecimal> totalsByMonth) {
        StringBuilder sb = new StringBuilder("Витрати по місяцях:\n");
        for (Map.Entry<YearMonth, BigDecimal> e : totalsByMonth.entrySet()) {
            YearMonth ym = e.getKey();
            BigDecimal total = e.getValue();
            int stars = total.abs().divide(DEFAULT_UNIT, 0, BigDecimal.ROUND_HALF_UP).intValue();
            sb.append(String.format("%s | %s (%.2f)\n",
                    ym,
                    "*".repeat(Math.max(1, stars)),
                    total));
        }
        return sb.toString();
    }
}
