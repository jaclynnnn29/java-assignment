import java.util.Date;
import java.text.SimpleDateFormat;
import users.User;

public class StudyRoom {
    private final String roomId;
    private final int capacity;
    private boolean isAvailable;
    private User reservedBy;
    private Date reservationTime;

    public StudyRoom(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    // Returns boolean so the Manager can tell the user if it failed
    public boolean reserveRoom(User user) {
        if (user == null) {
            System.out.println("Error: Invalid user.");
            return false;
        }

        if (isAvailable) {
            this.reservedBy = user;
            this.reservationTime = new Date();
            this.isAvailable = false;
            System.out.println("Room " + roomId + " successfully reserved for " + user.getuserName());
            return true;
        } else {
            System.out.println("Room " + roomId + " is currently occupied by " + reservedBy.getuserName());
            return false;
        }
    }

    public void releaseRoom() {
        if (isAvailable) {
            System.out.println("Room " + roomId + " is already vacant.");
        } else {
            this.isAvailable = true;
            this.reservedBy = null;
            this.reservationTime = null;
            System.out.println("Room " + roomId + " has been released and is now available.");
        }
    }

    public void displayRoomStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        System.out.println("\n--- Study Room Report ---");
        System.out.println("Room ID    : " + roomId);
        System.out.println("Capacity   : " + capacity + " persons");
        System.out.println("Status     : " + (isAvailable ? "AVAILABLE" : "OCCUPIED"));
        
        if (!isAvailable && reservedBy != null) {
            System.out.println("Reserved By: " + reservedBy.getuserName());
            System.out.println("Since      : " + sdf.format(reservationTime));
        }
        System.out.println("--------------------------");
    }
    
    // Getters
    public String getRoomId() { 
        return roomId; 
    }

    public boolean isAvailable() { 
        return isAvailable; 
    }
}