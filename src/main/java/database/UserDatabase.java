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
 * This class represents a generic user database for managing user records in an Excel file.
 * It extends the Database class and provides common methods for querying and manipulating user data.
 *
 * @author weiya
 */
public class UserDatabase extends Database{
    
    public final String COLUMN_NAME = "Name";
    public final String COLUMN_ID = "ID";
    public final String COLUMN_EMAIL = "Email";
    public final String COLUMN_PASSWORD = "Password";
    public final String COLUMN_FACULTY = "Faculty";
    /**
     * Constructor for the UserDatabase class.
     *
     * @param filePath The path to the Excel file used as the user database.
     */
    
    public UserDatabase(String filePath){
        super(filePath);
    }
    /**
     * Changes the password of a user.
     *
     * @param user The User object whose password needs to be changed.
     * @param newPassword The new password for the user.
     * @return true if the password is successfully changed, false otherwise.
     */
    
    boolean changePassword(User user, String newPassword)
    {      
        return editRow(user.getId(), COLUMN_PASSWORD, newPassword);
    }
    
}
