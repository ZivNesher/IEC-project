package iecBilling;

import java.util.Scanner;

public class MainMenu {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello and welcome to my IEC billing project.\n"
				+ "	  in the program you can find:\n"
				+ "	  1. Register as new account / Login in to exist account\n"
				+ "	  2. Watch account balance (The balance is depends on the system clock)\n"
				+ "	  3. Pay system by card -> when the account makes a payment the balance go to 0)\n"
				+ "	  4. the paying system is an API example to show how it contacts the program.\n"
				+ "	  5. Every account get a reference code from the IEC");
		System.out.println("\nPlease write 'start' to start testing the program or 'exit' to quit the system.");
		try (Scanner sc = new Scanner(System.in)) {
			Boolean run = false;
			while (!run) {
				String command = sc.nextLine().toLowerCase();
				if (command.equals("start")) {
					IEC.menu();
					run = true;
				} else if (command.equals("exit")) {
					System.err.println("THANK YOU, GOOD BYE");
					System.exit(0);
					run = true;
				} else {
					System.out.println("Wrong, try again.");
				}
			}
		}

	}

}
