public class GrocersDatabase {
    public static final char STARTING_MENU = 'S', INPUTTING_PASSWORD = 'P', MANAGER_MODE = 'M', USER_MODE = 'U', QUIT = 'Q';

    public static void main(String[] args) {
        final String PROGRAM_CLOSE = "\nThank you for using the Grocers Database. The program is now closing...";
        char userState = STARTING_MENU;

        Prompter.addDefaultItems();

        while (userState != QUIT) {
            switch (userState) {
                case MANAGER_MODE:
                    userState = Manager.prompts();
                    break;
                case USER_MODE:
                    userState = User.prompts();
                    break;
                case INPUTTING_PASSWORD:
                    userState = Prompter.getPassword();
                    break;
                case STARTING_MENU:
                    userState = Prompter.startingMenu();
                    break;
            }
        }
        System.out.println(PROGRAM_CLOSE);
    }
}