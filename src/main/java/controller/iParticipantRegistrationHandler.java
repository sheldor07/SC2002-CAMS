/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Camp;
import entity.User;

/**
 *
 * @author weiya
 */
/**
 * Interface defining the contract for handling participant registrations in camps.
 * This includes methods for registering a user as either a committee member or a general participant in a camp.
 */

public interface iParticipantRegistrationHandler {
    /**
     * Registers a user as a committee member for a specific camp.
     * The implementation should handle the registration logic and return a boolean indicating the success of the operation.
     *
     * @param user The User instance to be registered as a committee member.
     * @param camp The Camp instance where the user is to be registered as a committee member.
     * @return true if registration is successful, false otherwise.
     */
    boolean registerAsCommittee(User user, Camp camp);
    /**
     * Registers a user as a general participant in a specific camp.
     * This method should handle the necessary logic for participant registration and return a boolean indicating the success of the operation.
     *
     * @param user The User instance to be registered as a participant.
     * @param camp The Camp instance where the user is to be registered as a participant.
     * @return true if registration is successful, false otherwise.
     */
    boolean registerAsParticipant(User user, Camp camp);
    
}
