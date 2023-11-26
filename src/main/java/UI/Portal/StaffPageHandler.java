package UI.Portal;

import UI.*;
import controller.UserController;
import entity.User;

public class StaffPageHandler implements RoleHandler {
    private User user;
    private UserUI userUI;
    private CampUI campUI;
    private EnquiryUI enquiryUI;
    private SuggestionUI suggestionUI;
    private ReportUI reportUI;

    private UserController userC;

    public StaffPageHandler(User user, UserController userC) {
        this.user = user;
        this.userUI = new UserUI(user);
        this.campUI = new CampUI(user);
        this.enquiryUI = new EnquiryUI(user);
        this.userC = userC;
    }


    @Override
    public boolean displayPage() {

        reportUI.setCampIdForCommittee(user.getId());

        int input = 0;
        do {

            input = userUI.displayStaffMenu();

            if (input == 1) {
                int result = campUI.showCreateCamp();
                if (result == 1)
                    System.out.println("Camp created succesfully.");
            } else if (input == 2) {
                System.out.println();
                campUI.printAllCamps();

            } else if (input == 3) {
                campUI.printCampCreatedByUser(user);
            } else if (input == 4) {
                campUI.editCampUI();
            } else if (input == 5) {
                enquiryUI.printListOfUserEnquiry();
            } else if (input == 6) {
                enquiryUI.replyEnquiryUI();
            } else if (input == 7) {
                suggestionUI.printUnansweredSuggestionUI();
            } else if (input == 8) {
                suggestionUI.updateUnansweredSuggestionsUI();
            } else if (input == 9) {
                // Show list of camps and let the staff member choose one
                int selectedCampId = reportUI.showListOfCamps();

                // Check if a valid camp ID was selected
                if (selectedCampId != -1) { // Assuming -1 is returned for an invalid selection
                    reportUI.setCampId(selectedCampId); // Set the selected campId
                    ParticipantFilter participantFilter = reportUI.askParticipantFilter();

                    reportUI.generateStudentListReport(participantFilter);
                } else {
                    System.out.println("Invalid camp selection.");
                }
            } else if (input == 10) {
                // Show list of camps and let the staff member choose one
                int selectedCampId = reportUI.showListOfCamps();

                // Check if a valid camp ID was selected
                if (selectedCampId != -1) { // Assuming -1 is returned for an invalid selection
                    reportUI.setCampId(selectedCampId); // Set the selected campId
                    reportUI.generatePerformanceReport();
                } else {
                    System.out.println("Invalid camp selection.");
                }
            } else if (input == 11) {
                // Show list of camps and let the staff member choose one
                int selectedCampId = reportUI.showListOfCamps();

                // Check if a valid camp ID was selected
                if (selectedCampId != -1) { // Assuming -1 is returned for an invalid selection
                    reportUI.setCampId(selectedCampId); // Set the selected campId
                    reportUI.generateEnquiryReport();
                } else {
                    System.out.println("Invalid camp selection.");
                }
            } else if (input == 12) {

                userC.changePassword();
                return true;


            } else if (input == 13) {

                System.out.println("Thank you and see you again.");
                return true;

            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);
        return false;
    }
}
