package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    /**
     * handleUserSelection - method to show the available options in this
     * Address Book application
     */
    public static void handleUserSelection() {
        ContactManager contactManager = new ContactManager();

        UserMenu userMenu = new UserMenu();

        AddressBookManager addressBookManager = new AddressBookManager();
        addressBookManager.addAddressBook(ScannerUtil.getString("Enter name of Address Book: "));
        int x;

        do {
            /* User menu items are shown to user and let them select the option */
            int choice = userMenu.showMainMenu();
            switch (choice) {
                /*
                Option to add a new contact to the selected address book
                 */
                case 1 -> contactManager.addNewContact();
                /*
                Option to edit an existing contact with first name selected address book
                 */
                case 2 -> contactManager.editByName(ScannerUtil.getString("Enter contact name to edit: "));
                /*
                 Option to delete an existing contact from the selected address book
                 */
                case 3 -> contactManager.deleteContact(ScannerUtil.getString("Enter contact name to delete: "));
                /*
                Option to add multiple contacts to the selected address book
                 */
                case 4 ->  contactManager.addMultipleContacts();
                /*
                Option to add new address book
                 */
                case 5 -> addressBookManager.addAddressBook(ScannerUtil.getString("Enter name of Address Book: "));
                /*
                Search and print first names of all contacts by city
                 */
                case 6 -> addressBookManager.searchByCity();
                /*
                Search and print first name of all contacts by state
                 */
                case 7 -> addressBookManager.searchByState();
                /*
                Display only the contacts in certain city
                 */
                case 8 -> addressBookManager.displayPeopleByRegion(ContactManager.personByCity);
                /*
                Display only the contacts in certain state
                 */
                case 9 -> addressBookManager.displayPeopleByRegion(ContactManager.personByState);
                /*
                Count only the contacts in certain city
                 */
                case 10 -> addressBookManager.countPeopleByRegion(ContactManager.personByCity);
                /*
                Count only the contacts in certain state
                 */
                case 11 -> addressBookManager.countPeopleByRegion(ContactManager.personByState);
                /*
                Sort the contacts in address book
                 */
                case 12 -> addressBookManager.sortAddressBook();
                default -> {
                }
            }
            x = ScannerUtil.getInt("Do you want to continue...press 1\n");
        } while (x == 1);
    }

    /**
     * main method to initiate the program
     * @param args - no console arguments are received
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        try {
            InputStream inputStream = new FileInputStream(
                    "N:\\Bridgelabz\\Training\\RFPTrainingDay27");
            int byteData = inputStream.read();
            if (byteData != -1) {
                System.out.println(byteData);
            }
        } catch (IOException ignored) {

        }

        File file = new File(
                "N:\\Bridgelabz\\Training\\RFPTrainingDay27");
        try {
            boolean isFileCreated = file.createNewFile();
            if (isFileCreated) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exist");
            }
        } catch (IOException ignored) {

        }

        handleUserSelection();
    }
}