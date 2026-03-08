
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lYY
 */

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
    
    
    public void showAll() {
        if (userList.isEmpty()) {
            System.out.println("No User Found ah.");
            return;  // return 
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