import java.util.Scanner;
import transactions.TransactionManager;
import inventory.Book;
import inventory.DigitalBook;
import inventory.Journal;
import inventory.LibraryItem;
import inventory.catalogManager;
import users.Faculty;
import users.Librarian;
import users.PublicMember;
import users.Student;
import users.User;
import users.UserManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Main {
    private static final UserManager manager = new UserManager(); 
    private static catalogManager catalogManager = new catalogManager();
    private static final TransactionManager transManager = new TransactionManager();
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

        manager.addPublicMember("Ana", "ana@example.com", "023-456-7890");
        manager.addPublicMember("John", "john@example.com", "098-765-4321");

        while (true) {
            if (currentUser == null) {
                System.out.println("\n === Welcome to Library Management System! ===");
                System.out.println("1. Login Existing Account");
                System.out.println("2. Register New Account");
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
        System.out.print("Enter User ID to Login (e.g. L00X): ");
        String id = sc.nextLine();
        
        currentUser = manager.login(id);
        if (currentUser != null) {
            System.out.println("\n ========== Home Page ========== ");
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
        System.out.println("\n ===== REGISTER NEW USER ===== ");
        System.out.println("Select Type: ");
        System.out.println("1. Student ");
        System.out.println("2. Public Member");
        System.out.print("Choice: ");
        int type = readInt();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        switch (type) {
            
            case 1:
                System.out.print("Enter your Student ID (Number Only): ");
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
                System.out.print("Enter Phone Number: ");
                String phoneNumber = sc.nextLine();
                String newpmId = manager.addPublicMember(name, email, phoneNumber);
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
            System.out.println("\n===== CATALOG & BOOK SYSTEM =====");
            System.out.println("1. View All Items (Books/Journals)");
            System.out.println("2. Search by ISBN");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");

            
            // Check if user is staff (Librarian or Faculty)
            boolean isStaff = (currentUser instanceof Librarian || currentUser instanceof Faculty);
            
            if (isStaff) {
                System.out.println("5. Add New Item (Staff Only)");
                System.out.println("6. Delete Item (Staff Only)");
            }
            
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    catalogManager.showAllItems(); 
                    break;
                case 2:
                    searchISBN(); 
                    break;
                case 3:
                    handleBorrowing();
                    System.out.println("Borrowing functionality not implemented yet.");
                    break;
                case 4:
                    handleReturn();
                    System.out.println("Returning functionality not implemented yet.");
                    break;
                case 5:
                    if (isStaff) addNewItem(); // Call registration logic
                    else System.out.println("Access Denied!");
                    break;
                case 6:
                    if (isStaff) deleteItem(); // Call deletion logic
                    else System.out.println("Access Denied!");
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Error! Invalid selection.");
            }
        } while (choice != 0);
    }

    public static void searchISBN() {
        System.out.print("Enter ISBN to search: ");
        String isbn = sc.nextLine();
        LibraryItem item = catalogManager.findByIsbn(isbn);
        if (item != null) {
            System.out.println("\nItem Found:");
            System.out.printf("%-12s %-15s %-25s %-20s %-15s\n", "Status", "ISBN", "Title", "Author", "Special Info");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.print(item.toString());
        } else {
            System.out.println("No item found with ISBN: " + isbn);
        }
    }

    public static void addNewItem() {
        System.out.println("\n--- Add New Item ---");
        System.out.println("1. Add Book");
        System.out.println("2. Add Journal");
        System.out.println("3. Add Digital Book");
        System.out.print("Select type: ");
        int type = readInt();

        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        
        switch (type) {
            case 1:
                System.out.print("Enter Genre: ");
                String genre = sc.nextLine();
                LibraryItem newBook = new Book(isbn, title, author, genre);
                catalogManager.addItem(newBook);
                break;
            case 2:
                System.out.print("Enter Volume Number: ");
                int volumeNumber = readInt();
                LibraryItem newJournal = new Journal(isbn, title, author, volumeNumber);
                catalogManager.addItem(newJournal);
                break;
            case 3:
                System.out.print("Enter File Format: ");
                String fileFormat = sc.nextLine();
                System.out.print("Enter File Size (MB): ");
                double fileSize = sc.nextDouble();
                sc.nextLine(); // Consume newline
                LibraryItem newDigitalBook = new DigitalBook(isbn, title, author, fileFormat, fileSize);
                catalogManager.addItem(newDigitalBook);
                break;
            default:
                System.out.println("Invalid type! Item not added.");
        }

    }

    public static void deleteItem() {
        System.out.print("Enter ISBN of item to delete: ");
        String isbn = sc.nextLine();
        boolean success = catalogManager.deleteItem(isbn);
        if (success) {
            System.out.println("Item with ISBN " + isbn + " deleted successfully.");
        } else {
            System.out.println("No item found with ISBN: " + isbn);
        }
    }
    
    private static void handleBorrowing() {
    System.out.print("Enter ISBN to borrow: ");
    String isbn = sc.nextLine();
    LibraryItem item = catalogManager.findByIsbn(isbn);
    
    if (item != null) {
        transManager.borrowItem(currentUser, item);
    } else {
        System.out.println("Item not found.");
    }
    }

    private static void handleReturn() {
        System.out.print("Enter ISBN of the item you are returning: ");
        String isbn = sc.nextLine();
        transManager.returnItem(isbn, catalogManager);
    }

}
