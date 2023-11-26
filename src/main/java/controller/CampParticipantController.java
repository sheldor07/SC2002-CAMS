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
/**
 * Controller class for managing camp participants.
 * Handles operations such as registration, retrieving participant lists, and checking participant roles.
 */
public class CampParticipantController {
    CampParticipantDatabase campParticipantDatabase;
    CampController campController;
    /**
     * Constructor to initialize the CampParticipantController with a participant database and a camp controller.
     */
    public CampParticipantController(){
        campParticipantDatabase = new CampParticipantDatabase("camp_participants");
        campController = new CampController();
    }
    /**
     * Refreshes the current state of the camp participant database.
     */
    public void refreshCampParticipantDatabase(){
        campParticipantDatabase = new CampParticipantDatabase("camp_participants");
    }

    /**
     * Retrieves all camp participants.
     *
     * @return An ArrayList of CampParticipant objects.
     */
   public ArrayList<CampParticipant> getAllParticipants(){
       return campParticipantDatabase.getList();
   }
    /**
     * Retrieves a list of camp participants based on a student's ID.
     *
     * @param studentId The ID of the student.
     * @return An ArrayList of CampParticipant objects.
     */
   public ArrayList<CampParticipant> getListByStudentId(int studentId){
       return campParticipantDatabase.getListByStudentId(studentId);
   }
    /**
     * Retrieves a list of camp participants based on a camp's ID.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of CampParticipant objects.
     */
   public CampParticipant getCommitteeByStudentId(int studentId){
       return campParticipantDatabase.getCommitteeByStudentId(studentId);
   }
    /**
     * Retrieves a list of camp participants based on a camp's ID.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of CampParticipant objects.
     */
    
   public ArrayList<CampParticipant> getListByCampId(int campId){
//        System.out.println("campId: " + campId);
       return campParticipantDatabase.getListByCampId(campId);
   }
    /**
     * Registers a user as a participant in a camp.
     *
     * @param user The user to be registered.
     * @param camp The camp in which the user will participate.
     * @return true if the registration was successful, false otherwise.
     */
   public boolean registerAsParticipant(User user, Camp camp) {
       
       ParticipantRegistrationHandler handler = new ParticipantRegistrationHandler();
       
       return handler.registerAsParticipant(user, camp);

	}
    /**
     * Registers a user as a committee member in a camp.
     *
     * @param user The user to be registered.
     * @param camp The camp for which the user will be a committee member.
     * @return true if the registration was successful, false otherwise.
     */
     public boolean registerAsCommittee(User user, Camp camp) {
       
       iParticipantRegistrationHandler handler = new ParticipantRegistrationHandler();
       
       return handler.registerAsCommittee(user, camp);

	}

    /**
     * Retrieves a list of camp committee members for a specific camp ID.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of CampParticipant objects who are committee members.
     */
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
    /**
     * Checks if a student is a camp committee member for a specific camp.
     *
     * @param campId The ID of the camp.
     * @param studentId The ID of the student.
     * @return true if the student is a committee member for the camp, false otherwise.
     */
    public boolean isCampCommittee(int campId, int studentId){
        ArrayList<CampParticipant> campParticipants = campParticipantDatabase.getListByCampId(campId);
        for(CampParticipant c : campParticipants){
            if(c.getStudentId() == studentId && c.isCampCommittee()){
                return true;
            }
        }
        return false;
    }
    /**
     * Retrieves the camp ID associated with a specific camp committee member.
     *
     * @param campCommitteeID The ID of the camp committee member.
     * @return The camp ID if found, -1 or another indicator if not found.
     */
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
