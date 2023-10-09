final public class User extends Prompter {
    public static char prompts() {
        int[] displayChoices = Input.getDisplayChoices(DISPLAY_OPTIONS, SORT_AND_SEARCH_OPTIONS,
                SEARCH_ORDER_OPTIONS, "3). Enter Managerial Credentials\n4). Logout\n");
        if (displayChoices[0] == 3) {
            return GrocersDatabase.INPUTTING_PASSWORD;
        } else if(displayChoices[0] == 4) {
            return GrocersDatabase.QUIT;
        }
        Prompter.displayItems(displayChoices);
        return GrocersDatabase.USER_MODE;
    }
}