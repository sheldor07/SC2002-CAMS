package UI.Portal;

import UI.CampUI;
import UI.EnquiryUI;
import UI.UserUI;
import controller.UserController;
import entity.User;
/**
 * This class represents a role handler for students in the CAMs system.
 * It provides functionality for students to view and register for camps,
 * submit and manage inquiries, and change their passwords.
 */
public class StudentPageHandler implements RoleHandler {
    private User user;
    private UserUI userUI;
    private CampUI campUI;
    private EnquiryUI enquiryUI;
    private UserController userC;
    /**
     * Constructs a new StudentPageHandler with the given user and user controller.
     *
     * @param user The user for which this handler is created.
     * @param userC The user controller used for user-related operations.
     */
    public StudentPageHandler(User user, UserController userC) {
        this.user = user;
        this.userUI = new UserUI(user);
        this.campUI = new CampUI(user);
        this.enquiryUI = new EnquiryUI(user);
        this.userC = userC;
    }
    /**
     * Displays the student page and handles user interaction.
     *
     * @return True if the user chooses to logout, false otherwise.
     */
    @Override
    public boolean displayPage() {
        // Implementation of student page display logic
        int input = 0;
        do {

            input = userUI.displayStudentMenu();

            if (input == 1)
                campUI.showAvailableCampsForStudent(user);

            else if (input == 2) {
                boolean userSignedUpAsCommittee = campUI.registerCampUI();
                if (userSignedUpAsCommittee) {
                    return false;
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

                userC.changePassword();
                return true;

            } else if (input == 10) {

                System.out.println("Thank you and see you again.");
                return true;
            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);

        return false; // User does not choose to log out

    }
}