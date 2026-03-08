/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
class EBook extends Resource {
    private String fileFormat; // e.g., PDF, EPUB
    private double fileSizeMB;

    public EBook(String itemID, String title, String author, String genre, String fileFormat, double fileSizeMB) {
        super(itemID, title, author, genre);
        this.fileFormat = fileFormat;
        this.fileSizeMB = fileSizeMB;
    }

    public String toString() {
        // Formats the file size to 2 decimal places using %.2f (Chapter 5)
        return super.toString() + String.format(" | Format: %-5s | Size: %.2f MB", fileFormat, fileSizeMB);
    }
}

