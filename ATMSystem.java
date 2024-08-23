import java.util.Scanner;

public class ATMSystem {

    public static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }
            this.balance = initialBalance;
        }

        public String deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                return "Successfully deposited $" + amount + ". New balance: $" + balance;
            } else {
                return "Deposit failed. Amount must be greater than zero.";
            }
        }

        public String withdraw(double amount) {
            if (amount <= 0) {
                return "Withdrawal failed. Amount must be greater than zero.";
            }
            if (amount > balance) {
                return "Insufficient funds. Unable to withdraw $" + amount + ". Current balance: $" + balance;
            }
            balance -= amount;
            return "Successfully withdrew $" + amount + ". New balance: $" + balance;
        }

        public String checkBalance() {
            return "Your current balance is $" + balance;
        }
    }

    public static class ATM {
        private final BankAccount account;
        private final Scanner scanner;

        public ATM(BankAccount account) {
            this.account = account;
            this.scanner = new Scanner(System.in);
        }

        private void displayMenu() {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");
        }

        public void start() {
            boolean sessionActive = true;
            while (sessionActive) {
                displayMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        handleWithdraw();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        System.out.println(account.checkBalance());
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        sessionActive = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }

        private void handleWithdraw() {
            System.out.print("Enter the amount to withdraw: $");
            double amount = scanner.nextDouble();
            System.out.println(account.withdraw(amount));
        }

        private void handleDeposit() {
            System.out.print("Enter the amount to deposit: $");
            double amount = scanner.nextDouble();
            System.out.println(account.deposit(amount));
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        ATM atm = new ATM(account);
        atm.start();
    }
}
