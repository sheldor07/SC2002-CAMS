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
    
    public final String COLUMN_NAME = "Name";
    public final String COLUMN_ID = "ID";
    public final String COLUMN_EMAIL = "Email";
    public final String COLUMN_PASSWORD = "Password";
    public final String COLUMN_FACULTY = "Faculty";
    
    
    public UserDatabase(String filePath){
        super(filePath);
    }
    
    
    boolean changePassword(User user, String newPassword)
    {      
        return editRow(user.getId(), COLUMN_PASSWORD, newPassword);
    }
    
}
