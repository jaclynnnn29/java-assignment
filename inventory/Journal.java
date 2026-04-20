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
    private int issueNo;
    
    public Journal(String itemId, String title, String author, String genre, int issueNo){
        super(title, itemId, genre, author);
        this.issueNo = issueNo;
    }
    
    public String toString(){
        return super.toString() + String.format(" | Issue: %-5d", issueNo);
    }
    
}
