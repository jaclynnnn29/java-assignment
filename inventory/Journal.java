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
    
    public Journal(String title,String itemISBN,  String author, int volumeNumber){
        super(title,  itemISBN, author);
        this.volumeNumber = volumeNumber;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }
    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format("Volume: %-15d\n", volumeNumber);
    }    
}
