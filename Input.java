import java.util.Scanner;

final public class Input {
    private static Scanner stdIn = new Scanner(System.in);

    public static String getString() {
        return stdIn.nextLine();
    }

    public static int getListChoice(String list, int listLength) {
        String input = "no input yet";
        int output = 0;
        System.out.print(list);
        input = stdIn.nextLine();
        do {
        while (!input.chars().allMatch(Character::isDigit) && input.length() > 0) {
            System.out.printf("Invalid input. Enter an integer between 1 and %d, these are your options\n%s",
                    listLength, list);
            input = stdIn.nextLine();
        }
            output = Integer.parseInt(input);
            if (output < 1 || output > listLength) input = "invalid";
        } while (output < 1 || output > listLength);
        return output;
    }

    public static int stringToInt(String inputString) {
        if (inputString.chars().allMatch(Character::isDigit)) {
            return Integer.parseInt(inputString);
        } else {
            return getInt("Please enter a valid year.");
        }
    }

    public static int getInt(String errorMessage) {
        System.out.println(errorMessage);
        while (!stdIn.hasNextInt()) {
            stdIn.next();
            System.out.println(errorMessage);
        }
        return stdIn.nextInt();
    }

    public static int[] getDisplayChoices(String display, String movie, String searchOrder) {
        int[] returns = new int[4];
        returns[0] = getListChoice(display, 2);
        if (returns[0] == 2) {
            System.out.println("What would you like to search by?");
            returns[1] = getListChoice(movie, 5);
        }
        System.out.println("What would you like to sort by?");
        returns[2] = getListChoice(movie, 5);
        System.out.println("What order should the sort be in?");
        returns[3] = getListChoice(searchOrder, 2);
        return returns;
    }

    public static int[] getDisplayChoices(String display, String movie, String searchOrder, String extraOptions) {
        int[] returns = new int[4];
        returns[0] = getListChoice(display + extraOptions, 4);
        if (returns[0] > 2) {
            return returns;
        } else if (returns[0] == 2) {
            System.out.println("What would you like to search by?");
            returns[1] = getListChoice(movie, 5);
        }
        System.out.println("What would you like to sort by?");
        returns[2] = getListChoice(movie, 5);
        System.out.println("What order should the sort be in?");
        returns[3] = getListChoice(searchOrder, 2);
        return returns;
    }


    public static String[] splitNewItem() {
        System.out.println("Please enter the details of your item, seperated by a comma and a space (, ) in the following order:\nBrand (Optional), Name, Price Without $, Quantity");
        return stdIn.nextLine().split(", ");
    }
}
