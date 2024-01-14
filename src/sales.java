import java.util.*;
import java.io.*;

public class sales {

    public static void salesInfo() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Update Sales");
        System.out.println("2. Display Sales");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

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
                            parts[2] = String.valueOf(newQuantity);
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
                    System.out.printf("\nID  %-15s %-8s\n", "Name", "quantity sold(units)");
                    System.out.println("--------------------------------------");

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        System.out.printf("%-3s", parts[0]);
                        System.out.printf(" %-13s", parts[1]);
                        System.out.printf("\t   %-1s  ", parts[2]);
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

        scanner.close();
    }
}