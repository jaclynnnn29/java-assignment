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
    private String phoneNumber;
    private static final int BORROW_LIMIT = 2;
    private static final int BORROW_DAYS = 7;

    public PublicMember(String userId, String userName, String userEmail, String phoneNumber) {
        super(userId, userName, userEmail, "Public");
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return super.toString() + " | Phone: " + phoneNumber + 
        " | Limit: " + BORROW_LIMIT + " books (" + BORROW_DAYS + " days)";
    }

}