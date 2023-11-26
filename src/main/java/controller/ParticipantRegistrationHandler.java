/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author weiya
 *//**
 * Handler class implementing the registration of users as participants or committee members in camps.
 * It manages the logic for adding users to the camp participant database and updating camp slots.
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
    /**
     * Constructor to initialize the ParticipantRegistrationHandler with necessary databases and controllers.
     */

    public ParticipantRegistrationHandler() {
        
        campController = new CampController();
        this.campParticipantDatabase = new CampParticipantDatabase("camp_participants");
        campDatabase = new CampDatabase("camp_list");
    }
    /**
     * Registers a user as a participant in a specific camp.
     * Ensures the user is not already registered and that the camp has available slots.
     *
     * @param user The user to be registered as a participant.
     * @param camp The camp where the user will be registered.
     * @return true if the registration is successful, false otherwise.
     */
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
    /**
     * Registers a user as a committee member in a specific camp.
     * Verifies that the user is not already registered and that there are available slots for committee members.
     *
     * @param user The user to be registered as a committee member.
     * @param camp The camp where the user will be registered.
     * @return true if the registration is successful, false otherwise.
     */
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
             
             CampParticipant campParticipant = new CampParticipant(max+1,camp.getId(),user.getId(),camp.getStaffInCharge(),true);
            
             if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
                camp.setCampCommSlots(camp.getCampCommSlots()-1);
                camp.setParticipantSlots(camp.getParticipantSlots()-1);
            }

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots()) && campParticipantDatabase.add(campParticipant);
 
    }
    
    
}

