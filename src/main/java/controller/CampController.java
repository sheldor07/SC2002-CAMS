/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import database.CampDatabase;
import entity.Camp;
import java.util.Date;

import entity.Faculty;
import entity.User;
import java.util.ArrayList;

public class CampController implements iCampController{
    CampDatabase campDatabase;

    public CampController() {
        campDatabase = new CampDatabase("camp_list");
    }

    public void refreshCampDatabase() {
        campDatabase = new CampDatabase("camp_list");
    }

    @Override
    public ArrayList<Camp> getCamps() {
        return campDatabase.getList();
    }

    @Override
    public ArrayList<Camp> getCampsByStaff(int staffId) {
        return campDatabase.getCampsByStaffInCharge(staffId);
    }

    @Override
    public Camp getCampById(int Id) {
        return campDatabase.getCampsById(Id);
    }

    @Override
    public ArrayList<Camp> getCampsByFaculty(User user) {
        return campDatabase.getCampsByUserFaculty(user);
    }

    @Override
    public int createCamp(Camp camp) {
        ArrayList<Camp> camps = campDatabase.getList();
        int max = 0;
        for (Camp c : camps) {
            if (c.getId() > max)
                max = c.getId();
        }
        camp.setID(max + 1);
        int result = campDatabase.add(camp);
        refreshCampDatabase();

        return result;
    }

    @Override
    public boolean changeCampName(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_CAMPNAME, choice);

    }

    @Override
    public boolean changeStartDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_STARTDATE, date);

    }

    @Override
    public boolean changeEndDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_ENDDATE, date);

    }

    @Override
    public boolean changeRegCloseDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_REGISTRATIONDEADLINE, date);

    }

    @Override
    public boolean setFacultyTo(int campId, Faculty faculty) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_FACULTY, faculty);

    }

    @Override
    public boolean changeLocation(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_LOCATION, choice);
    }

    @Override
    public boolean changeCampParticipantSlots(int campId, int slots) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_SLOTS, slots);

    }

    @Override
    public boolean changeDescription(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_DESCRIPTION, choice);

    }

    @Override
    public boolean changeVisibility(int campId, boolean b) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_VISIBILITY, b);
    }

    @Override
    public boolean checkSlots(Camp camp, ParticipantFilter participantFilter) {
        if (participantFilter == ParticipantFilter.ATTENDEE) {
            return camp.getParticipantSlots() > 0;
        } else if (participantFilter == ParticipantFilter.CAMP_COMMITTEE) {
            return camp.getCampCommSlots() > 0;
        }
        return false;
    }
    @Override
    public boolean registerParticipant(Camp camp, ParticipantFilter participantFilter){
        if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()-1);
        }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
            camp.setCampCommSlots(camp.getCampCommSlots()-1);
            camp.setParticipantSlots(camp.getParticipantSlots()-1);
        }
        refreshCampDatabase();

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots());
    }
    @Override
    public boolean withdrawParticipant(Camp camp, ParticipantFilter participantFilter){
        if(participantFilter == ParticipantFilter.ATTENDEE){
            camp.setParticipantSlots(camp.getParticipantSlots()+1);
        }else if(participantFilter == ParticipantFilter.CAMP_COMMITTEE){
            camp.setCampCommSlots(camp.getCampCommSlots()+1);
            camp.setParticipantSlots(camp.getParticipantSlots()+1);
        }

        return campDatabase.editRow(camp.getId(), campDatabase.COLUMN_SLOTS, camp.getParticipantSlots()) && campDatabase.editRow(camp.getId(), campDatabase.COLUMN_COMMITTEESLOTS, camp.getCampCommSlots());
    }

}
