package iecBilling;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account implements Serializable {
	private static final long serialVersionUID = -6352851798381185954L;
	private String name;
	private int ID;
	private String Password;
	private Address address;
	private long accountBalance;
	private LocalDate date;
	private CreditCardAPI card;
	static Scanner sc = new Scanner(System.in);
	public static int logs = 0;

	public Account(String name, int ID, String password, Address add, LocalDate date, CreditCardAPI card) {
		this.name = name;
		this.address = add;
		this.Password = password;
		this.ID = ID;
		this.accountBalance = (long) 0.0;
		this.date = date;
		this.card = card;

	}

	public static Account register() {

		System.out.println("Please enter your ID:");
		int tmpID = 0;
		try {
			if (logs != 0)
				sc.nextLine();
			tmpID = sc.nextInt();
		} catch (Exception e) {
			System.err.println("ID most be numbers only, length is 8~9 digits".toUpperCase());
			logs++;
			return null;
		}
		if (checkId(tmpID) == false && tmpID != 0) {
			System.err.println("this ID is already used / you insert wrong id.");
			return null;
		}
		System.out.println("Please enter your name:");
		String tmpName = sc.nextLine();
		Boolean ok = false;
		String tmpPassword = "";
		while (!ok) {
			sc.nextLine();
			System.out.println("Please enter the PASSWORD you'd like to login with:");
			//tmpPassword = sc.nextLine();
			tmpPassword = checkPassword();
			System.out.println("Please enter the PASSWORD again:");
			String tmpPassword2 = sc.nextLine();
			if (tmpPassword.equals(tmpPassword2))
				ok = true;
			else {
				System.out.println("Please try again");
			}
		}
		System.out.println("Please enter your address by the format: city, street, building number,apartment number");
		String tmpAdd = sc.nextLine();
		Pattern delimiterPattern = Pattern.compile(",\\s*");
		String[] addressParts = delimiterPattern.split(tmpAdd);
		String tmpCity = addressParts[0];
		String tmpStreet = addressParts[1];
		int tmpBuildingNumber =0;
		try{tmpBuildingNumber = Integer.parseInt(addressParts[2]);
		}
		catch (Exception e) {
			System.err.println("Building number must be digits only");
		}
		String tmpApartmentNumber = addressParts[3];
		Address tmpAddress = new Address(tmpCity, tmpStreet, tmpBuildingNumber, tmpApartmentNumber);
		LocalDate date = LocalDate.now();
		CreditCardAPI cardAPI = CreditCardAPI.addCard();
		Account tmpAccount = new Account(tmpName, tmpID, tmpPassword, tmpAddress, date, cardAPI);
		return tmpAccount;
	}

	private static String checkPassword() {
		int specialChar = 0;
		String pass =sc.nextLine();
		for (int i = 0; i < pass.length(); i++) {
			if(pass.charAt(i)=='!'||pass.charAt(i)=='@'||pass.charAt(i)=='#'||pass.charAt(i)=='$'||pass.charAt(i)=='%'||pass.charAt(i)=='&')
				specialChar++;
		}
		if(pass.length()<8) {
			System.out.println("Password too short , it should be 8 chars at least.");
			checkPassword();
		}
		else if(specialChar==0){
			System.out.println("At least 1 char need to be special (!/@/#/$/%/&)");
			checkPassword();
		}
		return pass;
	}

	public static void checkBalance(Account acc) {
		acc.accountBalance = ChronoUnit.DAYS.between(acc.date, LocalDate.now());
		if (acc.accountBalance <= 0) {
			System.out.println("No bills to pay");
			return;
		}
		System.out.println("You need to pay: " + acc.accountBalance * 10 + " $");
	}

	public static void Payment(Account acc) {
		Boolean checkBoolean = CreditCardAPI.checkCard(acc.card);
		if (checkBoolean == true) {
			if (acc.accountBalance != 0) {
				System.out.println("Payment done!");
				acc.date = LocalDate.now();
			} else {
				System.out.println("Your bill is 0, no payment is needed this time.");
			}
		} else {
			System.err.println("Payment Failture, try again.");
		}
	}

	public String toString() {
		return "* Name: " + name + "\n* ID:" + ID + "\n* Address - " + address.toString() + "\n* Card: "
				+ card.getKind() + " -> ****" + CreditCardAPI.getLastDigits(card);
	}

	public void setCard(CreditCardAPI card) {
		this.card = card;

	}

	private static Account searchAccount() {
		System.out.println("Enter ID:");
		int tmpID = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Password:");
		String tmpPass = sc.nextLine();
		for (int i = 0; i < IEC.AccountsCount; i++) {
			if (IEC.accountsList.get(i).ID == tmpID && IEC.accountsList.get(i).Password.equals(tmpPass))
				return IEC.accountsList.get(i);
		}

		return null;

	}

	static Account Login() {
		Account account = searchAccount();
		return account;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CreditCardAPI getCard() {
		return card;
	}

	public static boolean checkId(int id) {
		for (int i = 0; i < IEC.AccountsCount; i++) {
			if (IEC.accountsList.get(i).ID == id)
				return false;
			if (String.valueOf(id).length() < 8 || String.valueOf(id).length() > 10) {
				return false;
			}
		}
		return true;
	}

}
