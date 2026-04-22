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

        // 2. NEW: Check if user has reached their specific BORROW_LIMIT
        int activeLoans = countActiveLoans(user);
        if (activeLoans >= user.getBorrowLimit()) {
            System.out.println("Error: Borrowing limit reached go return some books before you come again! (" + user.getBorrowLimit() + " books max)");
            return;
        }
        
        String tid = "T" + (transactionCounter++);
        Transaction newTrans = new Transaction(tid, user, item.getItemISBN(), user.getBorrowDuration());
        
        transactions.add(newTrans);
        item.setStatus(LibraryItem.STATUS_BORROWED); // Update status to "On Loan"
        
        System.out.println("Successfully Borrowed! Due Date: " + newTrans.getDueDate());
        System.out.println("Current active loans: " + (activeLoans + 1) + "/" + user.getBorrowLimit());
    }
    
    private int countActiveLoans(User user) {
        int count = 0;
        for (Transaction t : transactions) {
            // Match by User ID and ensure it hasn't been returned yet
            if (t.getMember().getuserId().equalsIgnoreCase(user.getuserId()) && !t.isReturned()) {
                count++;
            }
        }
        return count;
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
        System.out.println("Good Job you made it on time no compensation needed!");
    }

    public void showUserActiveLoans(User user) {
        System.out.println("\nYour Current Active Loans");
        System.out.println("=".repeat(100));
        System.out.printf("%-30s %-20s %-30s\n", "Transaction ID", "ISBN", "Due Date");
        System.out.println("=".repeat(100));
        
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getMember().getuserId().equalsIgnoreCase(user.getuserId()) && !t.isReturned()) {
                System.out.printf("%-30s %-20s %-30s\n", 
                    t.getTransactionID(), t.getItemISBN(), t.getDueDate());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("You have no active loans to return.");
        }
        System.out.println("=".repeat(100));
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