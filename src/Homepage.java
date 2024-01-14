import java.io.*;
import java.util.*;

public class Homepage {

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
            choice = input.nextInt();

            switch(choice) {

                case 3: displayInventory.displaying(); break;
                case 4: sales.salesInfo(); break;
                case 5: break;
            }

            if(choice == 5) {
                System.out.println("Exiting the program..."); 
                System.exit(0);
            }
            else {

                System.out.print("\nDo you want to continue the program? (Y/N): ");
                exitAndContinue = input.next().charAt(0);
            }

        } while (exitAndContinue != 'N');
        
        input.close();
    }
}