/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaclynnnnn
 */
public class Journal extends Resource {
    private int volume;
    
    public Journal(String title, String genre, int volume){
        super(title, genre);
        this.volume = volume;
    }
    
    public String toString(){
        return String.format("%-10s %-8d %-6d %-10s -15d", getTitle(), getId(), getGenre(), volume);
    }
    
}
