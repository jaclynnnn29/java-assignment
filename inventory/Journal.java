package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Journal extends LibraryItem {
    private int volumeNumber;
    
    public Journal(String itemId, String title, String author, int volumeNumber){
        super(title, itemId, author);
        this.volumeNumber = volumeNumber;
    }
    
    public String toString(){
        return super.toString() + String.format(" | Volume: %-5d", volumeNumber);
    }
    
}
