/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package user.membership.management;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */

public class UserMembershipManagement {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
            // TODO code application logic here
            Scanner sc = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println("=====================");
                System.out.println("   USER MANAGEMENT   ");
                System.out.println("=====================");
                System.out.println("1. Register New User ");
                System.out.println("2. View User Detail ");
                System.out.println("3. Update User Information");
                System.out.println("4. Delete User ");
                System.out.println("0. Back to main menu ");

                try {
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("registerUser");  // ????method
                            break;
                   


                    }

                } catch (NumberFormatException e ){
                    System.out.println("Invalid input. Please enter numbers only .");
                    sc.nextLine(); // Clear the invalid input from the buffer
                    choice = -1; // Reset choice to keep the loop going
                }


            }while (choice != 0);
            sc.close();

        }
    
}


