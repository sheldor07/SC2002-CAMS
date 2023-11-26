/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import controller.CampController;
import controller.CampParticipantController;
import controller.UserController;
import entity.Camp;
import entity.CampParticipant;
import entity.Faculty;
import entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * * A user interface class that provides menus and options specific to different user roles,
 *  * such as staff, students, and camp committee members.
 * @author weiya
 */
public class UserUI extends UI{
    private UserController userController;
    private CampController campController;
    private CampParticipantController campParticipantController;
    public Scanner sc;
    /**
     * Constructs a new instance of the UserUI class for a specific user.
     *
     * @param user The user for whom the user interface is created.
     */

    public UserUI(User user){
        super(user);
        userController = new UserController();
        campController = new CampController();
        campParticipantController = new CampParticipantController();
        sc = new Scanner(System.in);
    }
    /**
     * Displays the menu options for staff users and returns the selected choice.
     *
     * @return The selected menu option for staff users.
     */
    public int displayStaffMenu(){

        int input = 0;

        String message = "(1) Create camp\n"
                + "(2) View all camps\n"
                + "(3) View camps created by you\n"
                + "(4) Edit camps created by you\n"
                + "(5) View your camp enquires\n"
                + "(6) Reply your camp enquires\n"
                + "(7) View suggestions for your camp\n"
                + "(8) Approve/Reject suggestions for your camp\n"
                + "(9) Generate student list report for your camp\n"
                + "(10) Generate performance report for your camp\n"
                + "(11) Generate enquiry report for your camp\n"
                + "(12) Change password\n"
                + "(13) Exit\n"
                + "Enter the number of your choice: ";


        input = integerValidator(message);

        return input;
    }
    /**
     * Displays the menu options for student users and returns the selected choice.
     *
     * @return The selected menu option for student users.
     */
    public int displayStudentMenu(){
        int input = 0;

        String message = "(1) View available camps for sign up\n"
                + "(2) Register for a camp\n"
                + "(3) View registered camps\n"
                + "(4) Request withdrawal from camp\n"
                + "(5) Submit enquires for a camp\n"
                + "(6) View submitted enquiry for a camp\n"
                + "(7) Edit submitted unanswered enquiry for a camp\n"
                + "(8) Delete submitted unanswered enquiry for a camp\n"
                + "(9) Change password\n"
                + "(10) Exit\n"
                + "Enter the number of your choice: ";


        input = integerValidator(message);

        return input;
    }

    /**
     * Displays the menu options for camp committee members and returns the selected choice.
     *
     * @return The selected menu option for camp committee members.
     */
    public int displayCampCommitteeMenu(){

        int input = 0;

        String message = "(1) View available camps for sign up\n"
                + "(2) Register for a camp\n"
                + "(3) View registered camps\n"
                + "(4) Request withdrawal from camp\n"
                + "(5) Submit enquires for a camp\n"

                + "(6) View submitted enquiry for a camp\n"
                + "(7) Edit submitted unanswered enquiry for a camp\n"
                + "(8) Delete submitted unanswered enquiry for a camp\n"

                + "(9) View enquiries for your camp\n"
                + "(10) Reply enquiries for your camp\n"

                //
                + "(11) Submit suggestions for a camp\n"
                + "(12) View submitted camp suggestions\n"
                + "(13) Edit submitted camp suggestions\n"
                + "(14) Delete submitted camp suggestions\n"
                //
                + "(15) Generate student report for your camp\n"
                + "(16) Generate enquiry report for your camp\n"
                + "(17) Change password\n"
                + "(18) Exit\n"
                + "Enter the number of your choice: ";


        input = integerValidator(message);

        return input;
    }

}
