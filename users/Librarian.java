package users;

public class Librarian extends User { 
    private final int staffLevel;
    private static final int BORROW_LIMIT = 20;
    private static final int BORROW_DAYS = 60;


    public Librarian(String userId, String userName, String userEmail, int staffLevel) {
        super(userId, userName, userEmail, "Librarian");
        this.staffLevel = staffLevel;
    }

    @Override
    public int getBorrowLimit() {
        return BORROW_LIMIT;
    }

    @Override
    public int getBorrowDuration() {
        return BORROW_DAYS;
    }
    
    @Override 
    public String toString() {
        return super.toString() + " | Staff Level: " + staffLevel + " | Limit: " + BORROW_LIMIT + " books (" + BORROW_DAYS + " days)";
    }
    
}