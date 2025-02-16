package expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private int nextId = 1;

    public void addExpense(String category, double amount, String date) {
        Expense expense = new Expense(nextId++, category, amount, new Date());
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    public void deleteExpense(int id) {
        expenses.removeIf(expense -> expense.getId() == id);
        System.out.println("Expense deleted successfully!");
    }
}