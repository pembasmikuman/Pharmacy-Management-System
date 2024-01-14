/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compile;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author syami
 */
public class DirectoryNew {
    public static void addItem(String[] args) {

        File database = new File("C:\\Users\\syami\\OneDrive\\Documents\\NetBeansProjects\\eop\\src\\Compile\\database.csv");
        File temporary = new File("C:\\Users\\syami\\OneDrive\\Documents\\NetBeansProjects\\eop\\src\\Compile\\database.tmp");
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
    
        try (
            FileReader Freader = new FileReader(database);
            FileWriter FileWriter = new FileWriter(temporary);
            BufferedReader reader = new BufferedReader(Freader);
            BufferedWriter writer = new BufferedWriter(FileWriter)
        ) {
            System.out.println("\nADD ITEM");
    
            System.out.print("ID: ");
            String id = input.next();
            input.nextLine();
            System.out.print("Name: ");
            String name = input1.nextLine();
            System.out.print("Description: ");
            String description = input1.nextLine();
            System.out.print("Usage: ");
            String usage = input1.nextLine();
            System.out.print("Price: ");
            String price = input.next();
            input.nextLine();
            System.out.print("Quantity: ");
            String quantity = input.next();
            input.nextLine();
            System.out.print("Expiry Date (MM/yyyy): ");
            String expiryDate = input.next();
            input.nextLine();
    
            // Duplicate existing data to the temporary file
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
    
            // Add new data to the temporary file
            writer.write(id + "," + name + "," + description + "," + usage + "," + price + "," + quantity + "," + expiryDate);
            writer.newLine();
            
             if(database.exists()) {
            database.delete();
            temporary.renameTo(database);
        }
    
            System.out.println("Item added successfully.");
    
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //input.close(); // Close the scanner in the finally block
            //input1.close();
        }
    }

     public static void searchItem(String[] args) throws IOException {
        
        char update = ' ';
        String id = " ";
        String name = " ";
        String description = " ";
        String usage = " ";
        String price = " ";
        String quantity = " ";
        String expiryDate = " ";
        String searchID = " ";
        String[] parts = {};
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        File database = new File("C:\\Users\\syami\\OneDrive\\Documents\\NetBeansProjects\\eop\\src\\Compile\\database.csv");
        File temporary = new File("C:\\Users\\syami\\OneDrive\\Documents\\NetBeansProjects\\eop\\src\\Compile\\database.tmp");

        try (FileReader Freader = new FileReader(database)) {

            try (
                BufferedReader reader = new BufferedReader(Freader);
            ) {
                
                String line;
   
                System.out.print("\nEnter Inventory ID to search: ");
                searchID = input.next();
   
                while ((line = reader.readLine()) != null) {
                    parts = line.split(",");
   
                    if (parts[0].equals(searchID)) {
                        System.out.println("\nInventory Details:");
                        System.out.println("ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Description: " + parts[2]);
                        System.out.println("Usage: " + parts[3]);
                        System.out.println("Price: " + parts[4]);
                        System.out.println("Quantity: " + parts[5]);
                        System.out.println("Expiry Date: " + parts[6]);
                        return;
                    }
                }
                
                System.out.println("Inventory with ID '" + searchID + "' not found.");
   
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Do you want to update the quantity of this item? (Y/N): ");
        update = input.next().charAt(0);

        if(update == 'Y') {

            try (
                FileReader Freader = new FileReader(database);
                FileWriter FileWriter = new FileWriter(temporary);
                BufferedReader reader = new BufferedReader(Freader);
                BufferedWriter writer = new BufferedWriter(FileWriter)
            ) {
                
                System.out.print("ID: ");
                id = input.next();
                System.out.print("Name: ");
                name = input1.nextLine();
                System.out.print("Description: ");
                description = input1.nextLine();
                System.out.print("Usage: ");
                usage = input1.nextLine();
                System.out.print("Price: ");
                price = input.next();
                System.out.print("New Quantity: ");
                quantity = input.next();
                System.out.print("Expiry Date (MM/yyyy): ");
                expiryDate = input.next();

                String line;

                while((line = reader.readLine()) != null ) {
                    writer.write(line);
                    writer.newLine();
                }

                writer.write(id + "," + name + "," + description + "," + usage + "," + price + "," + quantity + "," + expiryDate);
                writer.newLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        input.close();
        input1.close();
    }
     public static void displaying() {

        File file = new File("C:\\Users\\syami\\OneDrive\\Documents\\NetBeansProjects\\eop\\src\\Compile\\database.csv");

        try (
            FileReader Freader = new FileReader(file);
            BufferedReader reader = new BufferedReader(Freader)
        ) { 
            String line;

            System.out.println("Inventory List: ");
            System.out.printf("\nID  %-15s %-26s %-63s %7s   %6s %-8s\n%131s%s\n", "name", "description", "usage", "price", "quantity", "expiry", "", "date");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                System.out.printf("%-3s", parts[0]);
                System.out.printf(" %-13s", parts[1]);
                System.out.printf(" %-26s", parts[2]);
                System.out.printf(" %-68s", parts[3]);
                System.out.printf(" %-4s ", parts[4]);
                System.out.printf("   %-1s  ", parts[5]);
                System.out.printf(" %-9s",parts[6]);
                System.out.println();
                
                
                
            }
            return;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public static void salesInfo() {

       Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. Update Sales\n2. Display Sales");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
          System.out.println("");

        switch (choice) {
            case 1:
                try {
                    System.out.print("Enter the medicine name to update: ");
                    String medToUpdate = scanner.nextLine();

                    System.out.print("Enter the new quantity sold: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    File file = new File("sales.csv");
                    File tempFile = new File("temp.csv");

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(medToUpdate)) {
                            // Update the quantity sold
                            parts[2] = String.valueOf(newQuantity);

                            // Calculate the quantity left after sold
                            int quantityBeforeSold = Integer.parseInt(parts[3]);
                            parts[4] = String.valueOf(quantityBeforeSold - newQuantity);
                            
                            //display quantity left in stock
                            System.out.println("\nQuantity left in stock: " + parts[4]);
                           
                            int quantityLeft = Integer.parseInt(parts[4]);
                            
                            //indicate if the quantity is below 5
                            if( quantityLeft <= 4)
                                System.out.println("There are only 5 of this medicine left!!!.\nPlease add more immediately");
                        }

                        writer.write(String.join(",", parts));
                        writer.newLine();
                    }

                    reader.close();
                    writer.close();

                    if (!file.delete()) {
                        System.out.println("Could not delete the original file.");
                        return;
                    }
                    if (!tempFile.renameTo(file)) {
                        System.out.println("Could not rename the temporary file.");
                    }

                    System.out.println("Sales updated successfully.");
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("sales.csv"));

                    System.out.println("\nSales For This Month: ");
                    System.out.printf("\nID  %-15s %-8s %-15s\n", "Name", "Quantity Sold(units)", "\t\tQuantity Left(units)");
                    System.out.println("---------------------------------------------------------------------------------------------");

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        // Display all sales and quantity left
                        System.out.printf("%-3s", parts[0]);
                        System.out.printf(" %-13s", parts[1]);
                        System.out.printf("\t   %-1s", parts[2]);
                        System.out.printf("\t\t\t\t\t%-1s", parts[4]);
                        System.out.println();
                    }

                    reader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
        }

       
    }
}
