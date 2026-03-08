/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
class Resource {
    private String title;
    private String id;
    private String genre;
    private String status;
    
    public Resource(String title, String id, String genre) {
        this.title = title;
        this.id = id;
        this.genre = genre;
        this.status = "Available";
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getId(){
        return id;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void displayDetails(){
        System.out.printf("\n%-10s %-25s %s\n", "ID: " , "Title: ", "Status: ");
    }
}
