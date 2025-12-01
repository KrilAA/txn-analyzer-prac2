package ua.annakril.prac2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class TransactionCSVReader {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Transaction> readFromResource(String resourceName) {
        List<Transaction> result = new ArrayList<>();

        ClassLoader cl = TransactionCSVReader.class.getClassLoader();
        try (InputStream is = cl.getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IllegalArgumentException("Ресурс не знайдено: " + resourceName);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                String line = br.readLine();
                if (line == null) {
                    return result;
                }

                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split(";");
                    if (parts.length < 6) {
                        parts = line.split(",");
                    }
                    if (parts.length < 6) {
                        continue;
                    }

                    long id = Long.parseLong(parts[0].trim());
                    LocalDate date = LocalDate.parse(parts[1].trim(), DATE_FMT);
                    BigDecimal amount = new BigDecimal(parts[2].trim());
                    String currency = parts[3].trim();
                    String category = parts[4].trim();
                    String description = parts[5].trim();

                    Transaction tx = new Transaction(id, date, amount, currency, category, description);
                    result.add(tx);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Помилка зчитування CSV: " + e.getMessage(), e);
        }

        return result;
    }
}
