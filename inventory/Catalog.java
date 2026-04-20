package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Catalog {
    
    private String catalogId;
    private LibraryItem[] libraryItems; 
    private int totalItems;

    public Catalog(String catalogId, int capacity) {
        this.catalogId = catalogId;
        this.libraryItems = new LibraryItem[capacity];
        this.totalItems = 0;
    }

  
    public void addItems(LibraryItem item) {
        if (totalItems < libraryItems.length) {
            libraryItems[totalItems] = item;
            totalItems++;
        } else {
            System.out.println("Catalog is full!!!!!");
        }
    }

  
    public LibraryItem searchByTitle(String title) {
        for (int i = 0; i < totalItems; i++) {
            if (libraryItems[i].getTitle().equalsIgnoreCase(title)) {
                return libraryItems[i];
            }
        }
        return null;
    }


    public void searchByGenre(String genre) {
        boolean found = false;
        for (int i = 0; i < totalItems; i++) {

            if (libraryItems[i].getGenre().equalsIgnoreCase(genre)) {
                System.out.println(libraryItems[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No items found in genre: " + genre);
    }


    public void displayItems() {
        for (int i = 0; i < totalItems; i++) {
            System.out.println(libraryItems[i]); 
        }
    }
}

