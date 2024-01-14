import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class addItem {

    public static void main(String[] args) {

        File database = new File("database.csv");
        File temporary = new File("database.tmp");
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
    
            System.out.println("Item added successfully.");
    
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            input.close(); // Close the scanner in the finally block
            input1.close();
        }
    }
   
    
}
