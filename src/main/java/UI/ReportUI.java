package UI;

import controller.CampController;
import controller.CampParticipantController;
import controller.ReportController;
import controller.StudentController;
import controller.EnquiryController;
import entity.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ReportUI extends UI {
    private final CampParticipantController campParticipantController;
private final ReportController reportController;
    private final StudentController studentController;
    private final EnquiryController enquiryController;
    private final CampController campController;
    private int campId;
    public void setCampId(int campId) {
        this.campId = campId;
    }
    public ReportUI(User user) {
        super(user);
        campController = new CampController();
        campParticipantController = new CampParticipantController();
        studentController = new StudentController();
        reportController = new ReportController();
        enquiryController = new EnquiryController();
    }
    public void setCampIdForCommittee(int campCommitteeId) {

            this.campId = campParticipantController.getCampIdByCampCommitteeID(campCommitteeId);
            System.out.println("campId: " + campId);

    }

    public int showListOfCamps() {
        ArrayList<Camp> camps = campController.getCampsByStaff(user.getId());
        if (camps.isEmpty()) {
            System.out.println("No camps created by you.");
            return -1; // Indicate no selection possible
        }

        System.out.println("List of camps created by you:");
        for (Camp camp : camps) {
            System.out.println(camp.getId() + ". " + camp.getName());
        }

        System.out.println("Select a camp ID to generate a report:");
        int result =  integerValidator("Enter the camp ID: ");
        for(Camp camp: camps)// Validate and return the selected camp ID{
            if(camp.getId() == result)
                return result;
        return -1;
    }


    public ParticipantFilter askParticipantFilter() {
        System.out.println("Select a filter for the report:");
        System.out.println("1. All participants");
        System.out.println("2. Camp committee only");
        System.out.println("3. Attendees only");
        int choice = integerValidator("Enter your choice: ");
        switch (choice) {
            case 1:
                return ParticipantFilter.ALL;
            case 2:
                return ParticipantFilter.CAMP_COMMITTEE;
            case 3:
                return ParticipantFilter.ATTENDEE;
            default:
                System.out.println("Invalid choice. Please try again.");
                return askParticipantFilter();
        }
    }


    // function to show list of camps created by staff member and allow them to select one

    public void generateStudentListReport(ParticipantFilter participantFilter) {
        Camp camp = campController.getCampById(campId);
        ArrayList<CampParticipant> campParticipants = campParticipantController.getListByCampId(campId);
        reportController.generateStudentReport(participantFilter, camp, campParticipants);
    }
    public void generatePerformanceReport(){
        Camp camp = campController.getCampById(campId);
        ArrayList<CampParticipant> campParticipants = campParticipantController.getListByCampId(campId);
        ArrayList<CampParticipant> campCommittee = campParticipantController.getListOfCampCommitteeByCampId(campId);
        reportController.generatePerformanceReport(camp, campCommittee);
    }
    public void generateEnquiryReport(){
        Camp camp  = campController.getCampById(campId);
        ArrayList<Enquiry> enquiries = enquiryController.getEnquiriesByCampId(campId);
        reportController.generateEnquiryReport(camp, enquiries);

    }

}
