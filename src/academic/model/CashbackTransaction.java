package academic.model;
public class CashbackTransaction extends Transaction {
    public CashbackTransaction(String u, double a, String t, String d) { 
        super(u, a, t, d); this.fee = 0.0; this.netAmount = a; 
    }
    public String getType() { return "cashback"; }
}