import java.util.*;
import java.io.*;

public class Homepage {
    
    
    public static void main(String[] args) throws IOException {
        int choice;
        char exitAndContinue = 'Y';
        Scanner input = new Scanner(System.in);
        File database = new File("database.csv");
        File temporary = new File("database.tmp");
        File sales = new File("sales.csv");
        File tempSales = new File("sales.tmp");

        System.out.print("\n\nPHARMACY MANAGEMENT SYSTEM \t@powered by Java");
        System.out.println("\n------------------------------------------------");

        do {
            System.out.println("\n\nDirectory: ");
            System.out.print("1. Add Item \n2. Search Inventory \n3. Display Inventory \n4. Sales Information \n5. Exit.");

            System.out.print("\n\nChoose directory(1-5): ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        Directory.addItem(args, database, temporary, sales, tempSales);
                        swapFile.swapping(database, temporary);
                        swapFile.swapping(sales, tempSales);
                        break;
                    case 2:
                        Directory.searchItem(args, database);
                        break;
                    case 3:
                        Directory.displaying(database);
                        break;
                    case 4:
                        Directory.salesInfo();
                        break;
                    case 5:
                        System.out.println("\nExiting the program...");
                        System.out.println("Thank you for using our program...");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please choose a number between 1 and 5.");
                        continue; // Skip the rest of the loop and start over
                }

                System.out.print("\nDo you want to continue the program? (Y/N): ");
                if (input.hasNext()) {
                    exitAndContinue = input.next().charAt(0);
                } else {
                    System.out.println("\nNo input available. Exiting the program...");
                    exitAndContinue = 'N';
                }
            } else {
                System.out.println("\nInvalid input. Please enter a number between 1 and 5.");
                input.nextLine(); // Consume the invalid input to avoid an infinite loop
            }

        } while (exitAndContinue != 'N');
        
        System.out.println("\nThank you for using our program...");
    }
}
