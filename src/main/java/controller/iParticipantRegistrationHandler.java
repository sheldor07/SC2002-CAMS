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
public interface iParticipantRegistrationHandler {

    boolean registerAsCommittee(User user, Camp camp);

    boolean registerAsParticipant(User user, Camp camp);
    
}
