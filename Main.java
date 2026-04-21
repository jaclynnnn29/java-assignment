import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Importing your custom packages
import users.*;
import inventory.*;
import transactions.*;

public class Main {
    private static final UserManager manager = new UserManager();
    private static final Scanner sc = new Scanner(System.in);
    private static User currentUser = null;
    
    // Initializing one Study Room for the library
    private static final StudyRoom studyRoom1 = new StudyRoom("SR-101", 4);

    public static void main(String[] args) {
        // Pre-loading Data (Your existing logic)
        manager.addLibrarian("JC", "admin@lib.com", 5);
        manager.addFaculty("Dr. Smith", "smith@faculty.com", "Computer Science");
        manager.addStudent("Alice", "alice@student.com", "S001");

        while (true) {
            if (currentUser == null) {
                System.out.println("\n=== Welcome to Smart Library System ===");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Choice: ");
                
                int choice = readInt();
                if (choice == 1) login();
                else if (choice == 2) break;
            } else {
                // Once logged in, go to the features menu
                bookManagement();
            }
        }
    }

    private static void login() {
        System.out.print("Enter User ID (e.g., L001, S001): ");
        String id = sc.nextLine();
        currentUser = manager.login(id);

        if (currentUser != null) {
            System.out.println("Login Successful! Welcome, " + currentUser.getuserName());
        } else {
            System.out.println("User not found.");
        }
    }

    public static void bookManagement() {
        int choice;
        do {
            System.out.println("\n--- Hello, " + currentUser.getuserName() + " ---");
            System.out.println("1. Borrow a Book (Demo)");
            System.out.println("2. Reserve Study Room");
            System.out.println("3. Check Study Room Status");
            System.out.println("4. Release Study Room");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    simulateBorrow();
                    break;
                case 2:
                    studyRoom1.reserveRoom(currentUser);
                    break;
                case 3:
                    studyRoom1.displayRoomStatus();
                    break;
                case 4:
                    studyRoom1.releaseRoom();
                    break;
                case 0:
                    currentUser = null;
                    System.out.println("Logged out...");
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
        } while (currentUser != null);
    }

    private static void simulateBorrow() {
        // 1. Create a dummy book from inventory package
        Book book = new Book("Java Design Patterns", "ISBN-999", "E. Gamma", "Tech");
        
        // 2. Create a transaction from transactions package
        // Notice it uses currentUser.getBorrowDuration() - Polymorphism!
        Transaction trans = new Transaction("T101", currentUser, book.getItemISBN(), currentUser.getBorrowDuration());
        
        System.out.println("\n[Transaction Created]");
        System.out.println("Book Title: " + book.getTitle());
        System.out.println("Due Date  : " + trans.getDueDate());
        
        // 3. Simulate a Fine using FineCalculator
        FineCalculator fc = new FineCalculator();
        double fine = fc.calculateTotalFine(trans, 3); // Simulating 3 days late
        System.out.println("Fine if 3 days late: $" + fine);
    }

    // Helper method to handle scanner errors
    private static int readInt() {
        try {
            int val = Integer.parseInt(sc.nextLine());
            return val;
        } catch (Exception e) {
            return -1;
        }
    }
}