public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double availableBalance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.availableBalance = 0.0; 
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getAvailableBalance() {
        return this.availableBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.availableBalance += amount;
            System.out.println("Deposit of Php " + String.format("%.2f", amount) + " successful.");
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
        } else if (amount > this.availableBalance) {
            System.out.println("Error: Insufficient funds. Your balance is Php " + String.format("%.2f", this.availableBalance));
        } else {
            this.availableBalance -= amount;
            System.out.println("Withdrawal of Php " + String.format("%.2f", amount) + " successful.");
        }
    }

    public void displayInfo() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Account Holder: " + this.accountHolderName);
        System.out.println("Available Balance: Php " + String.format("%.2f", this.availableBalance));
    }
}