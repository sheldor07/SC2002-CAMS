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
/**
 * A user interface for generating various reports related to camp participants, performance, and enquiries.
 * This class allows staff and camp committee members to generate reports based on different filters and criteria.
 */

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
    /**
     * Constructs a new instance of the ReportUI class.
     *
     * @param user The user for whom the report interface is created.
     */
    public ReportUI(User user) {
        super(user);
        campController = new CampController();
        campParticipantController = new CampParticipantController();
        studentController = new StudentController();
        reportController = new ReportController();
        enquiryController = new EnquiryController();
    }
    /**
     * Sets the camp ID for generating reports when the user is a camp committee member.
     *
     * @param campCommitteeId The ID of the camp committee member to determine the associated camp.
     */
    public void setCampIdForCommittee(int campCommitteeId) {

            this.campId = campParticipantController.getCampIdByCampCommitteeID(campCommitteeId);

    }
    /**
     * Displays a list of camps created by the user and allows them to select one.
     *
     * @return The ID of the selected camp or -1 if no camp is selected.
     */
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
    /**
     * Asks the user to select a participant filter for generating reports.
     *
     * @return The selected ParticipantFilter option.
     */

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


    /**
     * Generates a student list report based on the selected participant filter.
     *
     * @param participantFilter The filter to apply to the report.
     */
    public void generateStudentListReport(ParticipantFilter participantFilter) {
        Camp camp = campController.getCampById(campId);
        ArrayList<CampParticipant> campParticipants = campParticipantController.getListByCampId(campId);
        reportController.generateStudentReport(participantFilter, camp, campParticipants);
    }
    /**
     * Generates a performance report for camp participants and camp committee members.
     */
    public void generatePerformanceReport(){
        Camp camp = campController.getCampById(campId);
        ArrayList<CampParticipant> campParticipants = campParticipantController.getListByCampId(campId);
        ArrayList<CampParticipant> campCommittee = campParticipantController.getListOfCampCommitteeByCampId(campId);
        reportController.generatePerformanceReport(camp, campCommittee);
    }
    /**
     * Generates an enquiry report for the selected camp.
     */
    public void generateEnquiryReport(){
        Camp camp  = campController.getCampById(campId);
        ArrayList<Enquiry> enquiries = enquiryController.getEnquiriesByCampId(campId);
        reportController.generateEnquiryReport(camp, enquiries);

    }

}
