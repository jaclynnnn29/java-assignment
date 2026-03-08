
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LYY
 */

// User Menu
public class userMenu {
    private static final userManager manager = new userManager(); // final??
    private static final Scanner sc = new Scanner(System.in); //final??
    
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input error! Please enter a NUMBER: ");
            }
        }
    }

    public static void display() {
        
            int choice ;
            do {
                System.out.println("\n===== USER MANAGEMENT =====");
                System.out.println("1. Register New User");
                System.out.println("2. View All Users Detail");
                System.out.println("3. Update User Information");
                System.out.println("4. Delete User");
                System.out.println("0. Back To Main Menu");
                System.out.print("Choice: ");
                
                    choice = readInt();
                
                    switch (choice) {
                        case 1:
                            register(); // method
                            break;
                        case 2:
                            manager.showAll();
                            break;
                        case 3:
                            update();
                            break;
                        case 4:
                            delete();
                            break;
                        case 0:
                            System.out.println("Returning...");
                            break;
                        default:
                            System.out.println("Invalid Input. Please enter a NUMBER above.");
                    }
                
                
            } while (choice != 0);
        
    }

    
    private static void register() {
    System.out.println("Select Type: ");
    System.out.println("Select Type: 1.Student ");
    System.out.println("Select Type: 2.Faculty ");
    System.out.println("Select Type: 3.Librarian");
    System.out.println("Select Type: 4.Public");
    int type = Integer.parseInt(sc.nextLine());

    System.out.print("Enter Name: ");
    String name = sc.nextLine();
    System.out.print("Enter Email: ");
    String email = sc.nextLine();

    switch (type) {
        case 1: 
            System.out.print("Enter Student ID: ");
            String sid = sc.nextLine();
            manager.addStudent(name, email, sid);
            break;
        case 2:
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            manager.addFaculty(name, email, dept);
            break;
        case 3:
            System.out.print("Enter Staff Level (1-5): ");
            int level = Integer.parseInt(sc.nextLine());
            manager.addLibrarian(name, email, level);
            break;
        case 4:
            manager.addPublicMember(name, email);
            break;
        default:
            System.out.println("Wrong selection!");
    }
}

    private static void update() {
        System.out.print("Enter User ID to update: ");
        //int id = Integer.parseInt(sc.nextLine()); /
        int id;
        
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Update cancelled.");
            return; 
        }
        
        System.out.print("Enter New Name oh baby: ");
        String userName = sc.nextLine();
        System.out.print("Enter New Email: ");
        String userEmail = sc.nextLine();
        manager.updateUser(id, userName, userEmail);
    }

    private static void delete() {
        System.out.print("Enter User ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        manager.deleteUser(id);
    }
}

