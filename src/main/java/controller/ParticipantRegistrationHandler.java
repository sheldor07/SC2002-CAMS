/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author weiya
 */
import UI.ParticipantFilter;
import database.CampDatabase;
import database.CampParticipantDatabase;
import entity.Camp;
import entity.CampParticipant;
import entity.User;
import java.util.ArrayList;

public class ParticipantRegistrationHandler implements iParticipantRegistrationHandler {
    
    private final CampParticipantDatabase campParticipantDatabase;
    private final CampDatabase campDatabase;

    CampController campController;

    public ParticipantRegistrationHandler() {
        
        campController = new CampController();
        this.campParticipantDatabase = new CampParticipantDatabase("camp_participants");
        campDatabase = new CampDatabase("camp_list");
    }

    @Override
    public boolean registerAsParticipant(User user, Camp camp) {
        
    ParticipantFilter participantFilter = ParticipantFilter.CAMP_COMMITTEE;
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
            
             if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
                camp.setCampCommSlots(camp.getCampCommSlots()-1);
                camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots()) && campParticipantDatabase.add(campParticipant);
 
    }
    
    @Override
    public boolean registerAsCommittee(User user, Camp camp) {

        ParticipantFilter participantFilter = ParticipantFilter.CAMP_COMMITTEE;
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
            
             if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
                camp.setCampCommSlots(camp.getCampCommSlots()-1);
                camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots()) && campParticipantDatabase.add(campParticipant);
 
    }
    
    
}

