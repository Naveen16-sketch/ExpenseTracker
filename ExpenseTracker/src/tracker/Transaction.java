package tracker;

import java.time.LocalDate;

public class Transaction {
    private TransactionType type;
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(TransactionType type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(String type, String category, double amount, String date) {
        this.type = TransactionType.valueOf(type.toUpperCase());
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.parse(date);
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
