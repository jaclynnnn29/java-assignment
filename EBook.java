/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class EBook extends Resource {
    private double fileSize;
    
    public EBook(String title, String id, String genre, double fileSize){
        super(title, id, genre);
        this.fileSize = fileSize;
    }
    
    public void displayDetails(){
         System.out.printf("\n%-10s %-25s %s\n", "ID: " , "Title: ", "Status: ", getId(), getTitle(), fileSize, getStatus());
    }
}
