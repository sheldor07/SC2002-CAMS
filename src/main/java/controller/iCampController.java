/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import entity.Camp;
import entity.Faculty;
import entity.User;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author weiya
 */
public interface iCampController {
    
    public ArrayList<Camp> getCamps();
    public ArrayList<Camp> getCampsByStaff(int staffId);
    public Camp getCampById(int Id);
    public ArrayList<Camp> getCampsByFaculty(User user);
    public int createCamp(Camp camp);
    public boolean changeCampName(int campId, String choice);
    public boolean changeStartDate(int campId, Date date);
     public boolean changeEndDate(int campId, Date date);
     public boolean changeRegCloseDate(int campId, Date date);
     public boolean setFacultyTo(int campId, Faculty faculty);
     public boolean changeLocation(int campId, String choice);
     public boolean changeCampParticipantSlots(int campId, int slots);
     public boolean changeDescription(int campId, String choice);
     public boolean changeVisibility(int campId, boolean b);
     public boolean checkSlots(Camp camp, ParticipantFilter participantFilter);
     public boolean registerParticipant(Camp camp, ParticipantFilter participantFilter);
     public boolean withdrawParticipant(Camp camp, ParticipantFilter participantFilter);
    
}
