package entity;

import java.util.Collection;
import java.util.Date;
/**
 * Represents a camp with various attributes and operations.
 */
public class Camp implements Identity {

    /**
     * @return the information
     */
    public CampInformation getInformation() {
        return information;
    }
    private final CampInformation information;

    /**
     * Constructs a new camp with the provided information.
     *
     * @param name           The name of the camp.
     * @param location       The location of the camp.
     * @param description    The description of the camp.
     * @param startDate      The start date of the camp.
     * @param endDate        The end date of the camp.
     * @param closingDate    The closing date of the camp.
     * @param participantSlots The number of participant slots available.
     * @param campCommSlots  The number of camp committee slots available.
     * @param staffInCharge  The staff member in charge of the camp.
     * @param facultyOpenTo  The faculty to which the camp is open.
     * @param visibility     The visibility status of the camp.
     */
    public Camp(String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility) {
        // validation for campCommittee Slots is to be done inside the ui portion
        this.information = new CampInformation(name, location, description, startDate, endDate , closingDate, participantSlots, campCommSlots, staffInCharge, facultyOpenTo, visibility);

    }
    
    public Camp(int id, String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, int staffInCharge, Faculty facultyOpenTo, boolean visibility) {
        // validation for campCommittee Slots is to be done inside the ui portion
        this.information = new CampInformation(id, name, location, description, startDate, endDate , closingDate, participantSlots, campCommSlots, staffInCharge, facultyOpenTo, visibility);

    }
    /**
     * Sets the ID of the camp.
     *
     * @param id The ID to set for the camp.
     */
    public void setID(int id) {
        this.getInformation().setCampId(id);
    }

    /**
     * Checks if the camp is visible.
     *
     * @return {@code true} if the camp is visible, {@code false} otherwise.
     */
    public boolean isVisible() {
        return information.isVisible();
    }

    /**
     * Makes the camp visible.
     */
    public void show() {
        information.setVisibility(true);
    }

    /**
     * Hides the camp from visibility.
     */
    public void hide() {
        information.setVisibility(false);
    }

    /**
     * Sets the camp to be open to NTU faculty.
     */
    public void openToNTU() {
        getInformation().setFacultyOpenTo(Faculty.NTU);
    }

    /**
     * Sets the camp to be open to a specific faculty.
     *
     * @param faculty The faculty to open the camp to.
     */
    public void openToFaculty(Faculty faculty) {
        getInformation().setFacultyOpenTo(faculty);
    }

    /**
     * Retrieves the name of the camp.
     *
     * @return The name of the camp.
     */
    public String getName() {
        return getInformation().getName();
    }

    /**
     * Sets the name of the camp.
     *
     * @param name The name to set for the camp.
     */
    public void setName(String name) {
        getInformation().setName(name);
    }

    /**
     * Retrieves the location of the camp.
     *
     * @return The location of the camp.
     */
    public String getLocation() {
        return getInformation().getLocation();
    }

    /**
     * Sets the location of the camp.
     *
     * @param location The location to set for the camp.
     */
    public void setLocation(String location) {
        getInformation().setLocation(location);
    }

    /**
     * Retrieves the start date of the camp.
     *
     * @return The start date of the camp.
     */
    public Date getStartDate() {
        return getInformation().getStartDate();
    }

    /**
     * Sets the start date of the camp.
     *
     * @param startDate The start date to set for the camp.
     */
    public void setStartDate(Date startDate) {
        getInformation().setStartDate(startDate);
    }

    /**
     * Retrieves the end date of the camp.
     *
     * @return The end date of the camp.
     */
    public Date getEndDate() {
        return getInformation().getEndDate();
    }

    /**
     * Sets the end date of the camp.
     *
     * @param endDate The end date to set for the camp.
     */
    public void setEndDate(Date endDate) {
        getInformation().setEndDate(endDate);
    }

    /**
     * Retrieves the closing date of the camp.
     *
     * @return The closing date of the camp.
     */
    public Date getClosingDate() {
        return getInformation().getClosingDate();
    }

    /**
     * Sets the closing date of the camp.
     *
     * @param closingDate The closing date to set for the camp.
     */
    public void setClosingDate(Date closingDate) {
        getInformation().setClosingDate(closingDate);
    }

    /**
     * Retrieves the number of participant slots available in the camp.
     *
     * @return The number of participant slots.
     */
    public int getParticipantSlots() {
        return getInformation().getParticipantSlots();
    }

    /**
     * Sets the number of participant slots available in the camp.
     *
     * @param participantSlots The number of participant slots to set.
     */
    public void setParticipantSlots(int participantSlots) {
        getInformation().setParticipantSlots(participantSlots);
    }

    /**
     * Retrieves the number of camp committee slots available in the camp.
     *
     * @return The number of camp committee slots.
     */
    public int getCampCommSlots() {
        return getInformation().getCampCommSlots();
    }

    /**
     * Sets the number of camp committee slots available in the camp.
     *
     * @param campCommSlots The number of camp committee slots to set.
     */
    public void setCampCommSlots(int campCommSlots) {
        getInformation().setCampCommSlots(campCommSlots);
    }

    /**
     * Retrieves the staff member in charge of the camp.
     *
     * @return The staff member in charge of the camp.
     */
    public int getStaffInCharge() {
        return getInformation().getStaffInCharge();
    }

    /**
     * Retrieves the description of the camp.
     *
     * @return The description of the camp.
     */
    public String getDescription() {
        return getInformation().getDescription();
    }

    /**
     * Sets the description of the camp.
     *
     * @param description The description to set for the camp.
     */
    public void setDescription(String description) {
        getInformation().setDescription(description);
    }

    /**
     * Retrieves the faculty to which the camp is open.
     *
     * @return The faculty to which the camp is open.
     */
    public Faculty getOpenTo() {
        return getInformation().getFacultyOpenTo();
    }

    /**
     * Sets the faculty to which the camp is open.
     *
     * @param facultyOpenTo The faculty to open the camp to.
     */
    public void setOpenTo(Faculty facultyOpenTo) {
        getInformation().setFacultyOpenTo(facultyOpenTo);
    }

    /**
     * Checks if the camp is open to a specific faculty.
     *
     * @param faculty The faculty to check for openness.
     * @return {@code true} if the camp is open to the specified faculty, {@code false} otherwise.
     */
    public boolean isOpenTo(Faculty faculty) {
        Faculty facultyOpenTo = getInformation().getFacultyOpenTo();
        return facultyOpenTo.equals(faculty);
    }

    /**
     * Retrieves the ID of the camp.
     *
     * @return The ID of the camp.
     */
    @Override
    public int getId() {
        return getInformation().getCampId();
    }

    /**
     * Retrieves the faculty open to in string format.
     *
     * @return The faculty open to as a string.
     */
    public String getFacultyOpenToString() {
        return getInformation().getFacultyOpenTo().toString();
    }
}