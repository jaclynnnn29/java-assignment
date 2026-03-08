/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Main {    
    public static void main(String[] args) {

        Catalog catalog = new Catalog("C001",10);

        Resources book1 = new Resources("Java Programming","25wmd01234","John Smith");
        Resources book2 = new Resources("Database Systems","25wmd01234","Jane Lee");

        catalog.addItems(book1);
        catalog.addItems(book2);

        System.out.println("All Items:");
        catalog.displayItems();

        System.out.println("\nSearch Result:");
        System.out.println(catalog.searchByTitle("Java Programming"));
    }
}    