/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author weiya
 */

import entity.Camp;
import UI.ParticipantFilter;
import database.CampDatabase;
import database.CampParticipantDatabase;

/**
 * Handler class for managing the withdrawal of participants from camps.
 * It implements the iWithdrawalHandler interface and handles the logic for removing participants and updating camp slots.
 */
public class WithdrawalHandler implements iWithdrawalHandler {
    private final CampParticipantDatabase campParticipantDatabase;
    private final CampDatabase campDatabase;
    /**
     * Constructor to initialize the WithdrawalHandler with participant and camp databases.
     */
    public WithdrawalHandler() {
        campParticipantDatabase =new CampParticipantDatabase("camp_participants");
        campDatabase = new CampDatabase("camp_list");
    }
    /**
     * Handles the withdrawal of a participant (either attendee or camp committee member) from a camp.
     * It updates the camp slots accordingly and removes the participant from the database.
     *
     * @param camp The camp from which the participant is withdrawing.
     * @param id The ID of the participant.
     * @param participantFilter The type of participant (ATTENDEE or CAMP_COMMITTEE).
     * @return true if the withdrawal process is successful, false otherwise.
     */
    @Override
    public boolean withdraw(Camp camp, int id, ParticipantFilter participantFilter) {
        
        if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()+1);
        }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
            camp.setCampCommSlots(camp.getCampCommSlots()+1);
            camp.setParticipantSlots(camp.getParticipantSlots()+1);
        }

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots()) && campParticipantDatabase.delete(id);



	}

}
