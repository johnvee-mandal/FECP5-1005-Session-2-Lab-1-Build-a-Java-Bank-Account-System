import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter choice (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    depositToAccount();
                    break;
                case 5:
                    withdrawFromAccount();
                    break;
                case 6:
                    System.out.println("\nThank you for using the Bank System. Goodbye! 👋");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
            if (choice != 6) {
                askToReturnToMenu();
            }

        } while (choice != 6);

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("\n=== Bank Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Check Balance");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        if (findAccount(accNum) != null) {
            System.out.println("Error: An account with this number already exists.");
            return;
        }

        System.out.print("Enter Holder Name: ");
        String name = scanner.nextLine();

        BankAccount newAccount = new BankAccount(accNum, name);

        System.out.print("Initial deposit? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter initial deposit amount: ");
            double initialDeposit = scanner.nextDouble();
            scanner.nextLine(); 
            if (initialDeposit < 0) {
                System.out.println("Error: Deposit amount cannot be negative. Account created with Php 0 balance.");
            } else {
                newAccount.deposit(initialDeposit);
            }
        }

        accounts.add(newAccount);
        System.out.println("Account created successfully!");
    }

    private static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts have been created yet.");
            return;
        }
        System.out.println("\n--- All Bank Accounts ---");
        for (BankAccount acc : accounts) {
            acc.displayInfo();
        }
    }

    private static void checkBalance() {
        BankAccount acc = findAccountByPrompt();
        if (acc != null) {
            System.out.println("The available balance is: Php" + String.format("%.2f", acc.getAvailableBalance()));
        }
    }

    private static void depositToAccount() {
        BankAccount acc = findAccountByPrompt();
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 
            acc.deposit(amount);
        }
    }

    private static void withdrawFromAccount() {
        BankAccount acc = findAccountByPrompt();
        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 
            acc.withdraw(amount);
        }
    }

    private static BankAccount findAccountByPrompt() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts exist.");
            return null;
        }
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Error: Account not found.");
        }
        return account;
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    private static void askToReturnToMenu() {
        System.out.print("\nWould you like to return to the menu? (yes/no): ");
        String response = scanner.nextLine();
        if (!response.equalsIgnoreCase("yes")) {
            System.out.println("\nThank you for using the Bank System. Goodbye! 👋");
            System.exit(0);
        }
    }
}