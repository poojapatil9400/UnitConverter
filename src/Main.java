import converter.*;
import expense.Expense;
import expense.ExpenseManager;
import database.DatabaseHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler.initialize();
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();

        while (true) {
            System.out.println("1. Convert Units");
            System.out.println("2. Manage Expenses");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1. Length");
                    System.out.println("2. Weight");
                    System.out.println("3. Temperature");
                    int unitChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter value: ");
                    double value = scanner.nextDouble();
                    scanner.nextLine();

                    String[] units = getUnitsForChoice(unitChoice);
                    if (units == null) {
                        System.out.println("Invalid choice!");
                        break;
                    }

                    System.out.println("Available units: ");
                    for (int i = 0; i < units.length; i++) {
                        System.out.println((i + 1) + ". " + units[i]);
                    }

                    System.out.print("Enter from unit (number): ");
                    int fromUnitChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter to unit (number): ");
                    int toUnitChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (fromUnitChoice < 1 || fromUnitChoice > units.length || toUnitChoice < 1 || toUnitChoice > units.length) {
                        System.out.println("Invalid unit choice!");
                        break;
                    }

                    String fromUnit = units[fromUnitChoice - 1];
                    String toUnit = units[toUnitChoice - 1];

                    double result = 0;
                    switch (unitChoice) {
                        case 1:
                            result = LengthConverter.convert(value, fromUnit, toUnit);
                            break;
                        case 2:
                            result = WeightConverter.convert(value, fromUnit, toUnit);
                            break;
                        case 3:
                            result = TemperatureConverter.convert(value, fromUnit, toUnit);
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    System.out.println("Result: " + result);
                    break;
                case 2:
                    System.out.println("1. Add Expense");
                    System.out.println("2. View Expenses");
                    System.out.println("3. Delete Expense");
                    int expenseChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (expenseChoice) {
                        case 1:
                            System.out.print("Enter category: ");
                            String category = scanner.nextLine();
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Enter date (yyyy-MM-dd): ");
                            String date = scanner.nextLine();
                            expenseManager.addExpense(category, amount, date);
                            break;
                        case 2:
                            expenseManager.viewExpenses();
                            break;
                        case 3:
                            System.out.print("Enter expense ID to delete: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            expenseManager.deleteExpense(id);
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
            DatabaseHandler.printAllExpenses();
        }
    }
   

    private static String[] getUnitsForChoice(int unitChoice) {
        switch (unitChoice) {
            case 1:
                return new String[]{"km", "m", "cm", "mm", "inch", "foot"};
            case 2:
                return new String[]{"kg", "g", "mg", "pound", "ounce"};
            case 3:
                return new String[]{"c", "f", "k"};
            default:
                return null;
        }
    }
}