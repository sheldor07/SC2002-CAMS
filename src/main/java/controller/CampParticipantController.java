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
    
    public void refreshCampParticipantDatabase(){
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
       
       ParticipantRegistrationHandler handler = new ParticipantRegistrationHandler();
       
       return handler.registerAsParticipant(user, camp);

	}
   
     public boolean registerAsCommittee(User user, Camp camp) {
       
       iParticipantRegistrationHandler handler = new ParticipantRegistrationHandler();
       
       return handler.registerAsParticipant(user, camp);

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
    
       public int getCampIdByCampCommitteeID(int campCommitteeID) {
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getList();
        for (CampParticipant c : campParticipants) {
            if (c.getId() == campCommitteeID && c.isCampCommittee()) {
                return c.getCampId();
            }
        }
        return -1; // or any other indication that the camp ID was not found
    }

}
