import java.util.ArrayList;

public class MovieDriver {
    public static void main(String[] args) {

        // Constants
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Inputs
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        int displayChoice = 1, managerChoice = 1, selectChoice, delChoice, editChoice, searchChoice, sortChoice;
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
        boolean itemSet = false;

        // Code Start
        // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println(
                "Input password to continue as a manager, \'continue\' to continue as a user, and 'q' to quit.");
        passwordInput = Input.getString();
        continuing = !passwordInput.equalsIgnoreCase("q");
        while (continuing) {
            if (passwordInput.equals(PASSWORD)) {

            if (passwordInput.equalsIgnoreCase("continue") && displayChoice != 3) {

                }
                continuing = false;
            } else if (!passwordInput.equals(PASSWORD) && !passwordInput.equalsIgnoreCase("q")) {
                System.out.println("Password incorrect, please try again.");
                passwordInput = Input.getString();
            }
        }
        System.out.println("Progam closing...");
    }
}
