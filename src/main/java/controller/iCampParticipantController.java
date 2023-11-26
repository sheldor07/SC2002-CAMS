/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import entity.Camp;
import entity.CampParticipant;
import entity.User;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public interface iCampParticipantController {

    ArrayList<CampParticipant> getAllParticipants();

    int getCampIdByCampCommitteeID(int campCommitteeID);

    CampParticipant getCommitteeByStudentId(int studentId);

    ArrayList<CampParticipant> getListByCampId(int campId);

    ArrayList<CampParticipant> getListByStudentId(int studentId);

    ArrayList<CampParticipant> getListOfCampCommitteeByCampId(int campId);

    boolean isCampCommittee(int campId, int studentId);

    void refreshCampDatabase();

    boolean registerAsCommittee(User user, Camp camp);

    boolean registerAsParticipant(User user, Camp camp);

    boolean withdraw(Camp camp, int id, ParticipantFilter participantFilter);
    
}
