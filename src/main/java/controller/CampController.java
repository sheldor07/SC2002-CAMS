/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import database.CampDatabase;
import entity.Camp;
import entity.Faculty;
import entity.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller class for managing various operations related to camps.
 * This includes accessing, updating, and managing camps data, and handling camp participant slots.
 */
public class CampController {
    private CampDatabase campDatabase;

    /**
     * Constructor to initialize the CampController with a specific camp database.
     */
    public CampController() {
        campDatabase = new CampDatabase("camp_list");
    }

    /**
     * Refreshes the current state of the camp database.
     */
    public void refreshCampDatabase() {
        campDatabase = new CampDatabase("camp_list");
    }

    /**
     * Retrieves a list of all camps.
     *
     * @return An ArrayList of Camp objects.
     */
    public ArrayList<Camp> getCamps() {
        return campDatabase.getList();
    }

    /**
     * Retrieves a list of camps managed by a specified staff member.
     *
     * @param staffId The ID of the staff member.
     * @return An ArrayList of Camp objects.
     */
    public ArrayList<Camp> getCampsByStaff(int staffId) {
        return campDatabase.getCampsByStaffInCharge(staffId);
    }

    /**
     * Retrieves a camp based on its ID.
     *
     * @param Id The ID of the camp.
     * @return The Camp object if found, null otherwise.
     */
    public Camp getCampById(int Id) {
        return campDatabase.getCampsById(Id);
    }

    /**
     * Retrieves a list of camps associated with the faculty of a given user.
     *
     * @param user The user whose faculty is to be considered.
     * @return An ArrayList of Camp objects.
     */
    public ArrayList<Camp> getCampsByFaculty(User user) {
        return campDatabase.getCampsByUserFaculty(user);
    }

    /**
     * Creates a new camp and adds it to the database.
     *
     * @param camp The Camp object to be added.
     * @return An integer indicating the result of the operation.
     */
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

    /**
     * Changes the name of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param choice The new name of the camp.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeCampName(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_CAMPNAME, choice);
    }

    /**
     * Changes the start date of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param date The new start date.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeStartDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_STARTDATE, date);
    }

    /**
     * Changes the end date of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param date The new end date.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeEndDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_ENDDATE, date);
    }

    /**
     * Changes the registration closing date for a specified camp.
     *
     * @param campId The ID of the camp.
     * @param date The new registration closing date.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeRegCloseDate(int campId, Date date) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_REGISTRATIONDEADLINE, date);
    }

    /**
     * Assigns a faculty to a specified camp.
     *
     * @param campId The ID of the camp.
     * @param faculty The faculty to be assigned.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean setFacultyTo(int campId, Faculty faculty) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_FACULTY, faculty);
    }

    /**
     * Changes the location of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param choice The new location of the camp.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeLocation(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_LOCATION, choice);
    }

    /**
     * Changes the number of participant slots for a specified camp.
     *
     * @param campId The ID of the camp.
     * @param slots The new number of slots.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeCampParticipantSlots(int campId, int slots) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_SLOTS, slots);
    }

    /**
     * Changes the description of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param choice The new description of the camp.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeDescription(int campId, String choice) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_DESCRIPTION, choice);
    }

    /**
     * Changes the visibility status of a specified camp.
     *
     * @param campId The ID of the camp.
     * @param visibility The new visibility status (true or false).
     * @return true if the operation was successful, false otherwise.
     */
    public boolean changeVisibility(int campId, boolean visibility) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_VISIBILITY, visibility);
    }

    /**
     * Checks if there are available slots for a participant in a given camp,
     * based on the participant filter (attendee or camp committee).
     *
     * @param camp The camp to check.
     * @param participantFilter The type of participant (ATTENDEE or CAMP_COMMITTEE).
     * @return true if slots are available, false otherwise.
     */
    public boolean checkSlots(Camp camp, ParticipantFilter participantFilter) {
        if (participantFilter == ParticipantFilter.ATTENDEE) {
            return camp.getParticipantSlots() > 0;
        } else if (participantFilter == ParticipantFilter.CAMP_COMMITTEE) {
            return camp.getCampCommSlots() > 0;
        }
        return false;
    }

    /**
     * Withdraws a participant from a camp based on the provided ID and participant filter.
     *
     * @param camp The camp from which to withdraw the participant.
     * @param id The ID of the participant.
     * @param participantFilter The type of participant (ATTENDEE or CAMP_COMMITTEE).
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdrawParticipant(Camp camp, int id, ParticipantFilter participantFilter) {
        iWithdrawalHandler withdrawlHandler = new WithdrawalHandler();
        boolean result = withdrawlHandler.withdraw(camp, id, participantFilter);
        if (result) {
            refreshCampDatabase();
        }
        return result;
    }
}
