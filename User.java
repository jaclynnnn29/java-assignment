/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LYY
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

    @Override
    public String toString() {
        return String.format("User Type: %15s |ID: %-5d | Name: %-15s | Email: %-20s" , userType, userId, userName, userEmail);
    }
    
}

