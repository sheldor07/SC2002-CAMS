/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import entity.Camp;

/**
 *
 * @author weiya
 *//**
 * Interface defining the contract for handling the withdrawal of participants from camps.
 * This includes a method for handling the withdrawal process based on the participant's type.
 */

public interface iWithdrawalHandler {
    /**
     * Handles the withdrawal of a participant (either a committee member or a general attendee)
     * from a camp based on the provided participant filter.
     *
     * @param camp The Camp instance from which the participant is withdrawing.
     * @param id The ID of the participant to be withdrawn.
     * @param participantFilter The type of participant (e.g., attendee, committee member) to be withdrawn.
     * @return true if the withdrawal is successful, false otherwise.
     */
    boolean withdraw(Camp camp, int id, ParticipantFilter participantFilter);
    
}
