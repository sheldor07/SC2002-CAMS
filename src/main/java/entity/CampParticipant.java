/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author weiya
 */
public class CampParticipant {
    
    private int id;
    private int campId;
    private int studentId;
    private int staffInChargeId;
    private boolean isCampCommittee; // 0 is no, 1 is yes.
    
    public CampParticipant(int id, int campId, int studentId, int staffInChargeId, boolean isCampCommittee){
        this.id = id;
        this.campId = campId;
        this.staffInChargeId = staffInChargeId;
        this.studentId = studentId;
        this.isCampCommittee = isCampCommittee;
        
    }
    
    //constructor for creating CampParticipant
    public CampParticipant(int campId, int studentId, int staffInChargeId, boolean isCampCommittee){
        this.campId = campId;
        this.staffInChargeId = staffInChargeId;
        this.studentId = studentId;
        this.isCampCommittee = isCampCommittee;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the campId
     */
    public int getCampId() {
        return campId;
    }

    /**
     * @param campId the campId to set
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the staffInChargeId
     */
    public int getStaffInChargeId() {
        return staffInChargeId;
    }

    /**
     * @param staffInChargeId the staffInChargeId to set
     */
    public void setStaffInChargeId(int staffInChargeId) {
        this.staffInChargeId = staffInChargeId;
    }
    
        /**
     * @return the isCampCommittee
     */
    public boolean isCampCommittee() {
        return isCampCommittee;
    }

    /**
     * @param isCampCommittee the isCampCommittee to set
     */
    public void setCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }
    
}
