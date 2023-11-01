import java.util.ArrayList;

public class Prompter {
    protected static final String DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
            GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
            SORT_AND_SEARCH_OPTIONS = "1). Name\n2). ID\n3). Price\n4). Quantity\n5). Brand\n",
            SEARCH_ORDER_OPTIONS = "1). A-Z/Highest to Lowest\n2). Z-A/Lowest to Highest\n";
    private static final String STARTING_PROMPT = "Welcome to Grocers Database!\n------------------------------\nThis is a program used to keep track of item batches in a grocery store.\n\nWould you like to:",
            STARTING_OPTIONS = "1). Enter user mode\n2). Enter manager mode\n3). Quit the program\n";

    protected static GenericItem[] searchedAndSortedItems;
    protected static String searchTerm;
    protected static ArrayList<GenericItem> inventory = FileManager.addDefaultItems();

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
        searchedAndSortedItems = Display.sortAndSearch(inventory, searchTerm, searchChoice, (displayChoice == 1), sortOrder,
                sortChoice);
        for (GenericItem item : searchedAndSortedItems) {
            System.out.print(item);
        }
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
