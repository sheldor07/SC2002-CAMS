/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import UI.ParticipantFilter;
import entity.Camp;
import entity.CampParticipant;
import entity.Enquiry;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public interface iReportController {

    void generateEnquiryReport(Camp camp, ArrayList<Enquiry> enquiries);

    void generatePerformanceReport(Camp camp, ArrayList<CampParticipant> campCommittee);

    void generateStudentReport(ParticipantFilter filter, Camp camp, ArrayList<CampParticipant> campParticipants);
    
}
