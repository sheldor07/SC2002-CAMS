/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import controller.UserController;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;

import java.io.IOException;

/**
 * @author weiya
 */
public class Portal {
    UserUI userUI;
    boolean logout = false;
    UserController userC;

    public void start() throws IOException {
        System.out.println("Welcome to Camp Application and Management System (CAMs)");
        do {
            logout = false;
            userC = new UserController();
            User user = userC.login();

            if (user == null) {
                logout = true;
            }

            while (!logout) {//if user == null after successfully, and its null, he needs to relogin because password changed.

                userUI = new UserUI(user);

                if (user != null) {
                    System.out.println("Welcome " + user.getName());

                    if (user instanceof Student) {
                        user = userC.checkIfStudentIsCampCommittee((Student) user);

                        if (user instanceof CampCommittee) {
                            displayCampCommitteePage(user);
                        } else {
                            displayStudentPage(user);
                        }
                    } else {
                        displayStaffPage(user);

                    }
                    //once application runs, we cannot exit. should discuss and specify how one logs out.
                }
            }
        } while (true);

    }

    public void displayStudentPage(User user) {

        CampUI campUI = new CampUI(user);
        EnquiryUI enquiryUI = new EnquiryUI(user);
        int input = 0;
        do {

            input = userUI.displayStudentMenu();

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
                logout = true;
                userC.changePassword();
                break;
            } else if (input == 10) {
                logout = true;
                System.out.println("Thank you and see you again.");
                break;
            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);

    }

    public void displayStaffPage(User user) {

        CampUI campUI = new CampUI(user);
        EnquiryUI enquiryUI = new EnquiryUI(user);
        SuggestionUI suggestionUI = new SuggestionUI(user);
        ReportUI reportUI = new ReportUI(user);
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
                logout = true;
                userC.changePassword();
                break;

            } else if (input == 13) {
                logout = true;
                System.out.println("Thank you and see you again.");
                break;
            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);


    }

    public void displayCampCommitteePage(User user) {

        CampUI campUI = new CampUI(user);
        EnquiryUI enquiryUI = new EnquiryUI(user);
        SuggestionUI suggestionUI = new SuggestionUI(user);
        ReportUI reportUI = new ReportUI(user);
        reportUI.setCampIdForCommittee(user.getId()+1);
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

            }else if(input == 16){

                // Show list of camps and let the staff member choose on
                reportUI.generateEnquiryReport();

            }else if (input == 17) {
                userC.changePassword();
                logout = true;
                break;
            } else if (input == 18) {
                logout = true;
                System.out.println("Thank you and see you again.");
                break;
            } else {
                System.out.println("Please enter a valid input");
            }

        } while (input != 11);


    }


}
