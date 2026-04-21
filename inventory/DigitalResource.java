package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
class DigitalBook extends LibraryItem {
    private String fileFormat; 
    private double fileSizeMB;

    public DigitalBook(String title, String itemISBN, String author, String genre, String fileFormat, double fileSizeMB) {
        super(title, itemISBN, author);
        this.fileFormat = fileFormat;
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("%-15s %.2f MB\n", fileFormat, fileSizeMB);
    }
}



