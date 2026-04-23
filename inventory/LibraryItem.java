package inventory;


public abstract class LibraryItem implements java.io.Serializable {
    private String title;
    private String itemISBN; 
    private String status = STATUS_AVAILABLE; // default constant for available
    private String author; 
    private boolean available = true;

    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_BORROWED = "Borrowed";
    
    public LibraryItem(String title, String itemISBN, String author) {
        this.title = title;
        this.itemISBN = itemISBN;
        this.author = author;
    }
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    
    public String getItemISBN(){
        return itemISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
        
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
    this.status = status;
        // This logic ensures 'available' matches the 'status' automatically
        if (status.equals(STATUS_AVAILABLE)) {
            this.available = true;
        } else {
            // If it's Borrowed or Reserved, it's not available
            this.available = false;
        }
    }

    public boolean isAvailable() { // Added this getter
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        this.status = available ? STATUS_AVAILABLE : STATUS_BORROWED;
    }
    
    @Override
    public String toString(){
        return String.format("%-12s %-15s %-35s %-30s ", status, itemISBN, title, author);
    }
}
