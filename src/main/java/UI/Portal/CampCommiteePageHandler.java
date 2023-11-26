package UI.Portal;

import UI.*;
import controller.UserController;
import entity.User;
/**
 * The `CampCommiteePageHandler` class is responsible for managing the user interface and actions
 * specific to camp committee members in the CAMs (Camp Administration Management System) application.
 * It handles camp-related operations such as registering for camps, submitting and managing enquiries
 * and suggestions, generating reports, and changing the user's password.
 *
 * This class implements the `RoleHandler` interface to provide role-specific functionality.
 *
 * @author weiya
 */
public class CampCommiteePageHandler implements RoleHandler {
    private User user;
    private UserUI userUI;
    private CampUI campUI;
    private EnquiryUI enquiryUI;
    private SuggestionUI suggestionUI;
    private ReportUI reportUI;
    private UserController userC;
    /**
     * Constructs a new `CampCommiteePageHandler` instance with the specified user and controller.
     *
     * @param user The camp committee member user.
     * @param userC The user controller used for user-related actions.
     */
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
    /**
     * Displays the camp committee member's user interface and handles user interactions.
     * The method allows camp committee members to perform various actions within the system,
     * including registering for camps, managing enquiries and suggestions, generating reports,
     * and changing their password.
     *
     * @return `true` if the user wants to exit the camp committee page, `false` otherwise.
     */

    @Override
    public boolean displayPage() {
//        System.out.println("User ID"+ user.getId());
        reportUI.setCampIdForCommittee(user.getId());

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
                return false;
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