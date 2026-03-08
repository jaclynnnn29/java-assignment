/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Book extends Resource {
    private int pageCount;
    
    public Book(String title, String id, String genre, int pageCount){
        super(title, id, genre);
        this.pageCount = pageCount;
    }
    
    public void displayDetails(){
        System.out.printf("\n%-10s %-25s %s\n", "ID: " , "Title: ", "Status: ", getId(), getTitle(), pageCount, getStatus());
    }
    
}
