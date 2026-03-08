
import java.util.Calendar;
import java.util.Date;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Loan {
    private String loanId;
    private User user;
    private Resource resource;
    private Date loanDate;
    private Date dueDate;
    private boolean returned;

    public Loan(String loanId, User user, Resource resource) {

        this.loanId = loanId;
        this.user = user;
        this.resource = resource;

        this.loanDate = new Date();

        // Due date = 7 days later
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        this.dueDate = cal.getTime();

        this.returned = false;

        // Change resource status to Borrowed
        resource.setStatus("Borrowed");
    }

    public void returnResource() {

        returned = true;
        resource.setStatus("Available");

        System.out.println(user.getuserName() + " returned " + resource.getTitle());
    }

    public void displayLoanDetails(){

        System.out.println("\nLoan ID: " + loanId);
        System.out.println("User: " + user.getuserName());
        System.out.println("Resource: " + resource.getTitle());
        System.out.println("Loan Date: " + loanDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Returned: " + returned);
    } 
    
    public long checkOverdueDays() {

        Date today = new Date();

        if(today.after(dueDate)){
            long diff = today.getTime() - dueDate.getTime();
            return diff / (1000 * 60 * 60 * 24);
        }
        return 0;
    }
    
    public User getUser(){
        return user;
    }   

    public Resource getResource(){
        return resource;
    }
}