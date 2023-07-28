package iecBilling;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    private static String filename = "accounts.ser";

    // Save the accountsList to a file
    public static void saveAccounts(ArrayList<Account> accountsList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(accountsList);
            System.out.println("Accounts list has been saved to the file.");
        } catch (IOException e) {
            System.err.println("Error saving accounts list: " + e.getMessage() + "\nGenerating default users for your test :)");
        }
    }

    // Load the accountsList from a file
    @SuppressWarnings("unchecked")
	public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accountsList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            accountsList = (ArrayList<Account>) ois.readObject();
            System.out.println("Accounts list has been loaded from the file.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading accounts list: " + e.getMessage());
        }
        return accountsList;
    }
}