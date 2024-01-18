import java.util.*;
import java.io.*;

public class DirectoryNew {
    
    public static void addItem(String[] args, File database, File temporary, File sales, File tempSales) {

        Scanner input = new Scanner(System.in);
    
        try (
            FileReader Freader = new FileReader(database);
            FileReader SalesReader = new FileReader(sales);
            FileWriter FileWriter = new FileWriter(temporary);
            FileWriter SalesWriter = new FileWriter(tempSales);
            BufferedReader reader = new BufferedReader(Freader);
            BufferedReader Sreader = new BufferedReader(SalesReader);
            BufferedWriter Swriter = new BufferedWriter(SalesWriter);
            BufferedWriter writer = new BufferedWriter(FileWriter)
        ) {
            System.out.println("\nADD ITEM");
    
            System.out.print("ID: ");
            String id = input.next();
            input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Description: ");
            String description = input.nextLine();
            System.out.print("Usage: ");
            String usage = input.nextLine();
            System.out.print("Price: ");
            String price = input.next();
            input.nextLine();
            System.out.print("Quantity: ");
            String quantity = input.next();
            input.nextLine();
            System.out.print("Quantity Sold: ");
            String sold = input.next();
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

            while((line = Sreader.readLine()) != null ) {
                Swriter.write(line);
                Swriter.newLine();
            }
            
            Swriter.write(id+ "," +name+ "," +sold+ "," +quantity+ "," +quantity);
            Swriter.newLine();

            // Add new data to the temporary file
            writer.write(id + "," + name + "," + description + "," + usage + "," + price + "," + expiryDate);
            writer.newLine();
    
            System.out.println("\nItem added successfully.");
    
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public static void searchItem(String[] args, File database) throws IOException {
        
        String searchID = " ";
        String[] parts = {};
        Scanner input = new Scanner(System.in);

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
                        System.out.println("----------------------");
                        System.out.println("ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Description: " + parts[2]);
                        System.out.println("Usage: " + parts[3]);
                        System.out.println("Price: " + parts[4]);
                        System.out.println("Expiry Date: " + parts[5]);
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


    }

    public static void displaying(File database) {


        try (
            FileReader Freader = new FileReader(database);
            BufferedReader reader = new BufferedReader(Freader)
        ) { 
            String line;

            System.out.println("Inventory List: ");
            System.out.printf("\nID  %-15s %-26s %-68s %5s    %11s\n", "name", "description", "usage", "price", "expiry date");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                System.out.printf("%-3s", parts[0]);
                System.out.printf(" %-15s", parts[1]);
                System.out.printf(" %-26s", parts[2]);
                System.out.printf(" %-68s", parts[3]);
                System.out.printf(" RM %5s ", parts[4]);
                System.out.printf("    %-11s",parts[5]);
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
        int i = 0;
        int quantityLeft = 0;
        String[][] sales = new String[100][5];
        int CurrentItem = 23;
        File file = new File("sales.csv");
        File temporary = new File("sales.tmp");
        
        System.out.println("1. Update Sales\n2. Update Stock\n3. Display Sales");
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
                    int newQuantitySold = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(temporary));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(medToUpdate)) {

                            // Calculate the quantity left after sold
                            int quantityBeforeSold = Integer.parseInt(parts[4]);
                            parts[4] = String.valueOf(quantityBeforeSold - newQuantitySold);

                            // Update the quantity sold
                            newQuantitySold += Integer.parseInt(parts[2]);
                            parts[2] = String.valueOf(newQuantitySold);
                            
                            //display quantity left in stock
                            System.out.println("Quantity left in stock: " + parts[4]);
                           
                            quantityLeft = Integer.parseInt(parts[4]);
                        }

                        writer.write(String.join(",", parts));
                        writer.newLine();

                    }

                    reader.close();
                    writer.close();

                    if (!file.delete()) {
                        System.out.println("\nCould not delete the original file.");
                        return;
                    }
                    if (!temporary.renameTo(file)) {
                        System.out.println("\nCould not rename the temporary file.");
                    }

                    System.out.println("\nSales updated successfully.");

                     //indicate if the quantity is below 5
                    if( quantityLeft <= 4)
                    System.out.println("\nNOTICE\n--------------------------------------");
                    System.out.println("There are only " +quantityLeft+ " of this medicine left!!!.\nPlease add more immediately.");
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    System.out.print("Enter the medicine name to update: ");
                    String medToUpdate = scanner.nextLine();

                    System.out.print("Enter the new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(temporary));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(medToUpdate)) {
            
                            parts[4] = String.valueOf(newQuantity);
                        }

                        writer.write(String.join(",", parts));
                        writer.newLine();

                    }

                    reader.close();
                    writer.close();

                    if (!file.delete()) {
                        System.out.println("\nCould not delete the original file.");
                        return;
                    }
                    if (!temporary.renameTo(file)) {
                        System.out.println("\nCould not rename the temporary file.");
                    }

                    System.out.println("\nStocks updated successfully.");
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("sales.csv"));

                    System.out.println("\nSales For This Month: ");
                    System.out.printf("\nID  %-15s %-8s %-15s\n", "Name", "Quantity Sold(units)", "\t\tQuantity Left(units)");
                    System.out.println("---------------------------------------------------------------------------------------------");

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        sales[i][0] = parts[0];
                        sales[i][1] = parts[1];
                        sales[i][2] = parts[2];
                        sales[i][3] = parts[3];
                        sales[i][4] = parts[4];
                        
                        i++;
                    }

                    for(i=0; i<CurrentItem; i++) {

                        for(int j=0; j<sales[i].length; j++) {

                            if(j==0)
                            System.out.printf("%-3s",sales[i][j]);
                            
                            if(j==1)
                            System.out.printf(" %-13s", sales[i][j]);

                            if(j==2)
                            System.out.printf("\t   %-1s", sales[i][j]);

                            if(j==4)
                            System.out.printf("\t\t\t\t\t%-1s", sales[i][j]);
                        }
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
