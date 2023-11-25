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
import entity.CampCommittee;
import entity.CampParticipant;
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
        int wrongPassword = 0;
        System.out.print("Enter your UserID: ");
        userID = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
        
        
        
        for(Student student: students){
            String email = student.getEmail();
            String regex = "(@e.ntu.edu.sg;)$";
            String processedEmail = email.replaceAll(regex,"");
            if(processedEmail.equals(userID)){
                wrongPassword = 1;
            if(student.getPassword().equals(password)){
               System.out.println("Login successful!");
                    
                    user = checkIfStudentIsCampCommittee(student);
               
                    if(user.getPassword().equals("password")){
                    System.out.println("You are required to change your default password: ");
                    return changePassword();
            }
                    return user;
             }
            
        }
        
        }
        
        for(Staff staff: staffs){
            String email = staff.getEmail();
            String regex = "(@NTU.EDU.SG)$";
            String processedEmail1 = email.replaceAll(regex,"");
            
            String email2 = staff.getEmail();
            String regex2 = "(@ntu.edu.sg)$";
            String processedEmail2 = email.replaceAll(regex2,"");
            
            if((processedEmail1.equals(userID) || processedEmail2.equals(userID))){
                wrongPassword = 1;
            if(staff.getPassword().equals(password)){
                user = staff;
               System.out.println("Login successful!");
                    if(user.getPassword().equals("password")){
                    System.out.println("You are required to change your default password: ");
                    return changePassword();
               
            }
                    return user;
        }       
            }
           }
        
        if(wrongPassword == 1)
          System.out.println("Wrong Password. Please try again.");
        else
          System.out.println("Login unsuccessful, try again");


    }
        return null;
    }
    
        public User checkIfStudentIsCampCommittee(Student student){
            //checking campparticipant database to see if user is campcommitee
        CampParticipantController campParticipantController = new CampParticipantController();
         ArrayList<CampParticipant> campParticipants = campParticipantController.getAllParticipants();
         
         for(CampParticipant c : campParticipants)
         {    
             if(c.getStudentId() == student.getId() && c.isCampCommittee()){                 
                 return new CampCommittee(student.getId(),student.getName(),student.getEmail(),student.getPassword(),student.getFaculty());
                 
             }
         }
             
        
        return student;
        
    }
    
    public User logout(){
        System.out.println("Logged out.");
        return null;
        
    }
    
    public User changePassword(){

    //make it strong password?
    Scanner sc = new Scanner(System.in);
    boolean passwordChanged = false;
    while(!passwordChanged){
        System.out.print("Enter your New Password: ");
        String newPassword = sc.nextLine();
        System.out.print("Confirm your New Password: ");
        String confirmNewPassword = sc.nextLine();

        if(newPassword.equals(confirmNewPassword)){

            boolean result = false;

            if(user instanceof Student)
             result = studentDatabase.changePassword(user, confirmNewPassword);
            else if(user instanceof Staff)
             result = staffDatabase.changePassword(user, confirmNewPassword);

            if(result == true){
                System.out.println("Password Changed succesfully.");
                System.out.println("Please re-login to validate again.");
                return logout();
            }  

//            break;
        }
        else{
            System.out.println("The password does not match.");
        }
    }
    return null;
}
    
}
