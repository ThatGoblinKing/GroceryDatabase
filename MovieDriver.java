import java.util.ArrayList;

public class MovieDriver {
    public static void main(String[] args) {

        // Constants
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        final String MANAGER_OPTIONS = "1). Edit an existing item listing\n2). Add a new item\n3). Delete an item\n4). Display inventory\n5). Exit manager mode\n6). Exit the progam\n",
                DISPLAY_OPTIONS = "1). Display all items\n2). Search inventory\n",
                GENERIC_ITEM_OPTIONS = "1). Item Name\n2). Item quantity\n3). Price\n",
                SEARCH_ORDER_OPTIONS = "1). A-Z/Highest to Lowest\n2). Z-A/Lowest to Highest\n",
                LOGOUT = "3). Logout\n",
                PASSWORD = "password123";

        // Inputs
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        int displayChoice = 1, managerChoice = 1, selectChoice, delChoice, editChoice, searchChoice, sortChoice,
                sortOrder;
        String passwordInput = "not inputted", editMovieInput, searchTerm;
        boolean continuing = true;

        // GenericItem Object
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        ArrayList<GenericItem> inventory = new ArrayList<GenericItem>();
        GenericItem[] searchedMovies, sortedMovies;
        String[] itemElements = new String[4];
        inventory.add(new GenericItem("Bread", 3.99, 10, 1));
        inventory.add(new BrandedItem("Kraft", "Cheese", 2.99, 43, 2));

        // Misc.
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        SortAndSearch sortAndSearch = new SortAndSearch();
        Inputs input = new Inputs();
        boolean itemSet = false;

        // Code Start
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println(
                "Input password to continue as a manager, \'continue\' to continue as a user, and 'q' to quit.");
        passwordInput = input.getString();
        continuing = !passwordInput.equalsIgnoreCase("q");
        while (continuing) {
            if (passwordInput.equals(PASSWORD)) {
                while (managerChoice != 6) {
                    managerChoice = input.getListChoice(MANAGER_OPTIONS, 6);
                    switch (managerChoice) {

                        case 1: // Edit an item
                            selectChoice = input.getListChoice(
                                    "Which item would you like to edit? (please enter the item's ID)",
                                    inventory.size());
                            System.out.println("What would you like to edit?");
                            editChoice = input.getListChoice(GENERIC_ITEM_OPTIONS, 4);
                            System.out.println("What would you like to change that to?");
                            editMovieInput = input.getString();
                            inventory.get(selectChoice).edit(editMovieInput, editChoice);
                            break;

                        case 2: // Add a item
                            itemElements = input.splitNewItem();

                            GenericItem newItem;
                            if (itemElements.length == 4)
                                newItem = new BrandedItem(itemElements[0], itemElements[1],
                                        Double.parseDouble(itemElements[2]), Integer.parseInt(itemElements[3]),
                                        inventory.size() + 1);
                            else if (itemElements.length == 3)
                                newItem = new GenericItem(itemElements[0], Double.parseDouble(itemElements[1]),
                                        Integer.parseInt(itemElements[2]), inventory.size() + 1);
                            else
                                newItem = null;

                            for (int i = 0; i < inventory.size(); i++) {
                                if (inventory.get(i) == null) {
                                    inventory.set(i, newItem);
                                    inventory.get(i).changeId(i + 1);
                                    itemSet = true;
                                    i = inventory.size();
                                }

                            }
                            if (!itemSet)
                                inventory.add(newItem);
                            break;

                        case 3: // Delete a item
                            delChoice = input.getListChoice(
                                    "Which item would you like to delete? (please enter the item's ID)",
                                    inventory.size());
                            inventory.set(delChoice - 1, null);
                            break;

                        case 4: // Display Movies
                            int[] displayChoices = input.getDisplayChoices(DISPLAY_OPTIONS, GENERIC_ITEM_OPTIONS,
                                    SEARCH_ORDER_OPTIONS);
                            displayChoice = displayChoices[0];
                            searchChoice = displayChoices[1];
                            sortChoice = displayChoices[2];
                            sortOrder = displayChoices[3];
                            if (displayChoice == 2) {
                                System.out.println("What is your search term?");
                                searchTerm = input.getString();
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
                            break;

                        case 5: // Quit
                            passwordInput = "continue";
                            managerChoice = 6;
                            break;
                        default:
                            System.out.println("Progam closing...");
                            return;
                    }
                    System.out.println("Task performed sucessfully");
                }
            }
            if (passwordInput.equalsIgnoreCase("continue") && displayChoice != 3) {
                System.out.println("Now entering user mode.");
                while (displayChoice != 3) {
                    int[] displayChoices = input.getDisplayChoices(DISPLAY_OPTIONS, GENERIC_ITEM_OPTIONS,
                            SEARCH_ORDER_OPTIONS, LOGOUT);
                    displayChoice = displayChoices[0];
                    if (displayChoice != 3) {
                        searchChoice = displayChoices[1];
                        sortChoice = displayChoices[2];
                        sortOrder = displayChoices[3];
                        if (displayChoice == 2) {
                            System.out.println("What is your search term?");
                            searchTerm = input.getString();
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
                        System.out.printf("%d result(s)\n", sortedMovies.length);
                        for (int i = 0; i < sortedMovies.length; i++) {
                            sortedMovies[i].display();
                        }
                    }
                }
                continuing = false;
            } else if (!passwordInput.equals(PASSWORD) && !passwordInput.equalsIgnoreCase("q")) {
                System.out.println("Password incorrect, please try again.");
                passwordInput = input.getString();
            }
        }
        System.out.println("Progam closing...");
    }
}