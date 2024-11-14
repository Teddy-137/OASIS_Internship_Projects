import java.util.Scanner;

public class ATM {
    private User currentUser;
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM System.");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        currentUser = bank.authenticateUser(userId, pin);

        if (currentUser != null) {
            showMenu();
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's ID: ");
                    String recipientId = scanner.next();
                    System.out.print("Enter amount to transfer: $");
                    double transferAmount = scanner.nextDouble();
                    transfer(transferAmount, recipientId);
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    quit();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction.toString());
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addTransaction(new Transaction("Withdrawal", -amount));
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        currentUser.setBalance(currentUser.getBalance() + amount);
        currentUser.addTransaction(new Transaction("Deposit", amount));
        System.out.println("Deposit successful!");

    }

    public void transfer(double amount, String receiverId) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        User receiver = bank.getUserById(receiverId);
        if (receiver != null && currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            currentUser.addTransaction(new Transaction("Transfer to " + receiverId, -amount));
            receiver.addTransaction(new Transaction("Transfer from " + currentUser.getId(), amount));
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Invalid transfer or insufficient balance.");
        }
    }

    public void quit() {
        System.out.println("Thank you for using our ATM. Goodbye!");
        System.exit(0);
    }
}