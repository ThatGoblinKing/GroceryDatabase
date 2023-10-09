public class GrocersDatabase {
    final public static char INPUTTING_PASSWORD = 'P', MANAGER_MODE = 'M', USER_MODE = 'U', QUIT = 'Q';

    public static void main(String[] args) {
        final String PASSWORD = "password123";
        char userState = INPUTTING_PASSWORD;
        String passwordInput = "not inputted";
        String passwordPrompt;

        Prompter.addDefaultItems();

        while (userState != QUIT) {

            while (userState == INPUTTING_PASSWORD) {
                if (passwordInput.equals(PASSWORD)) {
                    userState = MANAGER_MODE;
                } else if (passwordInput.equalsIgnoreCase("continue")) {
                    userState = USER_MODE;
                } else if (passwordInput.equalsIgnoreCase("q")) {
                    userState = QUIT;
                    break;
                } else {
                    passwordPrompt = passwordInput.equals("not inputted") ? "Input password to continue as a manager, "
                            : "Password incorrect, please try again. Enter ";
                    System.out.println(passwordPrompt + "\'continue\' to continue as user, or \'q\' to quit.");
                    passwordInput = Input.getString();
                }
            }
            passwordInput = "not inputted";

            switch (userState) {
                case MANAGER_MODE:
                    userState = Manager.prompts();
                    break;
                case USER_MODE:
                    userState = User.prompts();
                    break;
            }
        }
        System.out.println("Progam closing...");
    }
}
