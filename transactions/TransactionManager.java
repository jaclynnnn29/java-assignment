package transactions;

import inventory.LibraryItem;
import inventory.CatalogManager;
import users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private FineCalculator fineCalculator = new FineCalculator();
    private int transactionCounter = 1000;

    // Borrowing Logic
    public void borrowItem(User user, LibraryItem item) {
        if (!item.isAvailable()) {
            System.out.println("Error: Item is currently 'On Loan'.");
            return;
        }
        
        String tid = "T" + (transactionCounter++);
        Transaction newTrans = new Transaction(tid, user, item.getItemISBN(), user.getBorrowDuration());
        
        transactions.add(newTrans);
        item.setStatus(LibraryItem.STATUS_BORROWED); // Update status to "On Loan"
        
        System.out.println("Successfully Borrowed! Due Date: " + newTrans.getDueDate());
    }
    // Update status add "Reserved"
    public void reserveItem(User user, LibraryItem item) {
        // 1. Check if it's actually available to be reserved
        if (!item.isAvailable()) {
            System.out.println("Error: Item is already borrowed or reserved.");
            return;
        }

        // 2. Change the status using your constant
        item.setStatus(LibraryItem.STATUS_RESERVED);

        // 3. Print the success message
        System.out.println("Item reserved successfully. You will be notified when it's available for borrowing.");
    }

    // Returning Logic
    public void returnItem(String isbn, CatalogManager catalog) {
        for (Transaction t : transactions) {
            if (t.getItemISBN().equals(isbn) && !t.isReturned()) {
                t.setReturnDate(new Date());
                
                // Update Catalog Status
                LibraryItem item = catalog.findByIsbn(isbn);
                if (item != null) {
                    item.setAvailable(true);
                    item.setStatus(LibraryItem.STATUS_AVAILABLE);
                }

                // Calculate Fines
                long diff = t.getReturnDate().getTime() - t.getDueDate().getTime();
                int daysLate = (int) (diff / (1000 * 60 * 60 * 24));
                
                if (daysLate > 0) {
                    double fine = fineCalculator.calculateTotalFine(t, daysLate);
                    System.out.printf("Item returned LATE (%d days). Fine Amount: RM%.2f\n", daysLate, fine);
                } else {
                    System.out.println("Item returned on time. No fine.");
                }
                return;
            }
        }
        System.out.println("No active transaction found for ISBN: " + isbn);
    }

    public void showActiveTransactions() {
        System.out.println("\n--- Current Active Loans ---");
        for (Transaction t : transactions) {
            if (!t.isReturned()) {
                System.out.println("ID: " + t.getTransactionID() + " | Member: " + t.getMember().getuserName() + 
                                   " | ISBN: " + t.getItemISBN() + " | Due: " + t.getDueDate());
            }
        }
    }
}