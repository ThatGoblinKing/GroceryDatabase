import java.util.Scanner;

public final class Input {
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
            while (input.length() == 0 || !input.chars().allMatch(Character::isDigit)) {
                System.out.printf("Invalid input. Enter an integer between 1 and %d, these are your options\n%s",
                        listLength, list);
                input = stdIn.nextLine();
            }
            output = Integer.parseInt(input);
            if (output < 1 || output > listLength)
                input = "invalid";
        } while (output < 1 || output > listLength);
        return output;
    }

    public static int[] getDisplayChoices(String display, String itemPrompts, String searchOrder) {
        int[] returns = new int[4];
        returns[0] = getListChoice(display, 2);
        if (returns[0] == 2) {
            System.out.println("What would you like to search by?");
            returns[1] = getListChoice(itemPrompts, 5);
        }
        System.out.println("What would you like to sort by?");
        returns[2] = getListChoice(itemPrompts, 5);
        System.out.println("What order should the sort be in?");
        returns[3] = getListChoice(searchOrder, 2);
        return returns;
    }

    public static int[] getDisplayChoices(String display, String itemPrompts, String searchOrder, String extraOptions) {
        int[] returns = new int[4];
        returns[0] = getListChoice(display + extraOptions, 3);
        if (returns[0] > 2) {
            return returns;
        } else if (returns[0] == 2) {
            System.out.println("What would you like to search by?");
            returns[1] = getListChoice(itemPrompts, 5);
        }
        System.out.println("What would you like to sort by?");
        returns[2] = getListChoice(itemPrompts, 5);
        System.out.println("What order should the sort be in?");
        returns[3] = getListChoice(searchOrder, 2);
        return returns;
    }

    private static boolean validItem(String newItem) {
        boolean correctLength;
        try {
            String[] testedStrings = newItem.split(", ");
            correctLength = (testedStrings.length == 4 || testedStrings.length == 3);
            String price = testedStrings[testedStrings.length - 2],
                    quantity = testedStrings[testedStrings.length - 1];
            Double.parseDouble(price);
            Integer.parseInt(quantity);
        } catch (Exception e) {
            return false;
        }
        return (correctLength);

    }

    public static String[] splitNewItem() {
        final String PROMPT = "Please enter the details of your item, seperated by a comma and a space (, ) in the following order:\nBrand (Optional), Name, Price Without $, Quantity. I.E\n" + //
                "\"Organic Valley, Eggs, 3.99, 35\"";
        System.out.println(PROMPT);
        String newItem = stdIn.nextLine();
        while (!validItem(newItem)) {
            System.out.println(
                    "Sorry, that input wasn't readable. " + PROMPT);
            newItem = stdIn.nextLine();
        }
        return newItem.split(", ");
    }
}
