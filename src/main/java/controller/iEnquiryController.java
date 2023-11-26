/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Enquiry;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public interface iEnquiryController {

    boolean delete(Enquiry enquiry);

    boolean editEnquiryAnswer(Enquiry enquiry);

    boolean editEnquiryDetails(Enquiry enquiry);

    ArrayList<Enquiry> getAllEnquiries();

    ArrayList<Enquiry> getAnsweredEnquiryByStudentId(int studentId);

    ArrayList<Enquiry> getEnquiriesByCampId(int campId);

    ArrayList<Enquiry> getUnansweredEnquiriesByStaffId(int staffId);

    ArrayList<Enquiry> getUnansweredEnquiriesByStudentId(int studentId);

    void refreshCampDatabase();

    boolean submitEnquiry(Enquiry enquiry);
    
}
