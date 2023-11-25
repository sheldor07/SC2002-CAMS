/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.CampDatabase;
import entity.Camp;
import java.util.Date;

import entity.Faculty;
import entity.User;
import java.util.ArrayList;

public class CampController {
    CampDatabase campDatabase;
    public CampController(){
        campDatabase = new CampDatabase("camp_list");
    }
    
    public void refreshCampDatabase(){
        campDatabase = new CampDatabase("camp_list");
    }
        
        public ArrayList<Camp> getCamps(){
            return campDatabase.getList();
        }
        
        public ArrayList<Camp> getCampsByStaff(int staffId){
            return campDatabase.getCampsByStaffInCharge(staffId);
        }
        
        public Camp getCampById(int Id){
            return campDatabase.getCampsById(Id);
        }
        
        public ArrayList<Camp> getCampsByFaculty(User user){
            return campDatabase.getCampsByUserFaculty(user);
        }
    
        public int createCamp(Camp camp){
            ArrayList<Camp> camps = campDatabase.getList();
            int max = 0;
            for(Camp c : camps){
                   if(c.getId() > max)
                       max = c.getId();
            }
            camp.setID(max+1);
            int result = campDatabase.add(camp);
            refreshCampDatabase();
            
            return result;
        }

	public boolean changeName(int campId, String choice) {
           return campDatabase.editRow(campId, campDatabase.COLUMN_CAMPNAME, choice);
           
	}
        
	public boolean changeStartDate(int campId, Date date) {
           return campDatabase.editRow(campId, campDatabase.COLUMN_STARTDATE, date);

	}

	public boolean changeEndDate(int campId, Date date) {
	   return campDatabase.editRow(campId, campDatabase.COLUMN_ENDDATE, date);

	}

	public boolean changeRegCloseDate(int campId, Date date) {
            return campDatabase.editRow(campId, campDatabase.COLUMN_REGISTRATIONDEADLINE, date);

	}

	public boolean setFacultyTo(int campId, Faculty faculty) {
	return campDatabase.editRow(campId, campDatabase.COLUMN_FACULTY, faculty);

	}

	public boolean changeLocation(int campId, String choice) {
	return campDatabase.editRow(campId, campDatabase.COLUMN_LOCATION, choice);
	}

	public boolean changeCampParticipantSlots(int campId, int slots) {
        return campDatabase.editRow(campId, campDatabase.COLUMN_SLOTS, slots);

	}

	public boolean changeDescription(int campId, String choice) {
            return campDatabase.editRow(campId, campDatabase.COLUMN_DESCRIPTION, choice);

	}

	public boolean changeVisibility(int campId, boolean b) {
            return campDatabase.editRow(campId, campDatabase.COLUMN_VISIBILITY, b);
	}

}
