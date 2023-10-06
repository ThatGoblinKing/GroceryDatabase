import java.util.ArrayList;

public class Prompter {
    protected final static String
            DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
            GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
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
            if (searchChoice != 4) {
                searchedMovies = Display.search(inventory, searchTerm, searchChoice);
            } else {
                System.out.println("Searching by year published");
                searchedMovies = Display.search(inventory, searchTerm,
                        searchChoice);
            }
            if (searchedMovies.length > 0) {
                sortedMovies = Display.sort(searchedMovies, sortOrder, sortChoice);
            } else {
                sortedMovies = new GenericItem[0];
            }
        } else {
            sortedMovies = Display.sort(inventory, sortOrder, sortChoice);
        }
        for (int i = 0; i < sortedMovies.length; i++) {
            sortedMovies[i].display();
        }
    }

    public static void addDefaultItems(){
        inventory.add(new GenericItem("Bread", 3.99, 10, 1));
        inventory.add(new BrandedItem("Kraft", "Cheese", 2.99, 43, 2));
    }

    public static char prompts() {
        return GrocersDatabase.QUIT;
    }
}
