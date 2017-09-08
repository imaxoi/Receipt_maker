public class Product {
  private String description;
  private int quantity;
  private float price;

  public boolean setDescription(String new_description) {
    if (new_description.isEmpty())
      return false;
    description = new_description;
    return true;
  }

  public boolean setQuantityFromString(String new_quantity) {
    if (new_quantity.isEmpty())
      return false;
    try {
      quantity = Integer.parseInt(new_quantity);
      if (quantity <= 0)
        return false;
      else
        return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  public boolean setPriceFromString(String new_price) {
    try {
      if (new_price.isEmpty())
        return false;
      price = Float.parseFloat(new_price);
      if (price <= 0)
        return false;
      else
        return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  public String getDescription() { return description; }

  public int getQuantity() { return quantity; }

  public float getPrice() { return price; }
}
