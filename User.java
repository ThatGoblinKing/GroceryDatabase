public class User extends Prompter {
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
