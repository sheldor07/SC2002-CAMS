/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import controller.CampController;
import controller.UserController;
import database.CampDatabase;
import entity.Camp;
import entity.Staff;
import entity.Student;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author weiya
 */
public class Portal {
    UserUI userUI;
    
    public void start() throws IOException{
        System.out.println("Welcome to Camp Application and Management System (CAMs)");
        
        UserController userC = new UserController();
        User user = userC.login();
        userUI = new UserUI(user);

//        //ignore this part for now
//        
        if(user instanceof Student){
            
            displayStudentPage((Student)user);
        }
        else{
            displayStaffPage((Staff)user);
        }
    }
    
    public void displayStudentPage(User user){
        
        //...
        
    }
    
    public void displayStaffPage(User user) throws IOException{
        System.out.println("Welcome "+user.getName());
        int input = 0;
        Scanner sc = new Scanner(System.in);
        CampUI campUI = new CampUI(user);

        do{
           System.out.println("(1) Create camp");
           System.out.println("(2) View all camps");
           System.out.println("(3) View camps created by you");
           System.out.println("(4) Edit camps created by you");
           System.out.println("(5) View your camp enquires");
           System.out.println("(6) View suggestions for your camp");
           System.out.println("(7) Exit");
           System.out.print("Enter the number of your choice: ");
           input = sc.nextInt();
           
           if(input == 1)
           {    int result = campUI.showCreateCamp();
                if(result == 1)
                    System.out.println("Camp created succesfully.");
           }
           
           else if(input == 2){
               System.out.println("");
               campUI.printAllCamps();
               
           }
           else if(input == 3){
               campUI.printCampCreatedByUser(user);
           }
           else if(input == 4){
               campUI.editCampUI(user);
           }
           else if(input == 5){
               System.out.println("Thank you and see you again.");
               break;
           }
           else{
               System.out.println("Please enter a valid input");
           }

          }while(input != 5);
        

    }
    
    
    
}
