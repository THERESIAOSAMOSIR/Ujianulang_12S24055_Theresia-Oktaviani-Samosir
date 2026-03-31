package academic.model;

public abstract class Transaction {
    protected static int idCounter = 0;
    protected int id;
    protected String username, timestamp, description;
    protected double amount, fee, netAmount;

    public Transaction(String user, double amt, String time, String desc) {
        this.id = ++idCounter;
        this.username = user;
        this.amount = amt;
        this.timestamp = time;
        this.description = desc;
    }

    public double getNetAmount() { return netAmount; }
    public abstract String getType();

    public String toString() {
        return id + "|" + getType() + "|" + amount + "|" + fee + "|" + Math.abs(netAmount) + "|" + timestamp + "|" + description;
    }
}