package transactions;
import java.util.Date;
import java.util.Calendar;

public class Transaction {
    // Encapsulation: Private attributes [cite: 58]
    private String transactionID;
    private String memberID;
    private String itemISBN;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private boolean isReturned;

    // Constructor
    public Transaction(String transactionID, String memberID, String itemISBN, int loanDuration) {
        this.transactionID = transactionID;
        this.memberID = memberID;
        this.itemISBN = itemISBN;
        this.issueDate = new Date(); // Current date
        this.isReturned = false;
        
        // Logic to calculate due date [image_fb2783.png]
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.issueDate);
        cal.add(Calendar.DATE, loanDuration);
        this.dueDate = cal.getTime();
    }

    // Getters and Setters [cite: 58]
    public String getTransactionID() { return transactionID; }
    public Date getDueDate() { return dueDate; }
    public boolean isReturned() { return isReturned; }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
    }
}
