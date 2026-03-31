package academic.model;
public class DepositTransaction extends Transaction {
    public DepositTransaction(String u, double a, String t, String d) { 
        super(u, a, t, d); this.fee = 0.0; this.netAmount = a; 
    }
    public String getType() { return "deposit"; }
}