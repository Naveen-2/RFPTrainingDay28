package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookManager {

    /*
    Hashtable containing address books mapped to a name
     */
    public Hashtable<String, List<Contact>> addressBookDict = new Hashtable<>();

    /**
     * addAddressBook - method to add new addressBooks to the addressBookDict Hashtable
     * @param name - name that needs to be mapped to the address book
     */
    public void addAddressBook(String name) {
        if(!addressBookDict.containsKey(name)) {
            List<Contact> addressBook = new ArrayList<>();
            addressBookDict.put(name, addressBook);
            if (ScannerUtil.getString("Press 'a' to add more Address books\n").equalsIgnoreCase("A")) {
                addAddressBook(ScannerUtil.getString("Enter name of Address Book: "));
            }
        } else {
            System.out.println("Address Book with same name already available. Please use a different name.");
        }
    }

    /**
     * searchByCity - method to find the contacts in specified city
     */
    public void searchByCity() {

        String cityName = ScannerUtil.getString("Enter the name of the City to get the persons : ");
        for (String i : addressBookDict.keySet()) {
            List<Contact> arr = addressBookDict.get(i);
            arr.stream().filter(contact -> contact.getCity().equals(cityName)).forEach(contact -> System.out.println(contact.getFirstName()));
        }
    }

    /**
     * searchByState - method to find the contacts in specified state
     */
    public void searchByState() {

        String stateName = ScannerUtil.getString("Enter the name of the State to the get persons : ");
        for (String i : addressBookDict.keySet()) {
            List<Contact>	arr = addressBookDict.get(i);
            arr.stream().filter(person -> person.getState().equals(stateName)).forEach(person -> System.out.println(person.getFirstName()));
        }
    }

    /**
     * displayPeopleByRegion - method to display the contacts only in specified region
     * @param addressBookDict - List of contacts in the selected region
     */
    public void displayPeopleByRegion(Hashtable<String, ArrayList<Contact>> addressBookDict) {
        String regionName = ScannerUtil.getString("Enter the name of the region :");

        addressBookDict.values().stream()
                .map(region -> region.stream()
                        .filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
                .forEach(person -> person.forEach(System.out::println));
    }
    /**
     * In this method we are displaying the number of person in the city or state.
     * @param listToDisplay - we are passing the list of city or state
     */
    public void countPeopleByRegion(Hashtable<String, ArrayList<Contact>> listToDisplay) {

        String regionName = ScannerUtil.getString("Enter the name of the region :");
        long countPeople = listToDisplay.values().stream()
                .map(region -> region.stream().filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
                .count();

        System.out.println("Number of People residing in " + regionName+" are: "+countPeople+"\n");

    }

    /**
     * sortAddressBook - method to sort all address books based on user selection
     */
    public void sortAddressBook(){
        List<Contact> sortedContactList;
        for (String i : addressBookDict.keySet()) {
            List<Contact> contactList = addressBookDict.get(i);
            int sortingChoice = ScannerUtil.getInt("Sort by\n 1.First Name\n 2.City\n 3.State \n4.Zip");
            switch (sortingChoice) {
                case 1 -> {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getFirstName))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 2 -> {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getCity))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 3 -> {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getState))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
                case 4 -> {
                    sortedContactList = contactList.stream()
                            .sorted(Comparator.comparing(Contact::getZip))
                            .collect(Collectors.toList());
                    printSortedList(sortedContactList);
                }
            }

        }
    }

    /**
     * printSortedList - method to print contacts in addressBook
     *
     * @param sortedContactList - address book that is to be printed
     */
    public void printSortedList(List<Contact> sortedContactList) {
        for (Contact contact : sortedContactList) {
            System.out.println(contact);
            System.out.println();
        }
    }

    public void writeToFile() {

        String bookName = ScannerUtil.getString("Enter book name: ");
        String fileName = bookName + ".txt";

        StringBuffer addressBookBuffer = new StringBuffer();
        addressBookDict.values().forEach(contact -> {
            String personDataString = contact.toString().concat("\n");
            addressBookBuffer.append(personDataString);
        });

        try {
            Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> readFileData() {

        List<String> addressBookList = new ArrayList<>();
        String bookName = ScannerUtil.getString("Enter book name: ");
        String fileName = bookName + ".txt";
        System.out.println("Reading from : " + fileName + "\n");
        try {
            Files.lines(new File(fileName).toPath()).map(String::trim).forEach(contacts -> {
                System.out.println(contacts);
                addressBookList.add(contacts);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBookList;
    }

}