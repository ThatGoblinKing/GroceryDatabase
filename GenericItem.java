public class GenericItem {
    private String name;
    private int id, quantity;
    private double price;
    public String searchVariable, sortVariable;

    public GenericItem(String name, double price, int quantity, int id) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.id = id;
    }

    public void prepareForSearch(int varSelect) {
        switch (varSelect) {
            case 1:
                this.sortVariable = this.name.toUpperCase();
                break;
            case 2:
                this.sortVariable = String.valueOf(this.id).toUpperCase();
                break;
            case 3:
                this.sortVariable = String.valueOf(this.price).toUpperCase();
                break;
            case 4:
                this.sortVariable = String.valueOf(this.quantity).toUpperCase();
                break;
        }
    }

    public void prepareForSort(int varSelect) {
        switch (varSelect) {
            case 1:
                this.searchVariable = this.name.toUpperCase();
                break;
            case 2:
                this.searchVariable = String.valueOf(this.id).toUpperCase();
                break;
            case 3:
                this.searchVariable = String.valueOf(this.price).toUpperCase();
                break;
            case 4:
                this.searchVariable = String.valueOf(this.quantity).toUpperCase();
                break;
        }
    }

    public void edit(String edit, int varSelect) {
        switch (varSelect) {
            case 1:
                this.name = edit;
                break;
            case 2:
                this.price = Double.parseDouble(edit);
                ;
                break;
            case 3:
                this.quantity = Integer.parseInt(edit);
                break;
        }
    }

    public void changeId(int changeTo) {
        this.id = changeTo;
    }

    public void display() {
        System.out.printf(
                "Item: %s\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \nPrice: $%.2f\nQuantity In Stock: %d\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \nID: %08d\n----------------------------\n",
                this.name, this.price, this.quantity, this.id);
    }
}
