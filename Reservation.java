
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class Reservation {
    private String reservationId;
    private User user;
    private Resource item;
    private Date reservationDate;


    public Reservation(String reservationId, User user, Resource item) {

        this.reservationId = reservationId;
        this.user = user;
        this.item = item;

        this.reservationDate = new Date();
    }

    public void displayReservation() {

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("User: " + user.getuserName());
        System.out.println("Item: " + item.getTitle());
        System.out.println("Date: " + reservationDate);
    }
}
