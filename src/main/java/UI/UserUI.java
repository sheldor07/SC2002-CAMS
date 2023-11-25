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
 *
 * @author weiya
 */
public class UserUI extends UI{
   private UserController userController;
   private CampController campController;
   private CampParticipantController campParticipantController;
   public Scanner sc;
    
    public UserUI(User user){
        super(user);
        userController = new UserController();
        campController = new CampController();
        campParticipantController = new CampParticipantController();
        sc = new Scanner(System.in);
    }
    
    public int displayStaffMenu(){
        
        System.out.println("Welcome "+user.getName());
        int input = 0;
            
            String message = "(1) Create camp\n"
                    + "(2) View all camps\n"
                    + "(3) View camps created by you\n"
                    + "(4) Edit camps created by you\n"
                    + "(5) View your camp enquires\n"
                    + "(6) Reply your camp enquires\n"
                    + "(7) View suggestions for your camp\n"
                    + "(8) Approve suggestions for your camp\n"
                    + "(9) Generate student list report for your camp\n"
                    + "(10) Generate performance report for your camp\n"
                    + "(11) Generate enquiry report for your camp\n"
                    + "(11) Exit\n"
                    + "Enter the number of your choice: ";
           
           
           input = integerValidator(message);
        
           return input;
    }
    
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
                    + "(15) Generate report for your camp\n"
                    + "(16) Change password\n"
                    + "(17) Exit\n"
                    + "Enter the number of your choice: ";
           
           
           input = integerValidator(message);
        
           return input;
    }
    
    public void displayUserInfo(){
        System.out.println("Name: "+user.getName());
        System.out.println("Email: "+user.getEmail());
        System.out.println("Faculty: "+user.getFaculty());
        System.out.println();
    }
    
    
    
    
}
