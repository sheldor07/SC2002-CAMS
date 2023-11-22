package entity;

import java.util.Collection;
import java.util.Date;

public class Camp implements Identity {

    /**
     * @return the information
     */
    public CampInformation getInformation() {
        return information;
    }
    private final CampInformation information;

    //        SuggestionDatabase suggestionDatabase;
    //        EnquiryDatabase enquiriesDatabase;
    public Camp(String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility) {
        // validation for campCommittee Slots is to be done inside the ui portion
        this.information = new CampInformation(name, location, description, startDate, endDate , closingDate, participantSlots, campCommSlots, staffInCharge, facultyOpenTo, visibility);

    }
    
    public Camp(int id, String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility) {
        // validation for campCommittee Slots is to be done inside the ui portion
        this.information = new CampInformation(id, name, location, description, startDate, endDate , closingDate, participantSlots, campCommSlots, staffInCharge, facultyOpenTo, visibility);

    }
    
    public void setID(int id){
        this.getInformation().setCampId(id);
    }
    
    public boolean isVisible(){
        return information.isVisible();
    }
    public void show(){
        information.setVisibility(true);
    }
    public void hide(){
        information.setVisibility(false);
    }
    public void openToNTU(){
        getInformation().setFacultyOpenTo(Faculty.NTU);
    }
    public void openToFaculty(Faculty faculty){
        getInformation().setFacultyOpenTo(faculty);
    }
    public String getName(){
        return getInformation().getName();
    }
    public void setName(String name){
        getInformation().setName(name);
    }
    public String getLocation(){
        return getInformation().getLocation();
    }
    public void setLocation(String location){
        getInformation().setLocation(location);
    }
    //getDates
    public Date getStartDate(){
        return getInformation().getStartDate();
    }
    public void setDates(Date startDate){
        getInformation().setStartDate(startDate);
    }
    
        //getDates
    public Date getEndDate(){
        return getInformation().getEndDate();
    }
    public void setEndDate(Date endDate){
        getInformation().setEndDate(endDate);
    }
    
    public Date getClosingDate(){
        return getInformation().getClosingDate();
    }
    public void setClosingDate(Date closingDate){
        getInformation().setClosingDate(closingDate);
    }
    public int getParticipantSlots(){
        return getInformation().getParticipantSlots();
    }
    public void setParticipantSlots(int participantSlots){
        getInformation().setParticipantSlots(participantSlots);
    }
    public int getCampCommSlots(){
        return getInformation().getCampCommSlots();
    }
    public void setCampCommSlots(int campCommSlots){
        getInformation().setCampCommSlots(campCommSlots);
    }
    public int getStaffInCharge(){
        return getInformation().getStaffInCharge();
    }
    public String getDescription(){
        return getInformation().getDescription();
    }
    public void setDescription(String description){
        getInformation().setDescription(description);
    }
    public Faculty getOpenTo(){
        return getInformation().getFacultyOpenTo();
    }
    public void setOpenTo(Faculty facultyOpenTo){
        getInformation().setFacultyOpenTo(facultyOpenTo);
    }
    public boolean isOpenTo(Faculty faculty){
        Faculty facultyOpenTo = getInformation().getFacultyOpenTo();
        return facultyOpenTo.equals(faculty);
    }
    public void getSuggestionDB(){

    }
    public void getEnquiryDB(){

    }
    @Override
    public int getId() {
        return getInformation().getCampId();
    }
}