package fintech.driver;

import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import fintech.model.*;

public class Driver2 {
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
                    double amount = Double.parseDouble(data[2]);
                    account.addTransaction(new DepositTransaction(transactionIdCounter++, data[1], amount, data[3], data[4]));
                }
            } else if (command.equals("withdraw")) {
                Account account = accounts.get(data[1]);
                if (account != null) {
                    double amount = Double.parseDouble(data[2]);
                    if (account.getBalance() >= amount) {
                        account.addTransaction(new WithdrawTransaction(transactionIdCounter++, data[1], amount, data[3], data[4]));
                    }
                }
            }
        }

        for (Account account : accounts.values()) {
            System.out.println(account.toString());
        }
        scanner.close();
    }
}