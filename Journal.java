/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Journal extends Resource {
    private int issueNo;
    
    public Journal(String itemId, String title, String author, String genre, int issueNo){
        super(itemId, title, author, genre);
        this.issueNo = issueNo;
    }
    
    public String toString(){
        return super.toString() + issueNo;
    }
    
}


