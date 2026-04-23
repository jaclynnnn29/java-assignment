import java.io.ObjectInputFilter.Status;
import java.util.List;
import java.util.Scanner;
import transactions.TransactionManager;
import inventory.Book;
import inventory.DigitalBook;
import inventory.Journal;
import inventory.LibraryItem;
import studyroombooking.StudyRoom;
import studyroombooking.StudyRoomManager;
import inventory.CatalogManager;
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
    private static CatalogManager catalogManager = new CatalogManager();
    private static final TransactionManager transManager = new TransactionManager();
    private static final StudyRoomManager roomManager = new StudyRoomManager();
    private static final Scanner sc = new Scanner(System.in); 
    private static User currentUser = null; 

    public static void main(String[] args) {
        
        manager.addLibrarian("JC", "admin@lib.com", 5);
        manager.addLibrarian("LY", "ly@lib.com", 4);
        manager.addLibrarian("EX", "ex@lib.com", 3);

        manager.addFaculty("ABC", "abc@faculty.com", "Computer Science");
        manager.addFaculty("GG", "gg@faculty.com", "Mathe");

        manager.addStudent("Alice", "alice@student.com", "S001");
        manager.addStudent("Bob", "bob@student.com", "S002");

        manager.addPublicMember("Ana", "ana@example.com", "023-456-7890");
        manager.addPublicMember("John", "john@example.com", "098-765-4321");

        catalogManager.addItem(new Book("The Great Gatsby", "9780743273565", "F. Scott Fitzgerald", "Fiction"));
        catalogManager.addItem(new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classic"));
        catalogManager.addItem(new Book("1984", "9780451524935", "George Orwell", "Dystopian"));
        catalogManager.addItem(new Book("The Hobbit", "9780547928227", "J.R.R. Tolkien", "Fantasy"));
        catalogManager.addItem(new Book("Java Programming", "9780134685991", "Herbert Schildt", "Education"));

        catalogManager.addItem(new DigitalBook("Clean Code", "9780132350884", "Robert Martin", "PDF", 2.5));
        catalogManager.addItem(new DigitalBook("Effective Java", "9780134685991", "Joshua Bloch", "PDF", 3.8));
        catalogManager.addItem(new DigitalBook("The Pragmatic Programmer", "9780135957059", "Andrew Hunt", "EPUB", 1.2));
        catalogManager.addItem(new DigitalBook("Design Patterns", "9780201633610", "Erich Gamma", "PDF", 5.4));
        catalogManager.addItem(new DigitalBook("Algorithms", "9780321573513", "Robert Sedgewick", "MOBI", 12.1));

        catalogManager.addItem(new Journal("Journal of Computer Science", "J-101", "CS Publisher", 42));
        catalogManager.addItem(new Journal("IEEE Software","J-202",  "IEEE Board", 38));
        catalogManager.addItem(new Journal("Medical Research Quarterly","J-505",  "Health Press", 12));
        catalogManager.addItem(new Journal("History of Art", "J-990", "Oxford Pub", 5));
        catalogManager.addItem(new Journal("Modern Physics","J-112",  "Science Daily", 88));

        while (true) {
            if (currentUser == null) {
                System.out.println("\n=== Welcome to Library Management System ===");
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
        System.out.print("Enter User ID to Login (e.g. S001): ");
        String id = sc.nextLine();        
        
        currentUser = manager.login(id);
        if (currentUser != null) {
            System.out.println("Login Successfully!");
            System.out.println("\n========== Home Page ========== ");
            System.out.println("Welcome back, " + currentUser.getuserName());
            System.out.println("Your Borrow Limit is: " + currentUser.getBorrowLimit() + 
                            " | Your Duration is: " + currentUser.getBorrowDuration() + " days");
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
            System.out.println("5. Register New Staff");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    manager.showAll();
                    break;
                case 2:
                    updateUsers();
                    break;
                case 3:
                    deleteUsers();
                    break;
                case 4:
                    bookManagement();
                    break;
                case 5:
                    staffRegister();
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

        System.out.println("\n"+"=" .repeat(36));
        System.out.println("===== REGISTRATION INFORMATION ===== ");
        System.out.println("=" .repeat(36));

        String name = getNonEmptyInput("Enter Name: ");
        String email = getNonEmptyInput("Enter Email: ");

        switch (type) {
            
            case 1:
                String studentId = getNonEmptyInput("Enter your Student ID (Numbers Only): ");
                String newId = manager.addStudent(name, email, studentId);
                System.out.println("\nWelcome to our Library\n");
                System.out.println("Registration Success for: " + name);
                System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newId );
                System.out.println("-------------------------------------------");
                break;

            case 2:
                String phoneNumber = getNonEmptyInput("Enter Phone Number: ");
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
    private static void updateUsers() {
        System.out.print("Enter User ID to update: ");
        String id = sc.nextLine();

        String name = getNonEmptyInput("Enter New Name: ");
        String email = getNonEmptyInput("Enter New Email: ");

        manager.updateUser(id, name, email);
    }

    // Deletion
    private static void deleteUsers() {
        System.out.print("Enter User ID to delete: ");
        String id = sc.nextLine();
        manager.deleteUser(id);
    }

    private static void staffRegister() {
    System.out.println("\n ===== STAFF REGISTRATION ===== ");
    System.out.println("1. Faculty");
    System.out.println("2. Librarian");
    System.out.print("Choice: ");
    int type = readInt();

    System.out.println("=" .repeat(30));
    System.out.println("\n ===== REGISTRATION INFORMATION ===== ");
    System.out.println("=" .repeat(30));

    String name = getNonEmptyInput("Enter Name: ");
    String email = getNonEmptyInput("Enter Email: ");

    if (type == 1) {
        System.out.print("Enter Department: ");
        String dept = getNonEmptyInput("Enter Department: ");
        manager.addFaculty(name, email, dept);
    } else if (type == 2) {
        System.out.print("Enter Access Level (1-5): ");
        int level = readInt();
        manager.addLibrarian(name, email, level);
    }
    System.out.println("Staff account created successfully!");
    }
    
   
    public static void bookManagement() {
        int choice;
        do {
            System.out.println("\n===== CATALOG & BOOK SYSTEM =====");
            System.out.println("1. View All Items (Books/Journals)");
            System.out.println("2. Search Items by Title/Author/ISBN");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. Reserve Study Room");
            

            // Check if user is staff (Librarian or Faculty)
            boolean isStaff = (currentUser instanceof Librarian || currentUser instanceof Faculty);
            
            if (isStaff) {
                System.out.println("6. Release Study Room (Staff Only)");
                System.out.println("7. Add New Item (Staff Only)");
                System.out.println("8. Delete Item (Staff Only)");
            }
            
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    catalogManager.showAllItems(); 
                    break;
                case 2:
                    catalogManager.showAllItems();
                    searchItems(); 
                    break;
                case 3:
                    catalogManager.showAllItems();
                    handleBorrowing();
                    break;
                case 4:
                    handleReturn();
                    break;
                case 5:
                    handleRoomReservation();
                    break;
                case 6:
                    if (isStaff) {
                        System.out.print("Enter Room ID to release: ");
                        String rid = sc.nextLine();
                        StudyRoom roomToRelease = roomManager.findRoom(rid);
                        if (roomToRelease != null) roomToRelease.releaseRoom();
                        else System.out.println("Room not found.");
                    } else {
                        System.out.println("Access Denied!");
                    }
                    break;
                case 7:
                    if (isStaff) deleteItem(); // Call deletion logic
                    else System.out.println("Access Denied!");
                    break;
                case 0:
                    currentUser = null;
                    System.out.println("Logged out...");
                    break;
                default:
                    System.out.println("Error! Invalid selection.");
            }
        } while (choice != 0);        
    }

    public static void searchItems() {
        while (true) {
            System.out.print("Enter search keyword (Title, Author, or ISBN) or '0' to go return: ");
            String keyword = sc.nextLine().toLowerCase(); 

            if (keyword.equals("0")) {
                System.out.println("Returning to Catalog Menu...");
                break; 
            }
            // This calls the new method you just added!
            List<LibraryItem> allItems = catalogManager.getItemList(); 
            boolean found = false;

            System.out.println("\n--- Search Results ---");
            System.out.println("=".repeat(150));
            System.out.printf("%-12s %-15s %-35s %-30s\n", "Status", "ISBN", "Title", "Author");
            System.out.println("=".repeat(150));

            for (LibraryItem item : allItems) {
                // We check if the keyword is inside the Title, Author, OR ISBN
                if (item.getTitle().toLowerCase().contains(keyword) || 
                    item.getAuthor().toLowerCase().contains(keyword) || 
                    item.getItemISBN().toLowerCase().contains(keyword)) {
                    
                    System.out.println(item.toString());
                    found = true;
                }
            }
            System.out.println("=".repeat(150));

            if (!found) {
                System.out.println("No matching items found for: [" + keyword + "]");
            }
        }
    }

    public static void addNewItem() {
        int type = -1;
        while (type < 1 || type > 3) {
            System.out.println("\n--- Add New Item ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Journal");
            System.out.println("3. Add Digital Book");
            System.out.print("Select type (1-3): ");
            type = readInt();
            
            if (type < 1 || type > 3) {
                System.out.println("Invalid selection! Please choose 1, 2, or 3.");
            }
        }

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
                sc.nextLine(); // CRITICAL: Consume the leftover newline
                LibraryItem newDigitalBook = new DigitalBook(title, isbn, author, fileFormat, fileSize);
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
    
    private static void  handleBorrowing() {
        while (true) {
            System.out.print("Enter ISBN to borrow (or '0' to cancel): ");
            String isbn = sc.nextLine();

            if (isbn.equals("0")) {
                System.out.println("Borrowing cancelled.");
                break; // Exit the loop
            }

            LibraryItem item = catalogManager.findByIsbn(isbn);
            if (item != null) {
                transManager.borrowItem(currentUser, item);
                break; // Exit loop after successful attempt
            } else {
                System.out.println("Error: Item with ISBN [" + isbn + "] not found. Please try again.");
            }
        }
    }

    private static void handleReturn() {
        System.out.println("\n===== Return Item =====");

        transManager.showUserActiveLoans(currentUser);

        System.out.print("Enter ISBN of the item you want to return (or '0' to return page): ");
        String isbn = sc.nextLine();

        if (isbn.equals("0")) return;

        transManager.returnItem(isbn, catalogManager);
    }

    private static void handleRoomReservation() {
        System.out.println("\n=== STUDY ROOM RESERVATION ===");
        roomManager.showRoomStatus();

        System.out.println("1. Reserve a Room");
        System.out.println("2. Release/Vacate a Room");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        int choice = readInt();

        if (choice == 0) return;

        System.out.print("Enter Room ID: ");
        String roomId = sc.nextLine();
        StudyRoom room = roomManager.findRoom(roomId);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        if (choice == 1) {
            room.reserveRoom(currentUser);
        } else if (choice == 2) {
            room.releaseRoom();
        }
    }

    private static String getNonEmptyInput(String prompt) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("This field cannot be empty!");
            }
        }
        return input;
    }

}
