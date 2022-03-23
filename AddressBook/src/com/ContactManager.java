package com;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ContactManager {

    AddressBookManager addressBookManager = new AddressBookManager();
    List<Contact> addressBook = new ArrayList<>();


    public static Hashtable<String, ArrayList<Contact>> personByCity  = new Hashtable<>();
    public static Hashtable<String, ArrayList<Contact>> personByState = new Hashtable<>();

    /**
     *  addNewContact - method to add new contact to the addressBook
     */
    public void addNewContact() {
        Contact contact = new Contact();
        addressBook = addressBookManager.addressBookDict.get(ScannerUtil.getString("Enter name of Address Book: "));
        contact.setFirstName(ScannerUtil.getString("Enter First name: "));
        contact.setLastName(ScannerUtil.getString("Enter Last name: "));
        contact.setPhoneNumber(ScannerUtil.getLong("Enter Phone number: "));
        contact.setEmail(ScannerUtil.getString("Enter Email: "));
        contact.setAddress(ScannerUtil.getString("Enter Address: "));
        contact.setCity(ScannerUtil.getString("Enter City: "));
        contact.setState(ScannerUtil.getString("Enter State: "));
        contact.setZip(ScannerUtil.getInt("Enter Zip code: "));

        if (checkDuplicateByFirstName(contact.getFirstName())) {
            System.out.println("So not added to this address book.");
        } else {
            addressBook.add(contact);
            addPersonToCity(contact);
            addPersonToState(contact);
        }
    }
    /**
     * editByName - method to edit the contact based on first name
     * @param name - first name to match the details in the address book
     */
    public void editByName(String name) {
        addressBook = addressBookManager.addressBookDict.get(ScannerUtil.getString("Enter name of Address Book: "));
        if(!addressBook.isEmpty()) {
            for (int i = 0; i < addressBook.size(); i++) {
                if (name.equalsIgnoreCase(addressBook.get(i).firstName)) {
                    addressBook.set(i, editContact(addressBook.get(0)));
                } else {
                    System.out.println("Contact nor found");
                }
            }
        } else {
            System.out.println("Contact list empty. Please add some contacts first.");
        }
    }

    /**
     * editContact - method to edit the contact with new details
     * @param contact - contact that is to be edited
     * @return - the address book will be returned after adding new contact to it
     */
    public Contact editContact(Contact contact) {
        contact.setFirstName(ScannerUtil.getString("Enter new First name: "));
        contact.setLastName(ScannerUtil.getString("Enter new Last name: "));
        contact.setPhoneNumber(ScannerUtil.getLong("Enter new Phone number: "));
        contact.setEmail(ScannerUtil.getString("Enter new Email: "));
        contact.setAddress(ScannerUtil.getString("Enter new Address: "));
        contact.setCity(ScannerUtil.getString("Enter new City: "));
        contact.setState(ScannerUtil.getString("Enter new State: "));
        contact.setZip(ScannerUtil.getInt("Enter new Zip code: "));
        return contact;
    }

    /**
     * deleteContact - method to delete the given contact name if present
     * @param name - name of the contact to be deleted
     */
    public void deleteContact(String name) {
        addressBook = addressBookManager.addressBookDict.get(ScannerUtil.getString("Enter name of Address Book: "));
        if(!addressBook.isEmpty()) {
            for (Contact contact : addressBook) {
                if (name.equals(contact.firstName)) {
                    addressBook.remove(contact);
                    System.out.println("Contact removed successfully.");
                    break;
                } else {
                    System.out.println("Contact nor found");
                }
            }
        }
    }

    /**
     * addMultipleContacts - method to add multiple contacts in same list
     */
    public void addMultipleContacts() {
        String option;
        do {
            addNewContact();
            option = ScannerUtil.getString("Press 'Y' to add more contacts");
        } while (option.equalsIgnoreCase("Y"));
    }

    /**
     * checkDuplicateByFirstName - method to check if the entered name is unique
     * @param name - name that is used to match if it's unique or not
     * @return - true if the given name is present in the contact list
     */
    public boolean checkDuplicateByFirstName(String name) {
        addressBook = addressBookManager.addressBookDict.get(ScannerUtil.getString("Enter name of Address Book: "));
        for (Contact contact : addressBook) {
            if (name.equalsIgnoreCase(contact.firstName)) {
                System.out.println("Contact already available");
                return true;
            }
        }
        return false;
    }

    /**
     *  addPersonToCity - method t check the person by city
     * @param contact- We are parsing the contact there
     */
    public void addPersonToCity(Contact contact) {
        if (personByCity.containsKey(contact.getCity())) {
            personByCity.get(contact.getCity()).add(contact);
        }
        else {
            ArrayList<Contact> cityList = new ArrayList<>();
            cityList.add(contact);
            personByCity.put(contact.getCity(), cityList);
        }
    }


    /**
     *  addPersonToState method to check the person by state
     * @param contact- We are parsing the contact there
     */
    public void addPersonToState(Contact contact) {
        if (personByState.containsKey(contact.getState())) {
            personByState.get(contact.getState()).add(contact);
        }
        else {
            ArrayList<Contact> stateList = new ArrayList<>();
            stateList.add(contact);
            personByState.put(contact.getState(), stateList);
        }
    }

}