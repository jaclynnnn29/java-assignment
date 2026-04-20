/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */

public class Student extends User {
    private final String studentId; 
    public Student(String userId, String userName, String userEmail, String studentId) {
        super(userId, userName, userEmail, "Student"); 
        this.studentId = studentId;
    }

    @Override  
    public String toString() {
        return super.toString() + " | StudentID: " + studentId;
    }
}