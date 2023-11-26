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

public class WithdrawalHandler implements iWithdrawalHandler {
    private final CampParticipantDatabase campParticipantDatabase;
    private final CampDatabase campDatabase;

    public WithdrawalHandler() {
        campParticipantDatabase =new CampParticipantDatabase("camp_participants");
        campDatabase = new CampDatabase("camp_list");
    }
    
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
