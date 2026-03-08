/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Fine {
    private String fineId;
    private Loan loan;
    private double amount;
    private boolean paid;

    public Fine(String fineId, Loan loan) {
        this.fineId = fineId;
        this.loan = loan;
        this.amount = calculateFine();
        this.paid = false;
    }

    private double calculateFine() {

        long overdueDays = loan.checkOverdueDays();

        double fineRate = 1.0; // RM1 per day

        return overdueDays * fineRate;
    }

    public void payFine(){
        paid = true;
        System.out.println("Fine paid: RM" + amount);
    }

    public void displayFineDetails(){

        System.out.println("\nFine ID: " + fineId);
        System.out.println("User: " + loan.getUser().getuserName());
        System.out.println("Resource: " + loan.getResource().getTitle());
        System.out.println("Fine Amount: RM" + amount);
        System.out.println("Paid: " + paid);
    }    
}
