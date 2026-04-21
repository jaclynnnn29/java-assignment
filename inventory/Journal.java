package inventory;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Journal extends LibraryItem {
    private int volumeNumber;
    
    public Journal(String itemISBN, String title, String author, int volumeNumber){
        super(title, itemISBN, author);
        this.volumeNumber = volumeNumber;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }
    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }
    
    public String toString(){
        return super.toString() + String.format(" | Volume: %-5d\n", volumeNumber);
    }
    
}
