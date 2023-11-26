/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Portal;

import UI.*;
import controller.UserController;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;

import java.io.IOException;

/**
 * The `Portal` class is the entry point of the Camp Application and Management System (CAMs) user interface.
 * It provides the main menu and user authentication flow to access the system based on the user's role.
 * Users can log in and perform various actions within the system, depending on their roles.
 *
 * This class manages user sessions and transitions between different role-specific handlers.
 *
 * @author weiya
 */
public class Portal {
    private UserController userC;
    /**
     * Starts the CAMs application by displaying the main menu and handling user interactions.
     *
     * @throws IOException If an I/O error occurs during user authentication or interaction.
     */
    public void start() throws IOException {
        System.out.println("Welcome to Camp Application and Management System (CAMs)");
        boolean logout = false;

        do {
            userC = new UserController();
            User user = userC.login();
            do {
            if (user == null) {
                
                logout = false;
            } else {
                if(user instanceof CampCommittee){
                user = userC.checkIfStudentIsCampCommittee((Student)user);
                }
                System.out.println("Welcome " + user.getName());
                RoleHandler roleHandler = RoleHandlerFactory.getHandler(user, userC);
                if (roleHandler != null) {
                     logout = roleHandler.displayPage();
                }
            }
        } while (!logout);
       }while(true);
        }
}
