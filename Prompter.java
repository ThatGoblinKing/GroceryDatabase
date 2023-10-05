public class Prompter {
    protected final String
            DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
            GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
            SEARCH_ORDER_OPTIONS = "1). A-Z/Highest to Lowest\n2). Z-A/Lowest to Highest\n",
            LOGOUT = "3). Logout\n",
            PASSWORD = "password123";

    protected int displayChoice = 1, searchChoice, sortChoice, sortOrder;
    void displayItems() {
        int[] displayChoices = Input.getDisplayChoices(DISPLAY_OPTIONS, GENERIC_ITEM_OPTIONS,
                SEARCH_ORDER_OPTIONS);
        displayChoice = displayChoices[0];
        searchChoice = displayChoices[1];
        sortChoice = displayChoices[2];
        sortOrder = displayChoices[3];
        if (displayChoice == 2) {
            System.out.println("What is your search term?");
            searchTerm = Input.getString();
            if (searchChoice != 4) {
                searchedMovies = sortAndSearch.search(inventory, searchTerm, searchChoice);
            } else {
                System.out.println("Searching by year published");
                searchedMovies = sortAndSearch.search(inventory, searchTerm,
                        searchChoice);
            }
            if (searchedMovies.length > 0) {
                sortedMovies = sortAndSearch.sort(searchedMovies, sortOrder, sortChoice);
            } else {
                sortedMovies = new GenericItem[0];
            }
        } else {
            sortedMovies = sortAndSearch.sort(inventory, sortOrder, sortChoice);
        }
        for (int i = 0; i < sortedMovies.length; i++) {
            sortedMovies[i].display();
        }
    }
}
