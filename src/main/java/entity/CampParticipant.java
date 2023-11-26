/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 * The `CampParticipant` class represents a participant in a camp, including their
 * unique identifier, camp ID, student ID, staff in charge ID, and camp committee status.
 * This class is used to store and retrieve information about camp participants.
 *
 * @author weiya
 */
public class CampParticipant {

    private int id;
    private int campId;
    private int studentId;
    private int staffInChargeId;
    private boolean isCampCommittee; // 0 is no, 1 is yes.

    /**
     * Constructs a new `CampParticipant` object with the specified attributes.
     *
     * @param id The unique identifier of the camp participant.
     * @param campId The ID of the camp in which the participant is registered.
     * @param studentId The ID of the student participating in the camp.
     * @param staffInChargeId The ID of the staff member in charge of the camp.
     * @param isCampCommittee A boolean indicating if the participant is part of the camp committee.
     */
    public CampParticipant(int id, int campId, int studentId, int staffInChargeId, boolean isCampCommittee){
        this.id = id;
        this.campId = campId;
        this.staffInChargeId = staffInChargeId;
        this.studentId = studentId;
        this.isCampCommittee = isCampCommittee;
    }

    /**
     * Constructs a new `CampParticipant` object without a unique identifier.
     *
     * @param campId The ID of the camp in which the participant is registered.
     * @param studentId The ID of the student participating in the camp.
     * @param staffInChargeId The ID of the staff member in charge of the camp.
     * @param isCampCommittee A boolean indicating if the participant is part of the camp committee.
     */
    public CampParticipant(int campId, int studentId, int staffInChargeId, boolean isCampCommittee){
        this.campId = campId;
        this.staffInChargeId = staffInChargeId;
        this.studentId = studentId;
        this.isCampCommittee = isCampCommittee;
    }

    /**
     * Gets the unique identifier of the camp participant.
     *
     * @return The unique identifier of the camp participant.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the camp participant.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the camp in which the participant is registered.
     *
     * @return The camp ID.
     */
    public int getCampId() {
        return campId;
    }

    /**
     * Sets the ID of the camp in which the participant is registered.
     *
     * @param campId The camp ID to set.
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }

    /**
     * Gets the ID of the student participating in the camp.
     *
     * @return The student ID.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student participating in the camp.
     *
     * @param studentId The student ID to set.
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the ID of the staff member in charge of the camp.
     *
     * @return The staff member in charge ID.
     */
    public int getStaffInChargeId() {
        return staffInChargeId;
    }

    /**
     * Sets the ID of the staff member in charge of the camp.
     *
     * @param staffInChargeId The staff member in charge ID to set.
     */
    public void setStaffInChargeId(int staffInChargeId) {
        this.staffInChargeId = staffInChargeId;
    }

    /**
     * Checks if the participant is part of the camp committee.
     *
     * @return `true` if the participant is part of the camp committee, `false` otherwise.
     */
    public boolean isCampCommittee() {
        return isCampCommittee;
    }

    /**
     * Sets the camp committee status of the participant.
     *
     * @param isCampCommittee A boolean indicating if the participant is part of the camp committee.
     */
    public void setCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }
}
