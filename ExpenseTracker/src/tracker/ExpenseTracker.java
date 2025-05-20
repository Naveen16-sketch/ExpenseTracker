package tracker;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
	
	    private static List<Transaction> transactions = new ArrayList<>();
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) throws Exception {
	        boolean running = true;
	        while (running) {
	            System.out.println("\n1. Add Transaction");
	            System.out.println("2. View Monthly Summary");
	            System.out.println("3. Save to File");
	            System.out.println("4. Load from File");
	            System.out.println("5. Exit");
	            System.out.print("Choose: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1 -> addTransaction();
	                case 2 -> viewMonthlySummary();
	                case 3 -> {
	                    try {
	                        FileHandler.saveTransactions(transactions, "transactions.txt");
	                        System.out.println("✅ Transactions saved to file!");
	                    } catch (IOException e) {
	                        System.out.println("❌ Failed to save: " + e.getMessage());
	                    }
	                }
	                case 4 -> {
	                    try {
	                        transactions = FileHandler.loadTransactions("transactions.txt");
	                        System.out.println("✅ Transactions loaded from file!");
	                    } catch (IOException e) {
	                        System.out.println("❌ Failed to load: " + e.getMessage());
	                    }
	                }

	                case 5 -> running = false;
	                default -> System.out.println("Invalid choice.");
	            }
	        }
	    }

	    private static void addTransaction() {
	        System.out.print("Type (INCOME/EXPENSE): ");
	        TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());

	        System.out.print("Enter category (e.g., Food, Rent, Travel, Salary, Business): ");
	        String category = scanner.nextLine();

	        System.out.print("Enter amount: ");
	        double amount = scanner.nextDouble();
	        scanner.nextLine();

	        LocalDate date = LocalDate.now();
	        transactions.add(new Transaction(type, category, amount, date));
	        System.out.println("Transaction added!");
	    }

	    private static void viewMonthlySummary() {
	        double income = 0, expense = 0;
	        Map<String, Double> categoryMap = new HashMap<>();

	        for (Transaction t : transactions) {
	            if (t.getDate().getMonth() == LocalDate.now().getMonth()) {
	                if (t.getType() == TransactionType.INCOME) income += t.getAmount();
	                else expense += t.getAmount();

	                categoryMap.put(t.getCategory(), categoryMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
	            }
	        }

	        System.out.println("\n--- Monthly Summary ---");
	        System.out.println("Total Income: ₹" + income);
	        System.out.println("Total Expense: ₹" + expense);
	        System.out.println("Balance: ₹" + (income - expense));

	        System.out.println("\nBreakdown by Category:");
	        categoryMap.forEach((cat, amt) -> System.out.println(cat + ": ₹" + amt));
	    }
	}



