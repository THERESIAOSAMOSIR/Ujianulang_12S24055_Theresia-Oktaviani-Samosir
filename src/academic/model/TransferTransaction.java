package academic.model;
public class TransferTransaction extends Transaction {
    public TransferTransaction(String u, double a, String t, String d, boolean isSender) {
        super(u, a, t, d);
        if (isSender) { this.fee = a * 0.02; this.netAmount = -(a + fee); }
        else { this.fee = 0.0; this.netAmount = a; }
    }
    public String getType() { return "transfer"; }
}