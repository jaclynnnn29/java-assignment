package studyroombooking;
import java.util.ArrayList;
import java.util.List;
import users.User;

public class StudyRoomManager {
    private final List<StudyRoom> rooms = new ArrayList<>();

    public StudyRoomManager() {
        // Initialize with some default rooms
        rooms.add(new StudyRoom("R101", 3));
        rooms.add(new StudyRoom("R102", 6));
        rooms.add(new StudyRoom("R103", 9));
    }

    public void showRoomStatus() {
        for (StudyRoom room : rooms) {
            room.displayRoomStatus();
        }
    }

    public StudyRoom findRoom(String roomId) {
        for (StudyRoom room : rooms) {
            if (room.getRoomId().equalsIgnoreCase(roomId)) {
                return room;
            }
        }
        return null;
    }
}