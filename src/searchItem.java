import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class searchItem {
    
    public static void main(String[] args) throws IOException {
        
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
        File database = new File("database.csv");
        File temporary = new File("database.tmp");

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
        input.next().charAt(0);

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

}
