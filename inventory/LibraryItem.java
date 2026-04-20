package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
class LibraryItem {
    private String title;
    private String itemId;
    private String genre; // category
    private String status; // available, borrowed, reserved
    private String author; 
    
    public LibraryItem(String title, String itemId, String genre, String author) {
        this.title = title;
        this.itemId = itemId;
        this.author = author;
        this.genre = genre;
        this.status = "Available";
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getGenre(){
        return genre;
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
