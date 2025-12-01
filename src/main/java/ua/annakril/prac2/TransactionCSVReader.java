package ua.annakril.prac2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    private TransactionCSVReader() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Transaction> readFromResource(String resourceName) throws IOException {
        List<Transaction> result = new ArrayList<>();

        ClassLoader cl = TransactionCSVReader.class.getClassLoader();
        try (InputStream is = cl.getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourceName);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length < 7) continue;

                    Transaction tx = new Transaction(
                            parts[0].trim(),
                            LocalDate.parse(parts[1].trim()),
                            parts[2].trim(),
                            TransactionType.valueOf(parts[3].trim().toUpperCase()),
                            Double.parseDouble(parts[4].trim()),
                            parts[5].trim(),
                            parts[6].trim()
                    );
                    result.add(tx);
                }
            }
        }

        return result;
    }
}
