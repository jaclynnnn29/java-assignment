package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class UserManager { 
    //User[] userArray = new User[100];
    private final List<User> userList = new ArrayList<>(); 
    //private int nextId = 1001;
    private int studentCount = 1;
    private int facultyCount = 1;
    private int librarianCount = 1;
    private int publicCount = 1;
    
    private String generateId(String prefix, int count) {
        return String.format("%s%03d", prefix, count);
    }
    
    // Login verification: Find a person by ID
    public User login(String id) {
        for (User u : userList) { //Use equalsIgnoreCase (log in `s001` or `S001`)
            if (u.getuserId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    // A unified method for adding users 
    public void addUser(User u) {
        userList.add(u);
        
        System.out.println("Registration Success! [" + u.getuserName() + "] ");
        System.out.println("Your ID is: " + u.getuserId());
    }
    
    public String addStudent(String studentName, String studentEmail, String studentId){
        // 1. Use the helper and the dedicated counter
        String newId = generateId("S", studentCount++); 
        // 2. UNCOMMENT THIS: You must actually add the student to the list!
        userList.add(new Student(newId, studentName, studentEmail, studentId));
        return newId;
    }
    
    public String addFaculty(String userName, String userEmail, String department ) {
        String newfacultyId = generateId("F", facultyCount++);
        userList.add(new Faculty(newfacultyId, userName, userEmail, department));
        return newfacultyId;
    }
    
    public String addLibrarian(String userName, String userEmail, int staffLevel) {
        String newlibId = generateId("L", librarianCount++);
        userList.add(new Librarian(newlibId, userName, userEmail, staffLevel));
        return newlibId;
    }

    public String addPublicMember(String userName, String userEmail, String phoneNumber) {
        String newpmId = generateId("P", publicCount++);
        userList.add(new PublicMember(newpmId, userName, userEmail, phoneNumber));
        return newpmId;
    }
    
    public void showAll() {
        if (userList.isEmpty()) {
            System.out.println("No User Found ah.");
            return;   
        }
        
        userList.sort(new Comparator<User>() {
        @Override
        public int compare(User u1, User u2) {
            return Integer.compare(getPriority(u1), getPriority(u2));
        } 

            private int getPriority(User u) {
                if (u instanceof Librarian) return 1;
                if (u instanceof Faculty) return 2;
                if (u instanceof Student) return 3;
                if (u instanceof PublicMember) return 4;
                return 5; 
            }
        });
        
        System.out.println("\n=== USER LIST ===");
        String lastType = "";
        for (User u : userList) {
            String currentType = "";
        
        // Determine the specific type of the current object
        if (u instanceof Librarian) currentType = "Librarian";
        else if (u instanceof Faculty) currentType = "Faculty";
        else if (u instanceof Student) currentType = "Student";
        else if (u instanceof PublicMember) currentType = "Public Member";

        if (!currentType.equals(lastType)) {
            System.out.println("\n--- " + currentType + "'s User List ---");
            System.out.println("=".repeat(150));
            lastType = currentType;
            
        }
            System.out.println(u);
            System.out.println("=".repeat(150));
        }
    }
    
    public boolean updateUser(String userId, String newName, String newEmail) {
        for (User u : userList) {
            if (u.getuserId().equalsIgnoreCase(userId)) {
                // Validation check for the new email format
                if (!newEmail.contains("@")) {
                    System.out.println("Error: Invalid Email format (must contain '@'). Update failed.");
                    return false; 
                }
                
                u.setuserName(newName);
                u.setuserEmail(newEmail);
                System.out.println("Updated successfully for ID: " + userId + "!");
                return true; // Return true to indicate success
            }
        }
        System.out.println("Error: User ID [" + userId + "] not found.");
        return false; // Return false so Main.java knows to loop back
    }

    public void deleteUser(String id) {
        boolean removed = userList.removeIf(u -> u.getuserId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("User deleted!");
        } else {
            System.out.println("User ID not found.");
        }
    }
    
    public void saveData() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream("users.dat"))) {
            oos.writeObject(userList);
            oos.writeInt(studentCount);
            oos.writeInt(facultyCount);
            oos.writeInt(librarianCount);
            oos.writeInt(publicCount);
        } catch (java.io.IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public boolean loadData() {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("users.dat"))) {
            List<User> loadedList = (List<User>) ois.readObject();
            userList.clear();
            userList.addAll(loadedList);
            studentCount = ois.readInt();
            facultyCount = ois.readInt();
            librarianCount = ois.readInt();
            publicCount = ois.readInt();
            return true; // Successfully loaded
        } catch (Exception e) {
            return false; // File doesn't exist yet, which is normal for the first run
        }
    }

}