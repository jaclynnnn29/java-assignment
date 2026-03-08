/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
class EBook extends Resource {
    private String fileFormat; 
    private double fileSizeMB;

    public EBook(String itemID, String title, String author, String genre, String fileFormat, double fileSizeMB) {
        super(title, itemID, genre, author); 
        this.fileFormat = fileFormat;
        this.fileSizeMB = fileSizeMB;
    }
    
    public String toString() {
        // super.toString() handles the base info, then we add EBook details
        return super.toString() + String.format(" | Format: %-5s | Size: %.2f MB", fileFormat, fileSizeMB);
    }
}
