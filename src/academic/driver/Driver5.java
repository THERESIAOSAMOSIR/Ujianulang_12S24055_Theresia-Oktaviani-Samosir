package fintech.driver;

import java.util.*;
import fintech.model.*;

public class Driver4 {
    private static int transactionIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("---")) break;

            String[] data = input.split("#");
            String command = data[0];

            try {
                if (command.equals("create-account")) {
                    accounts.put(data[2], new Account(data[1], data[2]));
                } else if (command.equals("deposit")) {
                    Account acc = accounts.get(data[1]);
                    if (acc != null) {
                        acc.addTransaction(new DepositTransaction(transactionIdCounter++, data[1], Double.parseDouble(data[2]), data[3], data[4]));
                    }
                } else if (command.equals("withdraw")) {
                    Account acc = accounts.get(data[1]);
                    if (acc != null) {
                        double amount = Double.parseDouble(data[2]);
                        if (acc.getBalance() < amount) throw new NegativeBalanceException("Insufficient balance");
                        acc.addTransaction(new WithdrawTransaction(transactionIdCounter++, data[1], amount, data[3], data[4]));
                    }
                } else if (command.equals("transfer")) {
                    Account sender = accounts.get(data[1]);
                    Account receiver = accounts.get(data[2]);
                    if (sender != null && receiver != null) {
                        double amount = Double.parseDouble(data[3]);
                        if (sender.getBalance() < amount) throw new NegativeBalanceException("Insufficient balance");
                        sender.addTransaction(new TransferTransaction(transactionIdCounter++, data[1], data[2], amount, data[4], data[5]));
                        receiver.addTransaction(new DepositTransaction(transactionIdCounter++, data[2], amount, data[4], data[5]));
                    }
                } else if (command.equals("show-account")) {
                    Account acc = accounts.get(data[1]);
                    if (acc != null) {
                        System.out.println(acc.toString());
                        List<Transaction> history = acc.getTransactions();
                        history.sort(Comparator.comparing(Transaction::getTimestamp));
                        for (Transaction t : history) {
                            double amt = t instanceof DepositTransaction ? t.getAmount() : -t.getAmount();
                            System.out.printf("%d|%s|%.1f|%s|%s\n", t.getId(), t.getType(), amt, t.getTimestamp(), t.getDescription());
                        }
                    }
                }
            } catch (NegativeBalanceException e) {
                // Program berlanjut sesuai spesifikasi README
            }
        }
        scanner.close();
    }
}
