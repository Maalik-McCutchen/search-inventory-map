package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {

        loadInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
        } else {
            System.out.printf("We carry %s and the price is $%.2f",
                    matchedProduct.getName(), matchedProduct.getPrice());
        }
    }

    private static void loadInventory(){
                String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                String[] tokens = line.split("\\|");
                int iD = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);

                Product product = new Product(iD, name, price);

                inventory.put(iD, product);


            }
            reader.close();

        } catch (Exception e) {
            System.err.println("Something went wrong.");
        }

    }

}
