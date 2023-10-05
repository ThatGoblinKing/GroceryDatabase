public class BrandedItem extends GenericItem {
  private String brand;

  public BrandedItem(String brand, String name, double price, int quantity, int id) {
    super(name, price, quantity, id);
    this.brand = brand;
  }

  public void prepareForSearch(int varSelect) {
    super.prepareForSearch(varSelect);
    if (varSelect > 4) {
      super.searchTerm = this.brand;
    }
  }

  public void edit(String edit, int varSelect) {
    super.edit(edit, varSelect);
    if (varSelect > 4) {
      this.brand = edit;
    }
  }

  public void display() {
    System.out.println("Brand: " + this.brand);
    super.display();
}
}
