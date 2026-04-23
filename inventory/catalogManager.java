package inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jaclynnnnn
 */
public class CatalogManager {
    private final List<LibraryItem> itemList = new ArrayList<>();

    // Create - Polymorphism in action
    public void addItem(LibraryItem item) {
        itemList.add(item);
    }

    // Read (Show all)
    public void showAllItems() {
        if (itemList.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }
        System.out.println("\n--- Library Catalog ---");
        System.out.println("=".repeat(150));
        // Matches the format in your LibraryItem toString()
        System.out.printf("%-12s %-15s %-35s %-30s %-15s\n", "Status", "ISBN", "Title", "Author", "Special Info");
        System.out.println("=".repeat(150));
        for (LibraryItem item : itemList) {
            System.out.print(item.toString()); 
        }
        System.out.println("=".repeat(150));
    }

    // Update - Using the setters from LibraryItem
    public boolean updateItem(String isbn, String newTitle, String newAuthor) {
        LibraryItem item = findByIsbn(isbn);
        if (item != null) {
            item.setTitle(newTitle);
            // Ensure you added setAuthor(String author) to LibraryItem!
            item.setAuthor(newAuthor); 
            return true;
        }
        return false;
    }


    // Delete
    public boolean deleteItem(String isbn) {
        return itemList.removeIf(item -> item.getItemISBN().equalsIgnoreCase(isbn));
    }

    // Search by ISBN
    public LibraryItem findByIsbn(String isbn) {
        for (LibraryItem item : itemList) {
            if (item.getItemISBN().equalsIgnoreCase(isbn)) {
                return item;
            }
        }
        return null;
    }
    
    // Add this inside catalogManager.java
    public List<LibraryItem> getItemList() {
        return itemList;
    }

    public void saveData() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream("catalog.dat"))) {
            oos.writeObject(itemList);
        } catch (java.io.IOException e) { }
    }

    @SuppressWarnings("unchecked")
    public boolean loadData() {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("catalog.dat"))) {
            List<LibraryItem> loadedList = (List<LibraryItem>) ois.readObject();
            itemList.clear();
            itemList.addAll(loadedList);
            return true;
        } catch (Exception e) {
            return false; 
        }
    }
}