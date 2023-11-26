package entity;
/**
 * The `CampInformation` class represents information about a camp, including its name, location,
 * description, dates, slots, staff in charge, faculty open to, and visibility status.
 * This class is used to store and retrieve camp details.
 *
 * @author weiya
 */
import java.util.Date;
 public class CampInformation {

    private int campId;
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private Date closingDate;
    private int participantSlots;
    private int campCommSlots;
    private String description;
    private int staffInCharge;
    private Faculty facultyOpenTo;
    private boolean visibility;


     /**
      * Constructs a new `CampInformation` object with the specified attributes.
      *
      * @param name The name of the camp.
      * @param location The location of the camp.
      * @param description The description of the camp.
      * @param startDate The start date of the camp.
      * @param endDate The end date of the camp.
      * @param closingDate The closing date for registrations.
      * @param participantSlots The number of participant slots available.
      * @param campCommSlots The number of camp committee slots available.
      * @param staffInCharge The ID of the staff member in charge.
      * @param facultyOpenTo The faculty to which the camp is open.
      * @param visibility The visibility status of the camp.
      */
    public CampInformation(String name, String location,String description, Date startDate, Date endDate, Date closingDate, int participantSlots,int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility){
        // validation for campCommittee Slots is to be done inside the ui portion
//        this.campId = UUIDGenerator.generate();
        this.name = name;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closingDate = closingDate;
        this.participantSlots = participantSlots;
        this.campCommSlots = campCommSlots;
        this.staffInCharge = staffInCharge;
        this.facultyOpenTo = facultyOpenTo;
        this.visibility = visibility;
    }

     /**
      * Constructs a new `CampInformation` object with the specified attributes, including an ID.
      *
      * @param id The unique identifier of the camp information.
      * @param name The name of the camp.
      * @param location The location of the camp.
      * @param description The description of the camp.
      * @param startDate The start date of the camp.
      * @param endDate The end date of the camp.
      * @param closingDate The closing date for registrations.
      * @param participantSlots The number of participant slots available.
      * @param campCommSlots The number of camp committee slots available.
      * @param staffInCharge The ID of the staff member in charge.
      * @param facultyOpenTo The faculty to which the camp is open.
      * @param visibility The visibility status of the camp.
      */     public CampInformation(int id, String name, String location,String description, Date startDate, Date endDate, Date closingDate, int participantSlots,int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility){
        // validation for campCommittee Slots is to be done inside the ui portion
        this.campId = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closingDate = closingDate;
        this.closingDate = closingDate;
        this.participantSlots = participantSlots;
        this.campCommSlots = campCommSlots;
        this.staffInCharge = staffInCharge;
        this.facultyOpenTo = facultyOpenTo;
        this.visibility = visibility;
    }

    // write get and set methods for each
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getClosingDate(){
        return closingDate;
    }
    public int getParticipantSlots(){
        return participantSlots;
    }
    public int getCampCommSlots(){
        return campCommSlots;
    }

    public int getRemainingSlots() {//TODO FILLER METHOD
    	return 0; //NOT COMPLETED
    }

    public int getStaffInCharge(){
        return staffInCharge;
    }
     public Faculty getFacultyOpenTo(){
        return facultyOpenTo;
    }
     void setName(String name){
        this.name = name;
    }
     void setLocation(String location){
        this.location = location;
    }
     void setDescription(String description){
        this.description = description;
    }

     void setClosingDate(Date closingDate){
        this.closingDate = closingDate;
    }
     void setParticipantSlots(int participantSlots){
        this.participantSlots = participantSlots;
    }
     void setCampCommSlots(int campCommSlots){
        this.campCommSlots = campCommSlots;
    }
     void setStaffInCharge(int staffInCharge){
        this.staffInCharge = staffInCharge;
    }
     void setFacultyOpenTo(Faculty facultyOpenTo){
        this.facultyOpenTo = facultyOpenTo;
    }
    public int getCampId(){
        return campId;
    }
    void setCampId(int campId){
        this.campId = campId;
    }
 /**
     * @return the visibility
     */
    public boolean isVisible() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}
