/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.CampParticipantDatabase;
import entity.Camp;
import entity.CampParticipant;
import entity.Student;
import entity.User;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public class CampParticipantController {
    CampParticipantDatabase campParticipantDatabase;
    
    public CampParticipantController(){
        campParticipantDatabase = new CampParticipantDatabase("camp_participants");
    }
    
    public void refreshCampDatabase(){
        campParticipantDatabase = new CampParticipantDatabase("camp_participants");
    }
   
   public ArrayList<CampParticipant> getAllParticipants(){
       return campParticipantDatabase.getList();
   }
   
   public ArrayList<CampParticipant> getListByStudentId(int studentId){
       return campParticipantDatabase.getListByStudentId(studentId);
   }
   
   public boolean registerAsParticipant(User user, Camp camp) {
       
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getList();
             int max = 0;
             for(CampParticipant c : campParticipants){
                 if(c.getStudentId() == user.getId() && camp.getId() == c.getCampId())
                 {  
                     System.out.println("You are already registered in this camp.");
                     return false;
                 }
                 
                    if(c.getId() > max){
                        max = c.getId();
                    }
             }
             
             CampParticipant campParticipant = new CampParticipant(max+1,camp.getId(),user.getId(),camp.getStaffInCharge(),false);
             boolean result = campParticipantDatabase.add(campParticipant);
             refreshCampDatabase();

             return result;
                
		
	}

	public boolean registerAsCommittee(User user, Camp camp) {
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getList();
             int max = 0;
             for(CampParticipant c : campParticipants){
                 if(c.getStudentId() == user.getId() && camp.getId() == c.getCampId())
                 {  
                     System.out.println("You are already registered in this camp.");
                     return false;
                 }
                 
                    if(c.getId() > max){
                        max = c.getId();
                    }
             }
             
             CampParticipant campParticipant = new CampParticipant(max+1,camp.getId(),user.getId(),camp.getStaffInCharge(),true);
             boolean result = campParticipantDatabase.add(campParticipant);
             
             refreshCampDatabase();

             return result;
	}

	public boolean withdraw(int id) {
                    return campParticipantDatabase.delete(id);

	}
    
}
