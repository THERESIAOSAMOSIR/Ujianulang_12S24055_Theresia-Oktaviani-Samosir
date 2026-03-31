package fintech.driver;

import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import fintech.model.Account;

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("---")) break;

            String[] data = input.split("#");
            if (data[0].equals("create-account")) {
                String owner = data[1];
                String username = data[2];
                if (!accounts.containsKey(username)) {
                    accounts.put(username, new Account(owner, username));
                }
            }
        }

        for (Account account : accounts.values()) {
            System.out.println(account.toString());
        }
        scanner.close();
    }
}