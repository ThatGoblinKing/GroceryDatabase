public class BrandedItem extends GenericItem {
  private final String DISPLAY_FORMAT = "ID: %08d\nItem: %s (%s)\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\nPrice: $%.2f\nQuantity: %d\n---------------------------------\n";
  private String brand;

  public BrandedItem(String brand, String itemType, double price, int quantity, int id) {
    super(itemType, price, quantity, id);
    this.brand = brand;
  }

  public void prepareForSearch(int varSelect) {
    super.prepareForSearch(varSelect);
    if (varSelect == 5) {
      this.searchVariable = this.brand;
    }
  }

  public void prepareForSort(int varSelect) {
    super.prepareForSort(varSelect);
    if (varSelect == 5) {
      this.sortVariable = this.brand;
    }
  }

  public void edit(String edit, int varSelect) {
    super.edit(edit, varSelect);
    if (varSelect == 5) {
      this.brand = edit;
    }
  }

  public void display() {
    System.out.printf(DISPLAY_FORMAT, this.id, this.itemType, this.brand, this.price, this.quantity);
  }
}
