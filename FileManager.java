import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public final class FileManager {
    private static File savedInventory;
    private static Scanner fileReader;

    public static ArrayList<GenericItem> addDefaultItems() {
        ArrayList<GenericItem> readInventory = new ArrayList<GenericItem>();
        try {
            savedInventory = new File("SavedInventory.txt");
            fileReader = new Scanner(savedInventory);
            int counter = 0;
            while (fileReader.hasNextLine()) {
                counter++;
                String[] currentItem = fileReader.nextLine().split(", ");
                ;
                if (currentItem.length == 3) {
                    readInventory.add(new GenericItem(currentItem[0], Double.parseDouble(currentItem[1]),
                            Integer.parseInt(currentItem[2]), counter));
                } else {
                    readInventory.add(new BrandedItem(currentItem[0], currentItem[1],
                            Double.parseDouble(currentItem[2]), Integer.parseInt(currentItem[3]), counter));
                }
            }
            System.out.printf("Sucessfully parsed %d items\n", counter);
            fileReader.close();
        } catch (Exception e) {
            System.out.println("An error occured whilst reading the inventory.");
        }
        return readInventory;
    }

    public static void saveInventory(ArrayList<GenericItem> unsavedInventory) {
        String toWrite = "";
        for (GenericItem item : unsavedInventory) {
            if (item != null) {
            toWrite += item.save() + "\n";
            }
        }
        try {
            PrintWriter writer = new PrintWriter(savedInventory);
            writer.print(toWrite);
            writer.close();
        } catch (Exception e) {
            System.out.println("There was an error saving the inventory");
        }

    }
}