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

        boolean hasUsers = manager.loadData();
        boolean hasCatalog = catalogManager.loadData();
        roomManager.loadData();
        transManager.loadData();
        
        if (!hasUsers){
            manager.addLibrarian("JC", "admin@lib.com", 5);
            manager.addLibrarian("LY", "ly@lib.com", 4);
            manager.addLibrarian("EX", "ex@lib.com", 3);
            manager.addFaculty("ABC", "abc@faculty.com", "Computer Science");
            manager.addFaculty("GG", "gg@faculty.com", "Mathe");
            manager.addStudent("Alice", "alice@student.com", "S001");
            manager.addStudent("Bob", "bob@student.com", "S002");
            manager.addPublicMember("Ana", "ana@example.com", "023-456-7890");
            manager.addPublicMember("John", "john@example.com", "098-765-4321");
        }
        
        if (!hasCatalog) {
            catalogManager.addItem(new Book("The Great Gatsby", "9780743273565", "F. Scott Fitzgerald", "Fiction"));
            catalogManager.addItem(new Book("To Kill a Mockingbird", "9780061120084", "Harper Lee", "Classic"));
            catalogManager.addItem(new Book("1984", "9780451524935", "George Orwell", "Dystopian"));
            catalogManager.addItem(new Book("The Hobbit", "9780547928227", "J.R.R. Tolkien", "Fantasy"));
            catalogManager.addItem(new Book("Java Programming", "9780134685991", "Herbert Schildt", "Education"));

            catalogManager.addItem(new DigitalBook("Clean Code", "9780132350884", "Robert Martin", "PDF", 2.5));
            catalogManager.addItem(new DigitalBook("Effective Java", "9780134685369", "Joshua Bloch", "PDF", 3.8));
            catalogManager.addItem(new DigitalBook("The Pragmatic Programmer", "9780135957059", "Andrew Hunt", "EPUB", 1.2));
            catalogManager.addItem(new DigitalBook("Design Patterns", "9780201633610", "Erich Gamma", "PDF", 5.4));
            catalogManager.addItem(new DigitalBook("Algorithms", "9780321573513", "Robert Sedgewick", "MOBI", 12.1));

            catalogManager.addItem(new Journal("Journal of Computer Science", "J-101", "CS Publisher", 42));
            catalogManager.addItem(new Journal("IEEE Software","J-202",  "IEEE Board", 38));
            catalogManager.addItem(new Journal("Medical Research Quarterly","J-505",  "Health Press", 12));
            catalogManager.addItem(new Journal("History of Art", "J-990", "Oxford Pub", 5));
            catalogManager.addItem(new Journal("Modern Physics","J-112",  "Science Daily", 88));
        }

        while (true) {
            if (currentUser == null) {
                System.out.println("  _____________________________________________  ");
                System.out.println(" /                                             \\ ");
                System.out.println("|   =========================================   |");
                System.out.println("|          LIBRARY MANAGEMENT SYSTEM            |");
                System.out.println("|   =========================================   |");
                System.out.println("|                                               |");
                System.out.println("|     |||||||||      [=======]      |#|#|#|     |");
                System.out.println("|     |||||||||      [=======]      |#|#|#|     |");
                System.out.println("|     |||||||||      [_______]      |#|#|#|     |");
                System.out.println("|                                               |");
                System.out.println("|   =========================================   |");
                System.out.println(" \\_____________________________________________/ ");

                System.out.println("\n================================================");                                           
                System.out.println("      Welcome to Library Management System      ");
                System.out.println("================================================");
                System.out.println("1. Login Existing Account");
                System.out.println("2. Register New Account");
                System.out.println("0. Exit System");
                System.out.print("Choice: ");
                
                int n = readInt();
                
                if (n == 0) {
                    //SAVE ALL DATA RIGHT BEFORE CLOSING
                    System.out.println("Data Changes Saved...");
                    manager.saveData();
                    catalogManager.saveData();
                    transManager.saveData();
                    roomManager.saveData();

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
            System.out.println("\n===================================================");
            System.out.println("                      Home Page                    ");
            System.out.println("===================================================");
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
            System.out.println("\n================================================");
            System.out.println("                USER MANAGEMENT               ");
            System.out.println("================================================");
            System.out.println("1. View All Users Detail ");
            System.out.println("2. Update User Information");
            System.out.println("3. Delete User ");
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
                    manager.showAll();
                    updateUsers();
                    break;
                case 3:
                    manager.showAll();
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
        System.out.println("\n================================================");
        System.out.println("                REGISTER NEW USER               ");
        System.out.println("================================================");
        System.out.println("Select Type: ");
        System.out.println("1. Student ");
        System.out.println("2. Public Member");
        System.out.print("Choice: ");
        int type = readInt();

        System.out.println("\n"+"=" .repeat(48));
        System.out.println("            REGISTRATION INFORMATION            ");
        System.out.println("=" .repeat(48));

        String name = getNonEmptyInput("Enter Name: ");
        String email = getNonEmptyInput("Enter Email: ");

        switch (type) {
            
            case 1:
                String studentId = getNonEmptyInput("Enter your Student ID (Numbers Only): ");
                String newId = manager.addStudent(name, email, studentId);             
                System.out.println("\n-------------------------------------------");
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
        while (true) {
            System.out.print("\nEnter User ID to update (or '0' to cancel): ");
            String id = sc.nextLine();

            if (id.equals("0")) {
                System.out.println("Update cancelled.");
                break;
            }

            // These already ensure the strings aren't empty
            String name = getNonEmptyInput("Enter New Name: ");
            String email = getNonEmptyInput("Enter New Email: ");

            // We check the boolean result from the manager
            boolean success = manager.updateUser(id, name, email);
            
            if (success) {
                break; // Exit the loop only if everything was valid and the user was found
            }
            
            System.out.println("Please try again with a valid ID and email.");
        }
    }

    // Deletion
    private static void deleteUsers() {
        System.out.print("\nEnter User ID to delete: ");
        String id = sc.nextLine();
        
        // Step 1: Check if the user exists using the new helper method
        User userToDelete = manager.findUserById(id);
        
        if (userToDelete != null) {
            // Step 2: Show a confirmation notification with the User's name
            System.out.println("\n" + "=".repeat(80));
            System.out.println("!!! WARNING: This action need to be confirmed carefully !!!");
            System.out.println("Selected Account: " + userToDelete.getuserName() + " (ID: " + id + ")");
            System.out.print("Are you sure you want to delete this user? (Type 'YES' to confirm): ");            
            String confirmation = sc.nextLine();
            System.out.println("\n" + "=".repeat(80) + "\n");
            if (confirmation.equalsIgnoreCase("YES")) {
                // Step 3: Perform the actual deletion
                manager.deleteUser(id); 
            } else {
                System.out.println("*Deletion cancelled. User remains in system.*");
            }
        } else {
            // Step 4: Handle the case where the ID doesn't exist
            System.out.println("Error: No user found with ID [" + id + "].");
        }
    }

    private static void staffRegister() {
    System.out.println("\n================================================");
    System.out.println("               STAFF REGISTRATION               ");
    System.out.println("================================================");

    int type;
    while (true) {
        System.out.println("1. Faculty");
        System.out.println("2. Librarian");
        System.out.print("Choice: ");
        type = readInt();
        if (type == 1 || type == 2) break;
        System.out.println("Invalid selection! Please choose 1 or 2.");
    }

    System.out.println("=" .repeat(48));
    System.out.println("            REGISTRATION INFORMATION            ");
    System.out.println("=" .repeat(48));

    String name = "";
    String email = "";
    boolean isValid = false;

    while (!isValid) {
        name = getNonEmptyInput("Enter Name: ");
        email = getNonEmptyInput("Enter Email: ");

        // Simple validation: check if email contains '@' 
        // (You can add more rules like checking for '.')
        if (!email.contains("@")) {
            System.out.println("\n[ERROR] Invalid Email format! It must contain '@'.");
            System.out.println("Please rewrite the information.\n");
            continue; // Loop back
        }

        // Check if Name is purely alphabetic (optional refinement)
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            System.out.println("\n[ERROR] Invalid Name! Please use only letters.");
            System.out.println("Please rewrite the information.\n");
            continue; // Loop back
        }

        isValid = true; // If it reaches here, all inputs are valid
    }

    String newId = "";
    if (type == 1) {
        String dept = getNonEmptyInput("Enter Department: ");
        newId = manager.addFaculty(name, email, dept);
    } else if (type == 2) {
        int level;
        while (true) {
            System.out.print("Enter Access Level (1-5): ");
            level = readInt();
            if (level >= 1 && level <= 5) break;
            System.out.println("Invalid Level! Please enter a number between 1 and 5.");
        }
        newId = manager.addLibrarian(name, email, level);
    }

    System.out.println("\n-------------------------------------------");
    System.out.println("Staff account created successfully!");
    System.out.println("PLEASE REMEMBER YOUR LOGIN ID: " + newId);
    System.out.println("-------------------------------------------");
}

    
   
    public static void bookManagement() {
        int choice;
        do {
            System.out.println("\n================================================");
            System.out.println("              CATALOG & BOOK SYSTEM             ");
            System.out.println("================================================");
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
                        handleRoomRelease(); // Call the new method instead of the old inline code
                    } else {
                        System.out.println("Access Denied!");
                    }
                    break;
                case 7:
                    catalogManager.showAllItems();
                    addNewItem();
                    break;
                case 8:
                    catalogManager.showAllItems();
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
            System.out.println("=".repeat(130));
            System.out.printf("%-12s %-15s %-35s %-30s\n", "Status", "ISBN", "Title", "Author");
            System.out.println("=".repeat(130));

            for (LibraryItem item : allItems) {
                // We check if the keyword is inside the Title, Author, OR ISBN
                if (item.getTitle().toLowerCase().contains(keyword) || 
                    item.getAuthor().toLowerCase().contains(keyword) || 
                    item.getItemISBN().toLowerCase().contains(keyword)) {
                    
                    System.out.println(item.toString());
                    found = true;
                }
            }
            System.out.println("=".repeat(130));

            if (!found) {
                System.out.println("No matching items found for: [" + keyword + "]");
            }
        }
    }

    public static void addNewItem() {
        int type = -1;
        while (type < 1 || type > 3) {
            System.out.println("\n================================================");
            System.out.println("                  ADD NEW ITEM                  ");
            System.out.println("================================================");
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
                LibraryItem newBook = new Book(title, isbn, author, genre);
                catalogManager.addItem(newBook);
                break;
            case 2:
                System.out.print("Enter Volume Number: ");
                int volumeNumber = readInt();
                LibraryItem newJournal = new Journal( title, isbn,author, volumeNumber);
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
        System.out.println("\nSuccess: [" + title + "] has been added to the library catalog!");
    }

    public static void deleteItem() {
        System.out.print("Enter ISBN of item to delete: ");
        String isbn = sc.nextLine();
        
        // Step 1: Find the item first to show the user what they are deleting
        LibraryItem itemToDelete = catalogManager.findByIsbn(isbn);
        
        if (itemToDelete != null) {
            // Step 2: Display a confirmation prompt
            System.out.println("\n" + "=".repeat(80));
            System.out.println("!!! WARNING: Item Deletion !!!");
            System.out.println("Target Item: " + itemToDelete.getTitle() + " (ISBN: " + isbn + ")");
            System.out.print("Are you sure you want to delete this item? (Type 'YES' to confirm): ");
            
            String confirmation = sc.nextLine();
            System.out.println("=".repeat(80) + "\n");
            
            if (confirmation.equalsIgnoreCase("YES")) {
                // Step 3: Perform the actual deletion
                boolean success = catalogManager.deleteItem(isbn);
                if (success) {
                    System.out.println("Success: Item [" + itemToDelete.getTitle() + "] has been removed.");
                }
            } else {
                System.out.println("*Deletion cancelled. Item remains in catalog.*");
            }
        } else {
            System.out.println("Error: No item found with ISBN: " + isbn);
        }
    }
    
    private static void  handleBorrowing() {
        while (true) {
            System.out.print("Enter ISBN to borrow (or '0' to cancel): ");
            String isbn = sc.nextLine();

            if (isbn.equals("0")) {
                System.out.println("Returning page.");
                break; // Exit the loop
            }

            LibraryItem item = catalogManager.findByIsbn(isbn);
            if (item != null) {
                boolean success = transManager.borrowItem(currentUser, item);
                if (success) {
                    break; // Only exit the loop if borrowing was actually successful
                }
            } else {
                System.out.println("Error: Item with ISBN [" + isbn + "] not found. Please try again.");
            }
        }
    }

    private static void handleReturn() {
        System.out.println("\n===== Return Item =====");

        transManager.showUserActiveLoans(currentUser);

        while (true) {
            System.out.print("Enter ISBN of the item you want to return (or '0' to return page): ");
            String isbn = sc.nextLine();

            if (isbn.equals("0")) {
                System.out.println("Returning to previous menu.");
                break; // Exit the loop if they type 0
            }

            // We check if the return was successful
            boolean success = transManager.returnItem(currentUser, isbn, catalogManager);
            
            if (success) {
                break; // EXIT loop ONLY if return was successful
            }
            // If success is false (invalid ISBN), the loop will automatically repeat!
        }
    }

    private static void handleRoomRelease() {
        System.out.println("\n=== RELEASE STUDY ROOM (Staff Only) ===");
        
        // Step 1: Display current status so staff can see which rooms are OCCUPIED
        roomManager.showRoomStatus(); 

        while (true) {
            System.out.print("Enter Room ID to release (or '0' to return): ");
            String rid = sc.nextLine();

            if (rid.equals("0")) break;

            StudyRoom roomToRelease = roomManager.findRoom(rid);
            
            if (roomToRelease != null) {
                // Step 2: Perform the release
                roomToRelease.releaseRoom();
                break; // Exit after success
            } else {
                System.out.println("Error: Room [" + rid + "] not found. Please try again.");
            }
        }
    }

    private static void handleRoomReservation() {
        System.out.println("\n=== STUDY ROOM RESERVATION ===");

        roomManager.showRoomStatus();
        // Added a while loop so it repeats until successful or cancelled
        while (true) {
            System.out.print("Enter Room ID to reserve (or '0' to return to menu): ");
            String roomId = sc.nextLine();

            // Option to go back to the previous menu
            if (roomId.equals("0")) {
                System.out.println("Returning to previous menu.");
                break; 
            }

            StudyRoom room = roomManager.findRoom(roomId);

            if (room == null) {
                // If room isn't found, print an error and the loop automatically restarts
                System.out.println("Error: Room [" + roomId + "] not found. Please try again.");
                continue; 
            }

            // Attempt to reserve the room
            // reserveRoom returns true if it worked, false if already occupied
            boolean success = room.reserveRoom(currentUser);
            
            if (success) {
                break; // Exit the loop and return to the menu only after a successful reservation
            }
            // If success is false (room occupied), the loop will repeat for another input
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
