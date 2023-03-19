package org.example;


import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.json.simple.JSONObject;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBook_Functions {

    public ArrayList<Contact_book> con = new ArrayList<>();
    public Map<String, ArrayList<Contact_book>> hm = new HashMap<String, ArrayList<Contact_book>>();

    public ArrayList<Contact_book> getContact() {


        return new ArrayList<Contact_book>(con);
    }

    public void newAddressBook() {
        System.out.println("Enter AddressBook Name");
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan4 = new Scanner(System.in);
        Scanner scan5 = new Scanner(System.in);
        String userInputBookName = scan.next();

        if (hm.containsKey(userInputBookName)) {
            System.out.println("-----Address Book Already Exists-----");
        } else {
            System.out.print("How Many Contacts You Want To Store? -->> ");
            int userInput = scan.nextInt();
            for (int i = 0; i < userInput; i++) {
                System.out.println("Enter First Name");
                String first = scan1.nextLine();
                System.out.println("Enter Last Name");
                String last = scan3.nextLine();
                System.out.println("Enter Address");

                String address = scan2.nextLine();
                System.out.println("Enter City");
                String city = scan4.nextLine();
                System.out.println("Enter State");
                String state = scan5.nextLine();
                System.out.println("Enter Zip Code");
                int zip = scan.nextInt();
                System.out.println("Enter Phone Number");
                long mobileNo = scan.nextLong();
                System.out.println("Enter E-mail");
                String email = scan.next();
                Contact_book contact1 = new Contact_book(first, last, address, city, state, email, mobileNo, zip);
                con.add(contact1);

                System.out.println("Contact added Successfully");
            }
            hm.put(userInputBookName, con);
        }
    }

    public void displayAddressBook() {

        System.out.println("AddressBooks are : ");
        System.out.println("Iterating Hashmap...");


        for (Map.Entry<String, ArrayList<Contact_book>> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());

        }

    }

    public void writeDataToCSVFile() {

        try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Icon\\IdeaProjects\\Adv_AddressBook_Problem\\src\\main\\resources\\AddressBook.csv"))) {
            StatefulBeanToCsvBuilder<Contact_book> contactsStatefulBeanToCsvBuilder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Contact_book> contactStatefulBeanToCsv = contactsStatefulBeanToCsvBuilder.build();
            contactStatefulBeanToCsv.write(getContact());
            System.out.println("---File Stored In CSV Format---");

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void readFromCSVFile() {
        {
            try(Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Icon\\IdeaProjects\\Adv_AddressBook_Problem\\src\\main\\resources\\AddressBook.csv"));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                System.out.println("First name : " + record[3]);
                System.out.println("Last name : " + record[4]);
                System.out.println("Email : " + record[2]);
                System.out.println("Address :" + record[0]);
                System.out.println("Phone number : " + record[5]);
                System.out.println("City : " + record[1]);
                System.out.println("State : " + record[6]);
                System.out.println("Zip : " + record[7]);
                System.out.println("----------------------------------------");
            }
            System.out.println("Successfully read from CSV file");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    public void writeDataToJSONFile() {

        try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Icon\\IdeaProjects\\Adv_AddressBook_Problem\\src\\main\\resources\\Contact.json"))) {
            Gson gson = new Gson();
            String json = gson.toJson(hm);
            writer.write(json);
            System.out.println("---Contacts Added To JSON File---");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void readFromJSONFile() {

        try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Icon\\IdeaProjects\\Adv_AddressBook_Problem\\src\\main\\resources\\Contact.json"))) {
            JsonParser jsonParser = new JsonParser();
           Object obj =  jsonParser.parse(reader);
           JsonObject empOBJ = (JsonObject)obj;
           // JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            for (Map.Entry<String, JsonElement> str: empOBJ.entrySet()) {
                System.out.println(str.getKey()+"\t"+str.getValue()+"\n");
                System.out.println();
            }
            System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
}
}