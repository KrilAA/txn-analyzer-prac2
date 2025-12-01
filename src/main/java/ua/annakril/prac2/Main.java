package ua.annakril.prac2;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Transaction> transactions =
                    TransactionCSVReader.readFromResource("transactions.csv");

            String report = TransactionReportGenerator.buildSummaryReport(transactions);
            System.out.println(report);
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}
