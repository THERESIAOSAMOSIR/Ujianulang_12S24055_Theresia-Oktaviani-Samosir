package academic.model;
public class SubscriptionTransaction extends Transaction {
    public SubscriptionTransaction(String u, double a, String t, String d) { 
        super(u, a, t, d); this.fee = 0.0; this.netAmount = -a; 
    }
    public String getType() { return "subscription"; }
}