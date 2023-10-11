public class GenericItem {
    protected String itemType;
    protected int id, quantity;
    protected double price;
    public String searchVariable, sortVariable;
    private final String DISPLAY_FORMAT = "ID: %08d\nItem: %s\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\nPrice: $%.2f\nQuantity: %d\n---------------------------------\n";

    public GenericItem(String itemType, double price, int quantity, int id) {
        this.price = price;
        this.quantity = quantity;
        this.itemType = itemType;
        this.id = id;
    }

    public void prepareForSearch(int varSelect) {
        switch (varSelect) {
            case 1:
                this.searchVariable = this.itemType.toUpperCase();
                break;
            case 2:
                this.searchVariable = String.format("%08d", this.id).toUpperCase();
                break;
            case 3:
                this.searchVariable = String.format("%08.2f", this.price);
                break;
            case 4:
                this.searchVariable = String.format("%08d", this.quantity);
                break;
            case 5:
                this.searchVariable = ""; // Prevents Null Exception error, while allow removing it from any searches.
                break;
        }
    }

    public void prepareForSort(int varSelect) {
        switch (varSelect) {
            case 1:
                this.sortVariable = this.itemType.toUpperCase();
                break;
            case 2:
                this.sortVariable = String.format("%08d", this.id).toUpperCase();
                break;
            case 3:
                this.sortVariable = String.format("%08.2f", this.price);
                break;
            case 4:
                this.sortVariable = String.format("%08d", this.quantity);
                break;
            case 5:
                this.sortVariable = "~"; // This ensures that an item without a brand will always be lexicographically lower than those that do.
        }
    }

    public void edit(String edit, int varSelect) {
        switch (varSelect) {
            case 1:
                this.itemType = edit;
                break;
            case 2:
                try {
                this.quantity = Integer.parseInt(edit);
                } catch(Exception e) {
                    System.out.println("Sorry, that wasn't readable. Make sure to enter an integer.");
                    edit(Input.getString(), 2);
                }
                break;
            case 3:
                try {
                this.price = Double.parseDouble(edit);
                } catch(Exception e) {
                    System.out.println("Sorry, that wasn't readable. Make sure to enter a double without a $.");
                    edit(Input.getString(), 3);
                }
                break;
        }
    }

    public void changeId(int changeTo) {
        this.id = changeTo;
    }

    public void display() {
        System.out.printf(DISPLAY_FORMAT,
                this.id, this.itemType, this.price, this.quantity);
    }
}
