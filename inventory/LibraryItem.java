package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public abstract class LibraryItem {
    private String title;
    private String itemISBN; 
    private String status = "Available"; // available, borrowed, reserved
    private String author; 
    private boolean available = true;

    //public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_BORROWED = "Borrowed";
    public static final String STATUS_RESERVED = "Reserved";
    
    public LibraryItem(String title, String itemISBN, String author) {
        this.title = title;
        this.itemISBN = itemISBN;
        this.author = author;
    }
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    
    public String getItemISBN(){
        return itemISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
        
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable() { // Added this getter
        return available;
    }

    public void setAvailable(boolean available) { // Added this setter
        this.available = available;
    }
    
    @Override
    public String toString(){
        return String.format("%-12s %-15s %-35s %-30s ", status, itemISBN, title, author);
    }
}
