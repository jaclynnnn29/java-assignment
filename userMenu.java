/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.membership.management;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ASUS
 */
class User {
    private final int userId;
    private String userName;
    private String userEmail;
    private final String userType;
    
    public User(int userId, String userName, String userEmail, String userType){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    //Getters
    public int getuserId() {
        return userId;
    }

    public String getuserName() {
        return userName;
    }
    
    public String getuserEmail(){
        return userEmail;
    }
    
    //Setters
    public void setuserName(String userName) {
        this.userName = userName;
    }
    
    public void setuserEmail(String userEmail) {
        if (userEmail.contains("@")){
            this.userEmail = userEmail;
        } else {
            System.out.println("Invalid Email lah aiyoo.");
        }
    }

    @Override  //？？？
    public String toString() {
        return String.format("User Type: %15s |ID: %-5d | Name: %-15s | Email: %-20s" , userType, userId, userName, userEmail);
    }
    
}

class Student extends User {
    private final String studentId;
    public Student(int userId, String userName, String userEmail, String studentId) {
        super(userId, userName, userEmail, "Student"); 
        this.studentId = studentId;
    }

    @Override  //???
    public String toString() {
        return super.toString() + " | StudentID: " + studentId;
    }
}

class Faculty extends User {
    private final String department;

    public Faculty(int userId, String userName, String userEmail, String department) {
        super(userId, userName, userEmail, "Faculty");
        this.department = department;
    }
}

class Librarian extends User {
    private final int staffLevel;

    public Librarian(int userId, String userName, String userEmail, int staffLevel) {
        super(userId, userName, userEmail, "Librarian");
        this.staffLevel = staffLevel;
    }
    
    @Override //???
    public String toString() {
        return super.toString() + " | Staff Level: " + staffLevel;
    }
    
}

class PublicMember extends User {
    public PublicMember(int userId, String userName, String userEmail) {
        super(userId, userName, userEmail, "Public");
    }
}

class userManager {
    //User[] userArray = new User[100];
    private final List<User> userList = new ArrayList<>(); //final ??
    private int nextId = 1001;
    
    public void addStudent(String studentName, String studentEmail, String studentId){
        userList.add(new Student(nextId++, studentName, studentEmail, studentId ));
    }
    
    public void addFaculty(String userName, String userEmail, String department ) {
        userList.add(new Faculty(nextId++, userName, userEmail, department));
    }
    
    public void addLibrarian(String userName, String userEmail, int staffLevel) {
        userList.add(new Librarian(nextId++, userName, userEmail, staffLevel));
        System.out.println("Librarian added successfully =3=3=3 !");
    }

    public void addPublicMember(String userName, String userEmail) {
        userList.add(new PublicMember(nextId++, userName, userEmail));
        System.out.println("Public Member added successfully =3=3=3 !");
    }
    
    //public void addUser(String userName, String userEmail) {
    //    userList.add(new User(nextId++, userName, userEmail));
    //    System.out.println("Users added sucessfully haha .");
    //}
    
    public void showAll() {
        if (userList.isEmpty()) {
            System.out.println("No User Found ah.");
            return;  // return userEmail 
        }
        System.out.println("\n=== USER LIST ===\n");
        for (User u : userList) {
            System.out.println(u);
        }
    }
    
    public void updateUser(int userId, String newName, String newEmail) {
        for (User u : userList) {
            if (u.getuserId() == userId) {
                u.setuserName(newName);
                u.setuserEmail(newEmail);
                System.out.println("User updated!");
                return;
            }
        }
        System.out.println("User ID not found.");
    }

    public void deleteUser(int id) {
        boolean removed = userList.removeIf(u -> u.getuserId() == id);
        if (removed) {
            System.out.println("User deleted!");
        } else {
            System.out.println("User ID not found.");
        }
    }
    
}

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
    System.out.println("=== Select Type === ");
    System.out.println("1.Student ");
    System.out.println("2.Faculty ");
    System.out.println("3.Librarian");
    System.out.println("4.Public");
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
        //int id = Integer.parseInt(sc.nextLine());
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











