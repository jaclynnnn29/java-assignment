package studyroombooking;
import java.util.ArrayList;
import java.util.List;

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

    public void saveData() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream("rooms.dat"))) {
            oos.writeObject(rooms);
        } catch (java.io.IOException e) { }
    }

    @SuppressWarnings("unchecked")
    public boolean loadData() {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("rooms.dat"))) {
            List<StudyRoom> loadedList = (List<StudyRoom>) ois.readObject();
            rooms.clear();
            rooms.addAll(loadedList);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}