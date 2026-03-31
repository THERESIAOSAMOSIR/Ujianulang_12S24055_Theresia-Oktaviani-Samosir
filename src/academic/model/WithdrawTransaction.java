package academic.model;
public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(String u, double a, String t, String d) { 
        super(u, a, t, d); this.fee = a * 0.05; this.netAmount = -(a + fee); 
    }
    public String getType() { return "withdraw"; }
}