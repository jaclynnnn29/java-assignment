
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class StudyRoom {
    private String roomId;
    private int capacity;
    private boolean available;
    private User reservedBy;
    private Date reservationDate;

    public StudyRoom(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.available = true;
    }

    public void reserveRoom(User user) {

        if (available) {
            reservedBy = user;
            reservationDate = new Date();
            available = false;

            System.out.println("Study room " + roomId + " reserved by " + user.getuserName());
        } else {
            System.out.println("Room is already reserved.");
        }
    }

    public void releaseRoom() {

        available = true;
        reservedBy = null;

        System.out.println("Study room " + roomId + " is now available.");
    }

    public void displayRoomInfo() {

        System.out.println("Room ID: " + roomId);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available: " + available);

        if (reservedBy != null) {
            System.out.println("Reserved By: " + reservedBy.getuserName());
        }
    }
}
