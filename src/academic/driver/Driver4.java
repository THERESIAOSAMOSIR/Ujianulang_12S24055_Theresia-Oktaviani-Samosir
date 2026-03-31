package fintech.driver;

import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import fintech.model.*;

public class Driver3 {
    private static int transactionIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("---")) break;

            String[] data = input.split("#");
            String command = data[0];

            if (command.equals("create-account")) {
                accounts.put(data[2], new Account(data[1], data[2]));
            } else if (command.equals("deposit")) {
                Account account = accounts.get(data[1]);
                if (account != null) {
                    account.addTransaction(new DepositTransaction(transactionIdCounter++, data[1], Double.parseDouble(data[2]), data[3], data[4]));
                }
            } else if (command.equals("transfer")) {
                Account sender = accounts.get(data[1]);
                Account receiver = accounts.get(data[2]);
                double amount = Double.parseDouble(data[3]);

                if (sender != null && receiver != null && sender.getBalance() >= amount) {
                    sender.addTransaction(new TransferTransaction(transactionIdCounter++, data[1], data[2], amount, data[4], data[5]));
                    receiver.addTransaction(new DepositTransaction(transactionIdCounter++, data[2], amount, data[4], data[5]));
                }
            }
        }

        for (Account account : accounts.values()) {
            System.out.println(account.toString());
        }
        scanner.close();
    }
}