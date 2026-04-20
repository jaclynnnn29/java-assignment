/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Librarian extends User { 
    private final int staffLevel;

    public Librarian(String userId, String userName, String userEmail, int staffLevel) {
        super(userId, userName, userEmail, "Librarian");
        this.staffLevel = staffLevel;
    }
    
    @Override 
    public String toString() {
        return super.toString() + " | Staff Level: " + staffLevel;
    }
    
}