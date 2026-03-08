/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Catalog {
    
    private String catelogID;
    private Resources[] LibraryItem;   
    private int totalItems;
            
    public Catalog(String catelogID,int items){
        this.catelogID = catelogID;
        this.LibraryItem = new Resources[items];
        this.totalItems = 0 ;
    }
    public void addItems(Resources item){
        if (totalItems < LibraryItem.length){
            LibraryItem[totalItems] = item;
            totalItems++;
        }else{
            System.out.println("Your borrowing limit is fulled.");
        }
    }
    public void removeItems(String title){
        for(int i=0 ; i < totalItems ; i++){
            if(LibraryItem[i].getTitle().equalsIgnoreCase((title))){
                
                for(int j = i;j < totalItems - 1;j++){
                    LibraryItem[j] = LibraryItem[j + 1];
                }
                LibraryItem[totalItems - 1] = null;
                totalItems--;
                break;
            }
        }
            
    }
    public Resources searchByTitle(String title){
        for (int i=0 ; i < totalItems ; i++){
            if(LibraryItem[i].getTitle().equalsIgnoreCase(title)){
                return LibraryItem[i];
            }
        }
        System.out.println("Not Found");
        return null;
    }
    public Resources searchById(String id){
        for (int i=0 ; i < totalItems ; i++){
            if(LibraryItem[i].getId().equalsIgnoreCase(id)){
                return LibraryItem[i];
            }
        }
        System.out.println("Not Found");
        return null;
    }
    public Resources searchByGenre(String genre){
        for (int i=0 ; i < totalItems ; i++){
            if(LibraryItem[i].getId().equalsIgnoreCase(genre)){
                return LibraryItem[i];
            }
        }
        System.out.println("Not Found");
        return null;
    }
    public int getTotalItems(){
        return totalItems;
    }
    public void displayItems(){
        for(int i=0 ; i < totalItems; i++){
            System.out.println(LibraryItem[i]);
        }
    }
}         

