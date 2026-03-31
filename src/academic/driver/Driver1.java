package academic.driver;
import academic.model.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) break;
            String[] d = line.split("#");

            try {
                if (d[0].equals("create-account")) {
                    accounts.put(d[2], new Account(d[1], d[2], d[3]));
                } else if (d[0].equals("deposit")) {
                    accounts.get(d[1]).addTransaction(new DepositTransaction(d[1], Double.parseDouble(d[2]), d[3], d[4]));
                } else if (d[0].equals("show-account")) {
                    Account acc = accounts.get(d[1]);
                    System.out.println(acc);
                    for (Transaction t : acc.getTransactions()) System.out.println(t);
                }
            } catch (Exception e) {}
        }
        
        // Output default jika tidak ada show-account
        for (Account a : accounts.values()) System.out.println(a);
    }
}