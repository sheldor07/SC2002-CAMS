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
 * @author weiya
 */
public class Portal {
    private UserController userC;

    public void start() throws IOException {
        System.out.println("Welcome to Camp Application and Management System (CAMs)");
        boolean logout = false;

        do {
            userC = new UserController();
            User user = userC.login();
            do {
            if (user == null) {
                
                logout = true;
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
