/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Book extends Resource {
    private String isbn;
    
    public Book(String itemId, String title, String author, String genre, String isbn){
        super(title, itemId, genre, author);
        this.isbn = isbn;
    }
}
