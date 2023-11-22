/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StaffDatabase;
import database.StudentDatabase;
import database.UserDatabase;
import database.StaffDatabase;
import database.StudentDatabase;
import entity.Staff;
import entity.Student;
import entity.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author weiya
 */
public class UserController {
    
    User user;
    StudentDatabase studentDatabase;
    StaffDatabase staffDatabase;
    
    
    public User login(){
        
        //the reason why we cannot simply have a userDatabase and call get list of users is because
        //the 2 files are in different folders. 
        
        //1) If we use UserDatabase to access 2 different excel folders,
        //it would means that there is no point having StudentDatabase & StaffDatabase since they 
        //have nothing to offer.
        
        //2)a "Database" object can contain only one excel file path UserDatabase cannot be constructed with
        //  2 file paths(one student and one staff).
        
        staffDatabase = new StaffDatabase("staff_list");
        studentDatabase = new StudentDatabase("student_list");
        
        
        ArrayList<Student> students = studentDatabase.getList();
                
        ArrayList<Staff> staffs = staffDatabase.getList();
        
        
        Scanner sc = new Scanner(System.in);
        
        String userID, password;
        boolean successful = false;
        
        while(!successful){
        System.out.print("Enter your UserID: ");
        userID = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
        
        
        
        for(Student student: students){
            String email = student.getEmail();
            String regex = "(@e.ntu.edu.sg;)$";
            String processedEmail = email.replaceAll(regex,"");
            if(processedEmail.equals(userID) && student.getPassword().equals(password)){
               System.out.println("Login successful!");
               
                user = student;
                    if(user.getPassword().equals("password")){
                    System.out.println("You are required to change your default password: ");
                    changePassword();
            }
                    return user;
        }
        }
        
        for(Staff staff: staffs){
            String email = staff.getEmail();
            String regex = "(@NTU.EDU.SG)$";
            String processedEmail1 = email.replaceAll(regex,"");
            
            String email2 = staff.getEmail();
            String regex2 = "(@ntu.edu.sg)$";
            String processedEmail2 = email.replaceAll(regex2,"");
            
            if((processedEmail1.equals(userID) || processedEmail2.equals(userID)) && staff.getPassword().equals(password)){
                user = staff;
               System.out.println("Login successful!");
                    if(user.getPassword().equals("password")){
                    System.out.println("You are required to change your default password: ");
                    changePassword();
               
            }
                    return user;
        }       
           }
        
          System.out.println("Login unsuccessful, try again");

        
    

    }
        return null;
    }
    
    int changePassword(){

    //make it strong password?
    Scanner sc = new Scanner(System.in);
    boolean passwordChanged = false;
    while(!passwordChanged){
        System.out.print("Enter your New Password: ");
        String newPassword = sc.nextLine();
        System.out.print("Confirm your New Password: ");
        String confirmNewPassword = sc.nextLine();

        if(newPassword.equals(confirmNewPassword)){

            int result = 0;

            if(user instanceof Student)
             studentDatabase.changePassword(user, confirmNewPassword);
            else if(user instanceof Staff)
             staffDatabase.changePassword(user, confirmNewPassword);

            if(result == 1){
                System.out.println("Password Changed succesfully.");
            }  

            break;
        }
        else{
            System.out.println("The password does not match.");
        }
    }
    return 0;
}
    
}
