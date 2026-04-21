package transactions;

public class FineCalculator {
    // A method that demonstrates polymorphism by accepting any User type
    public double calculateTotalFine(Transaction trans, int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        
        // Polmorphism in action: 
        // The rate is determined by the specific User type at runtime.
        double ratePerDay = trans.getMember().getFineRate();

        return daysOverdue * ratePerDay;
    }
}