import java.util.ArrayList;

public class Prompter {
    protected final static String DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
            GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
            SORT_AND_SEARCH_OPTIONS = "1). Name\n2). ID\n3). Price\n4). Quantity\n5). Brand\n",
            SEARCH_ORDER_OPTIONS = "1). A-Z/Highest to Lowest\n2). Z-A/Lowest to Highest\n";

    protected static GenericItem[] searchedMovies, sortedMovies;
    protected static String searchTerm;
    protected static ArrayList<GenericItem> inventory = new ArrayList<GenericItem>();

    protected static int displayChoice = 1, searchChoice, sortChoice, sortOrder;

    protected static void displayItems(int[] displayChoices) {
        displayChoice = displayChoices[0];
        searchChoice = displayChoices[1];
        sortChoice = displayChoices[2];
        sortOrder = displayChoices[3];
        if (displayChoice == 2) {
            System.out.println("What is your search term?");
            searchTerm = Input.getString();
        }
        else {searchTerm = "No Search Term";}
            sortedMovies = Display.sortAndSearch(inventory, searchTerm, searchChoice, (displayChoice == 1), sortOrder, sortChoice);
        for (int i = 0; i < sortedMovies.length; i++) {
            sortedMovies[i].display();
        }
    }

    public static void addDefaultItems() {
        inventory.add(new GenericItem("Bread", 3.99, 10, 1));
        inventory.add(new BrandedItem("Kraft", "Cheese", 2.99, 43, 2));
        inventory.add(new BrandedItem("Oreo", "Cookies", 5.99, 43, 3));
        inventory.add(new GenericItem("Milk", 2.49, 20, 4));
        inventory.add(new BrandedItem("Coca-Cola", "Soda", 1.99, 30, 5));
        inventory.add(new GenericItem("Eggs", 1.99, 15, 6));
        inventory.add(new BrandedItem("Kellogg's", "Cereal", 4.49, 25, 7));
        inventory.add(new GenericItem("Apples", 0.99, 50, 8));
        inventory.add(new BrandedItem("Colgate", "Toothpaste", 2.79, 15, 9));
        inventory.add(new GenericItem("Pasta", 1.49, 40, 10));
        inventory.add(new BrandedItem("Tide", "Laundry Detergent", 8.99, 10, 11));
        inventory.add(new GenericItem("Bananas", 0.49, 60, 12));
        inventory.add(new BrandedItem("Campbell's", "Soup", 1.29, 35, 13));
        inventory.add(new GenericItem("Potatoes", 1.99, 30, 14));
        inventory.add(new BrandedItem("Velveeta", "Mac & Cheese", 1.99, 25, 15));

        // Edge cases
        inventory.add(new GenericItem("Diamond Ring", 10000.0, 1, 16)); // Expensive and rare item
        inventory.add(new BrandedItem("None", "No-Brand", 0.01, 1000, 17)); // Cheap and high quantity
        inventory.add(new GenericItem("Water", 0.0, 10000, 18)); // Free item with a large quantity
    }

    public static char prompts() {
        return GrocersDatabase.QUIT;
    }
}
