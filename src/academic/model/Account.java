package academic.model;
import java.util.*;

public class Account {
    private String name, username, accountType;
    private double balance = 0.0;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String name, String username, String type) {
        this.name = name;
        this.username = username;
        this.accountType = type;
    }

    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction t) {
        this.transactions.add(t);
        this.balance += t.getNetAmount();
    }

    public String toString() {
        return username + "|" + name + "|" + accountType + "|" + balance;
    }
}