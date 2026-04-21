package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Book extends LibraryItem {
    private String genre;// category
    
    public Book(String title, String itemISBN, String author, String genre) {
        super(title, itemISBN, author);
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("%-15s\n", genre);
    }
}

 