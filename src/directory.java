import java.io.*;
import java.util.*;

public class directory {
    
    public static void addItem() {

        File database = new File("database.csv");
        File temporary = new File("database.tmp");
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
    
        try (
            BufferedReader reader = new BufferedReader(new FileReader("database.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("database.tmp"))
        ) {
            System.out.println("\nADD ITEM");
    
            System.out.print("ID: ");
            String id = input.next();
            System.out.print("Name: ");
            String name = input1.nextLine();
            System.out.print("Description: ");
            String description = input1.nextLine();
            System.out.print("Usage: ");
            String usage = input1.nextLine();
            System.out.print("Price: ");
            String price = input.next();
            System.out.print("Quantity: ");
            String quantity = input.next();
            System.out.print("Expiry Date (MM/yyyy): ");
            String expiryDate = input.next();
    
            // Duplicate existing data to the temporary file
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
    
            // Add new data to the temporary file
            writer.write(id + "," + name + "," + description + "," + usage + "," + price + "," + quantity + "," + expiryDate);
            writer.newLine();
    
            if (database.exists()) {
                database.delete(); // Delete the existing database file
            }

            temporary.renameTo(database); // Swap the file names to make the temporary file the new database file
    
            System.out.println("Item added successfully.");
    
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            input.close(); // Close the scanner in the finally block
            input1.close();
        }
    }
    

    public static void searchItem() {

        File database = new File("database.csv");

        try (FileReader Freader = new FileReader(database)) {

            try (
                BufferedReader reader = new BufferedReader(Freader);
                Scanner input = new Scanner(System.in);
            ) {
                
                String line;
   
                System.out.print("\nEnter Inventory ID to search: ");
                String searchID = input.next();
   
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
   
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
    }

    public static void displayInventory() {

        File file = new File("database.csv");

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
                System.out.printf(" %-65s", parts[3]);
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

    public static void Sales() {

        return;
    }
}
