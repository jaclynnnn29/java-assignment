package transactions;
import java.util.Date;

public class Reservation {
    private String reservationID;
    private String memberID;
    private String itemISBN;
    private Date requestDate;
    private boolean isFulfilled; // True when the book is returned and assigned to this user

    public Reservation(String reservationID, String memberID, String itemISBN) {
        this.reservationID = reservationID;
        this.memberID = memberID;
        this.itemISBN = itemISBN;
        this.requestDate = new Date();
        this.isFulfilled = false;
    }

    // Getters
    public String getItemISBN() { return itemISBN; }
    public String getMemberID() { return memberID; }
    public boolean isFulfilled() { return isFulfilled; }
    
    // Setter for when the book becomes available
    public void setFulfilled(boolean fulfilled) { isFulfilled = fulfilled; }
}