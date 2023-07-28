package iecBilling;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class CreditCardAPI implements Serializable {
	private static final long serialVersionUID = 3973740293476950723L;
	private String num;
	private int month;
	private int year;
	private String kind;
	public static Scanner sc = new Scanner(System.in);

	public CreditCardAPI(String num, int month, int year) {
		this.month = month;
		this.num = num;
		this.year = year;
		this.kind = "";
	}

	public static boolean checkCard(CreditCardAPI card) {
		int nowMonth = LocalDate.now().getMonthValue();
		int nowYear = LocalDate.now().getYear();
		int len = card.num.length();
		String lastDigit = last4digits(card.num);
		if (card.year < nowYear) {
			return false;
		} else if (card.year == nowYear && card.month < nowMonth) {
			return false;
		}

		if (len < 8 || len > 18)
			return false;
		else if (len == 8) {
			System.out.println("You are using Isracart Card:" + lastDigit);
			card.kind = "IsraCart";
			return true;
		} else if (len == 18 && card.num.charAt(0) == '5' && card.num.charAt(1) == '1' || card.num.charAt(1) == '2'
				|| card.num.charAt(1) == '3' || card.num.charAt(1) == '4') {
			System.out.println("You are using MasterCard Card:" + lastDigit);
			card.kind = "MaterCard";
			return true;
		} else if (len == 16 && card.num.charAt(0) == '4') {
			System.out.println("You are using Visa Card:" + lastDigit);
			card.kind = "Visa";
			return true;
		} else if (len == 14 && card.num.charAt(0) == '3') {
			System.out.println("You are using Diners Card:" + lastDigit);
			card.kind = "Diners";
			return true;
		} else if (len == 15 && card.num.charAt(1) == '7' && card.num.charAt(0) == '2' || card.num.charAt(0) == '3') {
			System.out.println("You are using Amax Card:" + lastDigit);
			card.kind = "Amax";
			return true;
		} else {
			System.err.println("Wrong card number.");
			return false;
		}
	}

	public String getNum() {
		return num;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public static CreditCardAPI addCard() {
		CreditCardAPI defaultApiCardAPI = new CreditCardAPI("defaultFail", 02, 2028);
		CreditCardAPI cardAPI = null;
		System.out.println("Please insert you card number:");
		String tmpNumString = sc.nextLine();
		System.out.println("Please insert your card expiration date month");
		int tmpMonth = sc.nextInt();
		System.out.println("Please insert your card expiration date year");
		int tmpYear = sc.nextInt();
		cardAPI = new CreditCardAPI(tmpNumString, tmpMonth, tmpYear);
		if (checkCard(cardAPI) == true) {
			System.out.println("card saved successfully!");
		} else if (checkCard(cardAPI) == false) {
			System.err.println("Card number or expiration date is wrong");
			return defaultApiCardAPI;
		}
		return cardAPI;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public static String last4digits(String str) {
		if (str.length() >= 4) {
			int len = str.length();
			StringBuilder sb = new StringBuilder();
			sb.append(str.charAt(len - 4)).append(str.charAt(len - 3)).append(str.charAt(len - 2))
					.append(str.charAt(len - 1));
			return sb.toString();
		} else {
			return "Invalid Card Number";
		}
	}

	public String getKind() {
		return kind;
	}

	public static String getLastDigits(CreditCardAPI card) {
		return last4digits(card.num);
	}

}
