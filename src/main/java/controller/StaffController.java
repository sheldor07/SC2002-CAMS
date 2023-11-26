/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StaffDatabase;
import entity.Camp;
import entity.Staff;
import java.util.ArrayList;

/**
 *
 * @author weiya
 *//**
 * Controller class for managing operations related to staff members.
 * It includes functionality to access staff information from a database.
 */
public class StaffController  {
      StaffDatabase staffDatabase;
    /**
     * Constructor to initialize the StaffController with a staff database.
     */
    public StaffController(){
        staffDatabase = new StaffDatabase("staff_list");
    }
    /**
     * Retrieves a staff member by their unique ID.
     *
     * @param id The unique ID of the staff member.
     * @return The Staff object if found, null otherwise.
     */
     public Staff getStaffById(int id){
            return staffDatabase.getListByStaffId(id);
        }
}
