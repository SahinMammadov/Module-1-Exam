import Exceptions.FileIOException;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            InventoryManager manager = new InventoryManager();

            manager.addItem(new InventoryItem(1, "Laptop", 10, 999.99));
            manager.addItem(new InventoryItem(2, "Printer", 5, 299.99));

            List<InventoryItem> items = manager.readInventory();
            System.out.println("Inventory:");
            for (InventoryItem item : items) {
                System.out.println("Item ID: " + item.getItemId() + ", Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Price: $" + item.getPrice());
            }
        } catch (FileIOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}