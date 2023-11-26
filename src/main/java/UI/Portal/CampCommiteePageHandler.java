package UI.Portal;

import UI.*;
import controller.UserController;
import entity.User;

public class CampCommiteePageHandler implements RoleHandler {
    private User user;
    private UserUI userUI;
    private CampUI campUI;
    private EnquiryUI enquiryUI;
    private SuggestionUI suggestionUI;
    private ReportUI reportUI;
    private UserController userC;
    public CampCommiteePageHandler(User user, UserController userC) {
        this.user = user;
        this.userUI = new UserUI(user);
        this.campUI = new CampUI(user);
        this.enquiryUI = new EnquiryUI(user);
        this.suggestionUI = new SuggestionUI(user);
        this.reportUI = new ReportUI(user);
        this.reportUI.setCampIdForCommittee(user.getId()); // Assuming this sets the campId correctly
        this.userC = userC;
    }

    @Override
    public boolean displayPage() {
        reportUI.setCampIdForCommittee(user.getId() + 1);
        int input = 0;
        do {

            input = userUI.displayCampCommitteeMenu();

            if (input == 1)
                campUI.showAvailableCampsForStudent(user);

            else if (input == 2) {
                boolean userSignedUpAsCommittee = campUI.registerCampUI();
                if (userSignedUpAsCommittee) {
                    break;
                }

            } else if (input == 3) {
                campUI.showRegisteredCamps();
            } else if (input == 4) {
                campUI.withdrawCampUI();
            } else if (input == 5) {
                enquiryUI.submitEnquiryUI();
            } else if (input == 6) {
                enquiryUI.printListOfProcessedEnquiry();
                enquiryUI.printListOfUnprocessedEnquiry();
            } else if (input == 7) {
                enquiryUI.editEnquiryUI();
            } else if (input == 8) {
                enquiryUI.deleteEnquiryUI();
            } else if (input == 9) {
                enquiryUI.printListOfUserEnquiry();
            } else if (input == 10) {
                enquiryUI.replyEnquiryUI();
            } else if (input == 11) {
                suggestionUI.submitSuggestionUI();
            } else if (input == 12) {
                suggestionUI.printUnansweredSuggestionUI();
                suggestionUI.printApprovedSuggestionUI();
                suggestionUI.printRejectedSuggestionUI();
            } else if (input == 13) {
                suggestionUI.editUnansweredSuggestionUI();
            } else if (input == 14) {
                suggestionUI.deleteUnansweredSuggestionUI();
            } else if (input == 15) {
                // Check if a valid camp ID was selected
                ParticipantFilter participantFilter = reportUI.askParticipantFilter();
                reportUI.generateStudentListReport(participantFilter);

            } else if (input == 16) {

                // Show list of camps and let the staff member choose on
                reportUI.generateEnquiryReport();

            } else if (input == 17) {
                userC.changePassword();
                return true;
            } else if (input == 18) {

                System.out.println("Thank you and see you again.");
                return true;


            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);
return false;
    }
}