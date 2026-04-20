
import java.util.Scanner;

import users.PublicMember;
import users.Student;
import users.User;
import users.userManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class uMain {
    private static final userManager manager = new userManager(); 
    private static final Scanner sc = new Scanner(System.in); 
    private static User currentUser = null; 

    public static void main(String[] args) {
        
        manager.addLibrarian("JC", "admin@lib.com", 5);
        manager.addLibrarian("LY", "ly@lib.com", 4);
        manager.addLibrarian("EX", "ex@lib.com", 3);

        manager.addFaculty("ABC", "abc@faculty.com", "Computer Science");
        manager.addFaculty("GG", "gg@faculty.com", "Mathematics");

        manager.addStudent("Alice", "alice@student.com", "S001");
        manager.addStudent("Bob", "bob@student.com", "S002");

        manager.addPublicMember("Ana", "ana@example.com");
        manager.addPublicMember("John", "john@example.com");

        while (true) {
            if (currentUser == null) {
                System.out.println("\n === Welcome to Library Management System! ===");
                System.out.println("1. Login");
                System.out.println("2. New User");
                System.out.println("0. Exit System");
                System.out.print("Choice: ");
                
                int n = readInt();
                
                if (n == 0) {
                System.out.println("Goodbye!");
                break; 
                }
               
                switch (n) {
                    case 1:
                        Login();
                        break;
                    case 2:
                        register(); 
                        break;
                    default:
                        System.out.println("Error! Invalid selection.");
                }
            } else {
                if (currentUser instanceof Student || currentUser instanceof PublicMember) {
                    bookManagement();
                } else {
                    display();
                }
            }
        }
    }

    private static void Login() {
        System.out.println("Login Page");
        System.out.print("\nEnter User ID to Login (e.g., L001): ");
        String id = sc.nextLine();
        
        currentUser = manager.login(id);
        if (currentUser != null) {
            System.out.println("Login Success! Hello, " + currentUser.getuserName());
            System.out.println("Your Borrow Limit is: " + currentUser.getBorrowLimit() + 
                            " Your Duration is: " + currentUser.getBorrowDuration() + " days");
        } else {
            System.out.println("User not found! Please check your ID or register.");
        }
    }

    public static void display() {
        int choice;
        do {
            System.out.println("\n========== USER MANAGEMENT ==========");
            System.out.println("1. View All Users Detail (Staff Only)");
            System.out.println("2. Update User Information (Staff Only)");
            System.out.println("3. Delete User (Staff Only)");
            System.out.println("4. Book Management");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    manager.showAll();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    bookManagement();
                    break;
                case 0:
                    currentUser = null;
                    System.out.println("Logged out...");
                    break;
                default:
                    System.out.println("Invalid Input. Please enter a NUMBER above.");
            }
        } while (currentUser != null);
    }

    // Ensure that the user enters a number
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input error! Please enter a NUMBER: ");
            }
        }
    }

    // Registration 
    private static void register() {
        System.out.println("\n === REGISTER NEW USER === ");
        System.out.println("Select Type: ");
        System.out.println("1. Student ");
        System.out.println("2. Faculty ");
        System.out.println("3. Librarian");
        System.out.println("4. Public");
        System.out.print("Choice: ");
        int type = readInt();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        switch (type) {
            
            case 1:
                System.out.print("Enter your Real Student ID : ");
                String studentId = sc.nextLine();
                String newId = manager.addStudent(name, email, studentId);
                System.out.println("\nWelcome to our Library\n");
                System.out.println("Registration Success for: " + name);
                System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newId );
                System.out.println("-------------------------------------------");
                break;

            case 2:
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                String newfacultyId = manager.addFaculty(name, email, dept);
                System.out.println("\n-------------------------------------------");
                System.out.println("Registration Success for: " + name);
                System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newfacultyId );
                System.out.println("-------------------------------------------");
                break;

            case 3:
                System.out.print("Enter Staff Level (1-5): ");
                int level = readInt();
                String newlibId = manager.addLibrarian(name, email, level);
                System.out.println("\n-------------------------------------------");
                System.out.println("Registration Success for: " + name);
                System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newlibId );
                System.out.println("-------------------------------------------");
                break;
            case 4:
                String newpmId = manager.addPublicMember(name, email);
                System.out.println("\n-------------------------------------------");
                System.out.println("Registration Success for: " + name);
                System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newpmId );
                System.out.println("-------------------------------------------");
                break;
            default:
                System.out.println("Wrong selection! Registration cancelled.");
        }
    }

    // Update 
    private static void update() {
        System.out.print("Enter User ID to update: ");
        String id = sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Email: ");
        String email = sc.nextLine();
        manager.updateUser(id, name, email);
    }

    // Deletion
    private static void delete() {
        System.out.print("Enter User ID to delete: ");
        String id = sc.nextLine();
        manager.deleteUser(id);
    }
    
   
    public static void bookManagement() {
    int choice;
        do {
            System.out.println("\n===== SMART LIBRARY SYSTEM =====");
            System.out.println("1. Borrow Book"); 
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("borrowBook()");
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
    

}


