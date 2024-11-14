import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String id, String pin, double balance) {
        this.id = id;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}
