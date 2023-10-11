import java.util.ArrayList;

public class Prompter {
    protected static final String DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
            GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
            SORT_AND_SEARCH_OPTIONS = "1). Name\n2). ID\n3). Price\n4). Quantity\n5). Brand\n",
            SEARCH_ORDER_OPTIONS = "1). A-Z/Highest to Lowest\n2). Z-A/Lowest to Highest\n";

    protected static GenericItem[] searchedItems, sortedItems;
    protected static String searchTerm;
    protected static ArrayList<GenericItem> inventory = new ArrayList<GenericItem>();
    private static final String STARTING_PROMPT = "Welcome to Grocers Database!\n------------------------------\nThis is a program used to keep track of item batches in a grocery store.\n\nWould you like to:",
            STARTING_OPTIONS = "1). Enter user mode\n2). Enter manager mode\n3). Quit the program\n";

    private static String passwordInput = "not inputted", passwordPrompt;

    protected static int displayChoice = 1, searchChoice, sortChoice, sortOrder;

    protected static void displayItems(int[] displayChoices) {
        displayChoice = displayChoices[0];
        searchChoice = displayChoices[1];
        sortChoice = displayChoices[2];
        sortOrder = displayChoices[3];
        if (displayChoice == 2) {
            System.out.println("What is your search term?");
            searchTerm = Input.getString();
        } else {
            searchTerm = "No Search Term";
        }
        sortedItems = Display.sortAndSearch(inventory, searchTerm, searchChoice, (displayChoice == 1), sortOrder,
                sortChoice);
        for (GenericItem item : sortedItems) {
            item.display();
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
    }

    public static char getPassword() {
        final String PASSWORD = "password123";
        if (passwordInput.equals(PASSWORD)) {
            passwordInput = "not inputted";
            return GrocersDatabase.MANAGER_MODE;
        } else if (passwordInput.equalsIgnoreCase("Q")) {
            return GrocersDatabase.STARTING_MENU;
        } else {
            passwordPrompt = passwordInput.equals("not inputted")
                    ? "Input password to continue as a manager. (Or type \'Q\' to go back)"
                    : "Password incorrect, please try again. (Or type \'Q\' to go back) ";
            System.out.println(passwordPrompt);
            passwordInput = Input.getString();
            return GrocersDatabase.INPUTTING_PASSWORD;
        }
    }

    public static char startingMenu() {
        System.out.println(STARTING_PROMPT);
        switch (Input.getListChoice(STARTING_OPTIONS, 3)) {
            case 1:
                return GrocersDatabase.USER_MODE;
            case 2:
                return GrocersDatabase.INPUTTING_PASSWORD;
            default:
                return GrocersDatabase.QUIT;
        }
    }

}
