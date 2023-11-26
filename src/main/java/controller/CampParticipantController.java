/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import database.CampParticipantDatabase;
import entity.*;

import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public class CampParticipantController {
    CampParticipantDatabase campParticipantDatabase;
    CampController campController;
    
    public CampParticipantController(){
        campParticipantDatabase = new CampParticipantDatabase("camp_participants");
        campController = new CampController();
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
   public CampParticipant getCommitteeByStudentId(int studentId){
       return campParticipantDatabase.getCommitteeByStudentId(studentId);
   }

   public ArrayList<CampParticipant> getListByCampId(int campId){
//        System.out.println("campId: " + campId);
       return campParticipantDatabase.getListByCampId(campId);
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
       if(!campController.checkSlots(camp, ParticipantFilter.ATTENDEE)){
           System.out.println("Camp is full. No more participants allowed");
           return false;
       }
             
             CampParticipant campParticipant = new CampParticipant(max+1,camp.getId(),user.getId(),camp.getStaffInCharge(),false);
             boolean result = campParticipantDatabase.add(campParticipant);
             campController.registerParticipant(camp, ParticipantFilter.ATTENDEE);

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
         if(!campController.checkSlots(camp, ParticipantFilter.CAMP_COMMITTEE)){
              System.out.println("Camp is full. No more committee allowed.");
              return false;
         }

             CampParticipant campParticipant = new CampParticipant(max+1,camp.getId(),user.getId(),camp.getStaffInCharge(),true);
             boolean result = campParticipantDatabase.add(campParticipant);
             campController.registerParticipant(camp, ParticipantFilter.CAMP_COMMITTEE);
             refreshCampDatabase();

             return result;
	}

	public boolean withdraw(Camp camp,int id, ParticipantFilter participantFilter) {
        campController.withdrawParticipant(camp, participantFilter);

        boolean result =  campParticipantDatabase.delete(id);
        refreshCampDatabase();
        return result;



	}
    public ArrayList<CampParticipant> getListOfCampCommitteeByCampId(int campId){
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getListByCampId(campId);
        ArrayList<CampParticipant> campCommittee = new ArrayList<>();
        for(CampParticipant c : campParticipants){
            if(c.isCampCommittee()){
                campCommittee.add(c);
            }
        }
        return campCommittee;
    }

    public boolean isCampCommittee(int campId, int studentId){
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getListByCampId(campId);
        for(CampParticipant c : campParticipants){
            if(c.getStudentId() == studentId && c.isCampCommittee()){
                return true;
            }
        }
        return false;
    }
}
