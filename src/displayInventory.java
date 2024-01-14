import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class displayInventory {
    
    public static void displaying() {

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

}
