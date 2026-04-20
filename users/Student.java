package users;
public class Student extends User {
    private final String studentId; 
    private static final int BORROW_LIMIT = 5;
    private static final int BORROW_DAYS = 14;

    public Student(String userId, String userName, String userEmail, String studentId) {
        super(userId, userName, userEmail, "Student"); 
        this.studentId = studentId;
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
        return super.toString() + " | StudentID: " + studentId + " | Limit: " + BORROW_LIMIT + " books (" + BORROW_DAYS + " days)";
    }

}