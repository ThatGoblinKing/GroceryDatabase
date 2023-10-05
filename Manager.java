public class Manager extends Prompter {
    final static String MANAGER_OPTIONS = "1). Edit an existing item listing\n2). Add a new item\n3). Delete an item\n4). Display inventory\n5). Exit manager mode\n6). Exit the program\n";
    private static int managerChoice = 1, selectChoice, delChoice, editChoice;
    public static void prompts(){
        while (managerChoice != 6) {
            managerChoice = Input.getListChoice(MANAGER_OPTIONS, 6);
            switch (managerChoice) {

                case 1: // Edit an item
                    selectChoice = Input.getListChoice(
                            "Which item would you like to edit? (please enter the item's ID)",
                            inventory.size());
                    System.out.println("What would you like to edit?");
                    editChoice = Input.getListChoice(GENERIC_ITEM_OPTIONS, 4);
                    System.out.println("What would you like to change that to?");
                    editMovieInput = Input.getString();
                    inventory.get(selectChoice).edit(editMovieInput, editChoice);
                    break;

                case 2: // Add a item
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
                    break;

                case 3: // Delete a item
                    delChoice = Input.getListChoice(
                            "Which item would you like to delete? (please enter the item's ID)",
                            inventory.size());
                    inventory.set(delChoice - 1, null);
                    break;

                case 4: // Display Movies

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
}
