public final class Manager extends Prompter {
    private static final String MANAGER_OPTIONS = "1). Edit an existing item listing\n2). Add a new item\n3). Delete an item\n4). Display inventory\n5). Logout\n",
            MANAGER_MODE = "User mode:\n\nWould you like to:";
    private static int managerChoice = 1, selectChoice, delChoice, editChoice;
    private static String editItemInput;
    private static String[] itemElements;
    private static boolean itemSet;

    public static char prompts() {
        System.out.println(MANAGER_MODE);
        managerChoice = Input.getListChoice(MANAGER_OPTIONS, 5);

        switch (managerChoice) {
            case 1:
                editItem();
                break;

            case 2:
                addItem();
                break;

            case 3:
                deleteItem();
                break;

            case 4:
                int[] displayChoices = Input.getDisplayChoices(DISPLAY_OPTIONS, SORT_AND_SEARCH_OPTIONS,
                        SEARCH_ORDER_OPTIONS);
                Prompter.displayItems(displayChoices);
                break;

            default:
                return GrocersDatabase.STARTING_MENU;
        }

        System.out.println("Task performed successfully");
        return GrocersDatabase.MANAGER_MODE;
    }

    private static void editItem() {
        selectChoice = Input.getListChoice(
                "Which item would you like to edit? (please enter the item's ID): ",
                inventory.size());
        System.out.println("What would you like to edit?");
        if (inventory.get(selectChoice - 1) instanceof BrandedItem) {
            editChoice = Input.getListChoice(GENERIC_ITEM_OPTIONS + "4). Brand\n", 4);
        } else {
            editChoice = Input.getListChoice(GENERIC_ITEM_OPTIONS, 3);
        }
        System.out.println("What would you like to change that to?");
        editItemInput = Input.getString();
        inventory.get(selectChoice - 1).edit(editItemInput, editChoice);
        FileManager.saveInventory(inventory);
    }

    private static void addItem() {
        itemElements = Input.splitNewItem();

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
        FileManager.saveInventory(inventory);
    }

    private static void deleteItem() {
        delChoice = Input.getListChoice(
                "Which item would you like to delete? (please enter the item's ID): ",
                inventory.size());
        inventory.set(delChoice - 1, null);
        FileManager.saveInventory(inventory);
    }
}
