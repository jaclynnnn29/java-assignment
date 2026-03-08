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
    // private String LibraryItems[];
    private String items;
    private int totalItems;
        
    public Catalog(String catelogID,String items){
        this.catelogID = catelogID;
        this.items = items ;
        //hvnt LibraryItems
        this.totalItems = 0 ;
    }
    
    public addItems(String items){
        items.add(items);
        totalItems++
    }
    
    public removeItems(String items){
        items.add(items);
        totalItems++
    }
            
}
