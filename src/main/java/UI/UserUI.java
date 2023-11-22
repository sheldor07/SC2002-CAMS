/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import controller.CampController;
import controller.UserController;
import entity.Camp;
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
public class UserUI extends MainUI{
   private UserController userController;
   private CampController campController;
    
    public UserUI(User user){
        super(user);
        userController = new UserController();
        campController = new CampController();
    }
    
//    public int displayStaffPage(){
//        
//        System.out.println("Welcome "+user.getName());
//        int input = 0;
//        Scanner sc = new Scanner(System.in);
//        
//        do{
//           System.out.println("(1) Create camp");
//           System.out.println("(2) View all camps");
//           System.out.println("(3) View camps created by you");
//           System.out.println("(4) View your camp enquires");
//           System.out.println("(5) View suggestions for your camp");
//           System.out.println("(6) Exit");
//           System.out.print("Enter the number of your choice: ");
//           input = sc.nextInt();
//           
//           if(input == 1)
//           {
//           if(result == 1)
//               System.out.println("Camp created succesfully.");
//           }
//           
//           else if(input == 2){
//               ArrayList<Camp> camps = campController.getCamps();
//               
//               
//           }
//           else if(input == 3){
//               
//           }
//           else if(input == 4){
//               
//           }
//           else if(input == 5){
//               
//           }
//           else{
//               System.out.println("Please enter a valid input");
//           }
//
//          }while(input != 5);
//        
//        return input;
//    }
    
    public void displayUserInfo(){
        System.out.println("Name: "+user.getName());
        System.out.println("Email: "+user.getEmail());
        System.out.println("Faculty: "+user.getFaculty());
        System.out.println();
    }
    
    
    
    
}
