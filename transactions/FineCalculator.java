package transactions;

public class FineCalculator {
    // A method that demonstrates polymorphism by accepting any User type
    public double calculateTotalFine(Transaction trans, int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        
        double ratePerDay = 0.50; // Default rate
        // The system checks if overdue and calculates fine [image_fb2783.png]
        return daysOverdue * ratePerDay;
    }
}