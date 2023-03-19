package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to advanced Address Book");
        AddressBook_Functions address = new AddressBook_Functions();
        address.newAddressBook();
        address.displayAddressBook();
      //  address.writeDataToCSVFile();
      //  address.readFromCSVFile();
        address.writeDataToJSONFile();
        address.readFromJSONFile();
    }
}