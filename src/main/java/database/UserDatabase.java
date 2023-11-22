/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author weiya
 */
public class UserDatabase extends Database{
    
    
    public UserDatabase(String filePath){
        super(filePath);
    }
    
    
    boolean changePassword(User user, String newPassword)
    {      
        
        boolean result = editRow(user.getId(), "Password", newPassword);
        
        if(result == true){
            System.out.println("Password Changed succesfully.");
            return true;
        }  
           System.out.println("An error has occured in the password change");
           return false;           
        }
   
        
    }
    

