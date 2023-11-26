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
 */
public interface iWithdrawalHandler {

    boolean withdraw(Camp camp, int id, ParticipantFilter participantFilter);
    
}
