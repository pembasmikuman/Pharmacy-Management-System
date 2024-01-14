/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compile;
import java.io.IOException;
import java.util.Scanner;

public class HomepageNew {

    public static void main(String[] args) throws IOException {
        int choice;
        char exitAndContinue = 'Y';
        Scanner input = new Scanner(System.in);

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
                        DirectoryNew.addItem(args);
                        break;
                    case 2:
                        DirectoryNew.searchItem(args);
                        break;
                    case 3:
                        DirectoryNew.displaying();
                        break;
                    case 4:
                        DirectoryNew.salesInfo();
                        break;
                    case 5:
                        System.out.println("Exiting the program...");
                        exitAndContinue = 'N';
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a number between 1 and 5.");
                        continue; // Skip the rest of the loop and start over
                }

                System.out.print("\nDo you want to continue the program? (Y/N): ");
                if (input.hasNext()) {
                    exitAndContinue = input.next().charAt(0);
                } else {
                    System.out.println("No input available. Exiting the program...");
                    exitAndContinue = 'N';
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                input.nextLine(); // Consume the invalid input to avoid an infinite loop
            }

        } while (exitAndContinue != 'N');
        
        System.out.println("Thank you for using our program...");
    }
}