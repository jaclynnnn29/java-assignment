package users;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
 
public class PublicMember extends User {
    private static final int BORROW_LIMIT = 2;
    private static final int BORROW_DAYS = 7;

    public PublicMember(String userId, String userName, String userEmail) {
        super(userId, userName, userEmail, "Public");
    }
    
    @Override
    public int getBorrowLimit() { 
        return BORROW_LIMIT; 
    }

    @Override
    public int getBorrowDuration() { 
        return BORROW_DAYS; 
    }

    @Override
    public String toString() {
        return super.toString() + " | Limit: " + BORROW_LIMIT + " books (" + BORROW_DAYS + " days)";
    }

}