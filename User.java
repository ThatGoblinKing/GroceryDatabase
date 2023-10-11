public final class User extends Prompter {
    private static final String USER_MODE = "User mode:\n\nWould you like to:";

    public static char prompts() {
        System.out.println(USER_MODE);
        int[] displayChoices = Input.getDisplayChoices(DISPLAY_OPTIONS, SORT_AND_SEARCH_OPTIONS,
                SEARCH_ORDER_OPTIONS, "3). Logout\n");
        if (displayChoices[0] == 3) {
            return GrocersDatabase.STARTING_MENU;
        }
        Prompter.displayItems(displayChoices);
        return GrocersDatabase.USER_MODE;
    }
}