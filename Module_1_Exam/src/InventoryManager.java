import Exceptions.FileIOException;
import Exceptions.InvalidDataException;
import Exceptions.ItemNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static final String FILE_NAME = "C:\\Users\\mamma\\OneDrive\\Desktop\\manager.txt";

    public List<InventoryItem> readInventory() throws FileIOException {
        List<InventoryItem> items = new ArrayList<>();
        try (BufferedReader bfreader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bfreader.readLine()) != null) {
                String[] parts = line.split(",");
                int itemId = Integer.parseInt(parts[0]);
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                InventoryItem item = new InventoryItem(itemId, name, quantity, price);
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            throw new FileIOException("File not found!: " + FILE_NAME);
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FileIOException("File read error: " + e.getMessage());
        }
        return items;
    }

    public void writeInventoryItem(List<InventoryItem> items) throws FileIOException{
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (InventoryItem item : items) {
                bufferedWriter.write(item.getItemId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getPrice());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new FileIOException("File writer error: " + FILE_NAME);
        }
    }

    public void addItem(InventoryItem item) throws FileIOException {
        try {
            List<InventoryItem> items = readInventory();
            items.add(item);
            writeInventoryItem(items);
        } catch (FileIOException e) {
            throw new FileIOException("Error adding item: " + e.getMessage());
        }
    }
    public void deleteItem(int itemId) throws FileIOException, ItemNotFoundException {
        try {
            List<InventoryItem> items = readInventory();
            InventoryItem itemToDelete = null;
            for (InventoryItem item : items) {
                if (item.getItemId() == itemId) {
                    itemToDelete = item;
                    break;
                }
            }
            if (itemToDelete == null) {
                throw new ItemNotFoundException("Item with ID " + itemId + " not found.");
            }
            items.remove(itemToDelete);
            writeInventoryItem(items);
        } catch (FileIOException e) {
            throw new FileIOException("Error deleting item: " + e.getMessage());
        }
    }
}

