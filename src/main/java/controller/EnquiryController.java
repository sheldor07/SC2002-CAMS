package controller;

import database.EnquiryDatabase;
import java.util.ArrayList;

import entity.Enquiry;

public class EnquiryController {
    
    public EnquiryDatabase enquiryDatabase;
    
    public EnquiryController(){
        enquiryDatabase = new EnquiryDatabase("enquiry_list");
    }
    
    public void refreshCampDatabase(){
        enquiryDatabase = new EnquiryDatabase("enquiry_list");
    }

	public ArrayList<Enquiry> getUnansweredEnquiriesByStudentId(int studentId) {
        return enquiryDatabase.getUnansweredEnquiryByStudentId(studentId);

	}
        
        public ArrayList<Enquiry> getUnansweredEnquiriesByStaffId(int staffId) {
        return enquiryDatabase.getUnansweredEnquiryByStaffId(staffId);

	}
       
        public ArrayList<Enquiry> getAnsweredEnquiryByStudentId(int studentId) {
        return enquiryDatabase.getAnsweredEnquiryByStudentId(studentId);

	}

                
        
        public ArrayList<Enquiry> getAllEnquiries()
        {
            return enquiryDatabase.getList();
        }
        
        public boolean submitEnquiry(Enquiry enquiry) {
                ArrayList<Enquiry> enquiries = enquiryDatabase.getList();
            int max = 0;
            for(Enquiry e : enquiries){
                   if(e.getId() > max)
                       max = e.getId();
            }
            enquiry.setId(max+1);
            boolean result = enquiryDatabase.add(enquiry);
            refreshCampDatabase();
            
            return result;
	}

	public boolean delete(Enquiry enquiry) {
            return enquiryDatabase.delete(enquiry.getId());

	}

	public boolean editEnquiryDetails(Enquiry enquiry) {
            return enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_DETAILS, enquiry.getDetails());
	}
        
        public boolean editEnquiryAnswer(Enquiry enquiry) {
            
            
            enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_ANSWER, enquiry.getAnswer());
	   return enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_ANSWEREDBY, enquiry.getAnsweredBy());

        }

}
