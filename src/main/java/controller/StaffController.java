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
 */
public class StaffController {
      StaffDatabase staffDatabase;
    
    public StaffController(){
        staffDatabase = new StaffDatabase("staff_list");
    }
    
     public Staff getStaffById(int id){
            return staffDatabase.getListByStaffId(id);
        }
}
