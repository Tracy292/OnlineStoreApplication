package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OnlineStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        homeScreen();
        searchByName();
        int Choice = scanner.nextInt();
        switch (Choice) {
            case 1:
            case 2:
            case 3:
        }

    }

    public static void homeScreen() {
        System.out.println("1. Display products");
        System.out.println("2. Display Cart");
        System.out.println("3. Exit");
        System.out.print("Choose: ");
    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;
            bufReader.readLine();

            while ((input = bufReader.readLine()) != null) {
                String[] fileSplit = input.split(Pattern.quote("|"));
                String SKU = fileSplit[0];
                String productName = fileSplit[1];
                double price = Double.parseDouble(fileSplit[2]);
                String department = fileSplit[3];

                inventory.add(new Product(productName, SKU, price, department));
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    public static void displayProducts() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = getInventory();
        System.out.println("Here's Our Inventory List: ");
        for (Product i : inventory) {
            System.out.println("_________________________________________________");
            System.out.printf("SKU: %s Produce Name: %s Price: %.2f Department: %s\n", i.getSKU(), i.getProductName(), i.getPrice(), i.getDepartment());
        }
        System.out.println("1. Search");
        System.out.println("2. Add");
        System.out.println("3. Go Back");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:productSearch();
            case 2:
            case 3:homeScreen();
        }
     productSearch();
    }

    public static void productSearch() {
        Scanner search = new Scanner(System.in);
        System.out.println("Which Category would you like to search in: ");
        System.out.println("1.Product name ");
        System.out.println("2.Price ");
        System.out.println("3.Department ");
        int choice = search.nextInt();
        switch (choice) {
            case 1: searchByName();
            case 2: searchByPrice();
            case 3: searchByDeparment();
        }

    }

    public static void searchByName() {
      boolean decision = true;
        do {

           Scanner scanner = new Scanner(System.in);
           ArrayList<Product> inventory = getInventory();
           System.out.print("Enter Product Name: ");
           String productName = scanner.nextLine();
           for (Product i : inventory) {
               if (productName.equalsIgnoreCase(i.getProductName())) {
                   System.out.printf("SKU: %s Produce Name: %s Price: %.2f Department: %s\n", i.getSKU(), i.getProductName(), i.getPrice(), i.getDepartment());
                   System.out.println("Would you like to add this to cart?");
                   String choice = scanner.nextLine();
                   if(choice.equalsIgnoreCase("yes")){
                       cart(productName);
                   }
               }

           }
         System.out.println("Would you like to search again");
           String input = scanner.nextLine();
           if (input.equalsIgnoreCase("no")){
                decision = false;
            }
       }
       while (decision);
}
    public static void searchByDeparment(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = getInventory();
        System.out.print("Enter Department Name: ");
        String departmentName = scanner.nextLine();
        for (Product i : inventory) {
            if (departmentName.equalsIgnoreCase(i.getDepartment())) {
                System.out.printf("SKU: %s Produce Name: %s Price: %.2f Department: %s\n", i.getSKU(), i.getProductName(), i.getPrice(), i.getDepartment());
            }

        }

    }
    public static void searchByPrice(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = getInventory();
        System.out.print("Enter Price (ex: 12.99): ");
        double price = scanner.nextDouble();
        for (Product i : inventory) {
            if (price== i.getPrice()) {
                System.out.printf("SKU: %s Produce Name: %s Price: %.2f Department: %s\n", i.getSKU(), i.getProductName(), i.getPrice(), i.getDepartment());
            }

        }

    }
    public static void cart(String choice ){
        ArrayList<Product> inventory = getInventory();
      try {
          FileWriter fileWriter = new FileWriter("src/main/resources/cart.csv",true);
          BufferedWriter bufwriter = new BufferedWriter(fileWriter);
          for(Product i:inventory){
              if(choice.equalsIgnoreCase(i.getProductName())){
                  bufwriter.write( i.getSKU());
                  bufwriter.write( i.getDepartment());
                  bufwriter.write( i.getProductName());
              }
          }
          bufwriter.close();
      }
      catch(IOException e) {
          e.printStackTrace();
        }
    }
}
