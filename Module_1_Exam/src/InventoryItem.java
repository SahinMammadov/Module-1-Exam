public class InventoryItem {
    private int itemId;
    private String name;
    private int quantity;
    private double price;
    public InventoryItem(int itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
