package users;

public class Faculty extends User {
    private final String department;
    private static final int BORROW_LIMIT = 10;
    private static final int BORROW_DAYS = 30;

    public Faculty(String userId, String userName, String userEmail, String department) {
        super(userId, userName, userEmail, "Faculty");
        this.department = department;
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
        return super.toString() + " | Dept: " + department + " | Limit: " + BORROW_LIMIT + " books (" + BORROW_DAYS + " days)";
    }
    
}