package iecBilling;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class IEC {
	public static ArrayList<Account> accountsList = new ArrayList<Account>();
	public static int AccountsCount = accountsList.size();
	public static Scanner sc = new Scanner(System.in);
	private static int logs = 0;

	public static void menu() throws Exception {
		accountsList = DataHandler.loadAccounts();
		AccountsCount = accountsList.size();
		if (AccountsCount == 0) {
			generateDefaultUsers();
		}

		Account a1 = null;
		int comm = 0;
		while (comm != -1) {
			if (a1 != null)
				System.out.println(
						"please choose the action you'd like to do:\n1.Login another account\n2.Account info \n3.Check balance\n4.Change card\n5.Pay your bills\n9.Back to main menu");
			if (a1 == null)
				System.out.println("please choose the action you'd like to do:\n1.Register \n2.Login");

			if (logs != 0) {
				sc.nextLine();
			}
			comm = sc.nextInt();
			logs++;
			if (a1 != null && comm != 9) {
				comm++;

			}
			switch (comm) {

			case 1: {
				a1 = Account.register();
				if (a1 != null) {
					accountsList.add(a1);
					AccountsCount++;
					break;
				}
				break;
			}
			case 2: {
				a1 = Account.Login();
				if (a1 != null) {
					System.out.println("Welcome " + a1.getName());
				} else {
					System.err.println("Login failed");
				}
				break;
			}
			case 3: {

				if (a1 == null) {
					System.err.println("Please login or register first!".toUpperCase());
					break;
				}
				System.out.println(a1.toString());
				break;
			}
			case 4: {
				if (a1 == null) {
					System.err.println("Please login or register first!".toUpperCase());
					break;
				}
				Account.checkBalance(a1);
				break;
			}
			case 5: {
				if (a1 == null) {
					System.err.println("Please login or register first!".toUpperCase());
					break;
				}
				a1.setCard(CreditCardAPI.addCard());
				break;
			}
			case 6: {
				if (a1 == null) {
					System.err.println("Please login or register first!".toUpperCase());
					break;
				}
				Account.Payment(a1);
				break;
			}
			case 9: {
				System.err.println("Thank you and good bye!");
				comm = -1;
				DataHandler.saveAccounts(accountsList);
				MainMenu.main(null);

			}
			case 200: {// testing case
				for (int i = 0; i < accountsList.size(); i++) {
					System.out.println(accountsList.get(i) + "\n* Pass: " + accountsList.get(i).getPassword()
							+ "\n__________________\n ");

				}
			}

			}
		}
	}

	private static void generateDefaultUsers() {

		Address add1 = new Address("Tel Aviv", "Jerusalem", 16, "4a");
		LocalDate date1 = LocalDate.of(2022, 12, 5);
		CreditCardAPI cardAPI = new CreditCardAPI("30000000000004", 01, 20220254);
		Account a = new Account("Ziv", 208533034, "1q2w", add1, date1, cardAPI);
		accountsList.add(a);

		Address add2 = new Address("Tel Aviv", "Shlomit", 16, "8");
		LocalDate date2 = LocalDate.of(2023, 9, 4);
		CreditCardAPI cardAPI2 = new CreditCardAPI("378282246310005", 01, 2026);
		Account b = new Account("Amit", 319024162, "1w", add2, date2, cardAPI2);
		accountsList.add(b);

		Address add3 = new Address("Jerusalem", "David", 1, "lobby");
		LocalDate date3 = LocalDate.of(1996, 12, 4);
		CreditCardAPI cardAPI3 = new CreditCardAPI("4580160016001600", 02, 2024);
		Account c = new Account("Yarden", 391842102, "1q", add3, date3, cardAPI3);
		accountsList.add(c);

		Address add4 = new Address("Eilat", "Avraham hostel", 9, "100");
		LocalDate date4 = LocalDate.of(2022, 1, 1);
		CreditCardAPI cardAPI4 = new CreditCardAPI("5555555555554444", 02, 2024);
		Account d = new Account("Ariel", 809631532, "42", add4, date4, cardAPI4);
		accountsList.add(d);
		AccountsCount = accountsList.size();

	}

}
