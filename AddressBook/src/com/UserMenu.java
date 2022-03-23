package com;

import java.util.Scanner;

public class UserMenu {
    Scanner input = new Scanner(System.in);

    public int showMainMenu() {
        System.out.println("""
                Main Menu:
                 1.Add Contact\s
                 2.Edit Contact\s
                 3.Delete Contact\s
                 4.Add Multiple Contacts
                 5.Add new Address Book
                 6.Search Contacts by city
                 7.Search Contacts by state
                 8.Display Contacts by city
                 9.Display Contacts by state
                 10.Count Contacts by city
                 11.Count Contacts by state
                 12.Sort contacts
                """);
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

}