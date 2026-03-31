/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Main {    
    public static void main(String[] args) {

        // 1. Create a User using your constructor: (userId, userName, userEmail, userType)
        User student = new User(101, "CeeXi", "ceexi@library.edu", "Student");

        // 2. Create a Resource using your constructor: (title, itemId, genre, author)
        Resource book = new Resource("Effective Java", "B001", "Programming", "Joshua Bloch");

        System.out.println("--- System Initialization ---");
        System.out.println(student.toString());
        System.out.println("Resource Title: " + book.getTitle());
        

        // 3. Test Loan Class
        // Ensure Loan.java uses getuserName() and getTitle()
        System.out.println("\n--- Processing Loan ---");
        Loan loan1 = new Loan("L-001", student, book);
        loan1.displayLoanDetails();

        // 4. Test Reservation Class
        System.out.println("\n--- Processing Reservation ---");
        Reservation res1 = new Reservation("RES-99", student, book);
        res1.displayReservation();

        // 5. Test Inter-Library Request
        System.out.println("\n--- Processing ILL Request ---");
        InterLibraryLoanRequest ill = new InterLibraryLoanRequest("ILL-55", student, book, "State Library");
        ill.approveRequest();
        ill.displayRequestDetails();

        // 6. Test Fine Class
        // Note: The fine will be RM0.0 because the loan was just created today
        System.out.println("\n--- Fine Status ---");
        Fine fine1 = new Fine("F-123", loan1);
        fine1.displayFineDetails();

        // 7. Test Return Logic
        System.out.println("\n--- Returning Resource ---");
        loan1.returnResource();
        
        // Check if the status changed back to 'Available' via Resource's toString
        System.out.println("Current Resource Status:" + book.toString());
    }   
}