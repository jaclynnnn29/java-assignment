/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LYY
 */
class Faculty extends User {
    private final String department;

    public Faculty(int userId, String userName, String userEmail, String department) {
        super(userId, userName, userEmail, "Faculty");
        this.department = department;
    }
    @Override
    public String toString() {
        return super.toString() + " | Dept: " + department;
    }
}
