package tracker;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void saveTransactions(List<Transaction> transactions, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.getType() + "," + t.getCategory() + "," + t.getAmount() + "," + t.getDate());
                writer.newLine();
            }
        }
    }

    public static List<Transaction> loadTransactions(String filename) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String date = parts[3];
                    transactions.add(new Transaction(type, category, amount, date));
                }
            }
        }
        return transactions;
    }
}
