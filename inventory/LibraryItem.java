package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class LibraryItem {
    private String title;
    private String itemId;
    private String genre; // category
    private String status; // available, borrowed, reserved
    private String author; 

    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_BORROWED = "Borrowed";
    public static final String STATUS_RESERVED = "Reserved";
    
    public LibraryItem(String title, String itemId, String genre, String author) {
        this.title = title;
        this.itemId = itemId;
        this.author = author;
        this.genre = genre;
        this.status = STATUS_AVAILABLE;
    }
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    
    public String getItemId(){
        return itemId;
    }
    
    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String toString(){
        return String.format("%-12s %-15s %-25s %-20s %-15s\n", status, itemId, title, author, genre);
    }
}
