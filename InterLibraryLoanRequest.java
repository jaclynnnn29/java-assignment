
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ceexi
 */
public class InterLibraryLoanRequest {
    private String requestId;
    private User user;
    private Resource resource;
    private String requestedLibrary;
    private String status;
    private Date requestDate;

    public InterLibraryLoanRequest(String requestId, User user, Resource resource, String requestedLibrary) {

        this.requestId = requestId;
        this.user = user;
        this.resource = resource;
        this.requestedLibrary = requestedLibrary;

        this.status = "Pending";
        this.requestDate = new Date();
    }

    public void approveRequest() {
        status = "Approved";
        System.out.println("Request approved from " + requestedLibrary);
    }

    public void rejectRequest() {
        status = "Rejected";
        System.out.println("Request rejected.");
    }

    public void displayRequestDetails() {

        System.out.println("Request ID: " + requestId);
        System.out.println("User: " + user.getuserName());
        System.out.println("Resource: " + resource.getTitle());
        System.out.println("Requested Library: " + requestedLibrary);
        System.out.println("Status: " + status);
        System.out.println("Request Date: " + requestDate);
    }
}
