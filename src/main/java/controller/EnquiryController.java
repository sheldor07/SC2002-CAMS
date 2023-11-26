package controller;

import database.EnquiryDatabase;
import java.util.ArrayList;

import entity.Enquiry;
/**
 * Controller class for managing enquiries.
 * Handles operations such as retrieving, submitting, deleting, and editing enquiries.
 */
public class EnquiryController {

    public EnquiryDatabase enquiryDatabase;
    /**
     * Constructor to initialize the EnquiryController with an enquiry database.
     */
    public EnquiryController(){
        enquiryDatabase = new EnquiryDatabase("enquiry_list");
    }
    /**
     * Refreshes the current state of the enquiry database.
     */
    public void refreshCampDatabase(){
        enquiryDatabase = new EnquiryDatabase("enquiry_list");
    }

    /**
     * Retrieves unanswered enquiries for a specific student.
     *
     * @param studentId The ID of the student.
     * @return An ArrayList of Enquiry objects.
     */
    public ArrayList<Enquiry> getUnansweredEnquiriesByStudentId(int studentId) {
        return enquiryDatabase.getUnansweredEnquiryByStudentId(studentId);

    }
    /**
     * Retrieves unanswered enquiries for a specific staff member.
     *
     * @param staffId The ID of the staff member.
     * @return An ArrayList of Enquiry objects.
     */
    public ArrayList<Enquiry> getUnansweredEnquiriesByStaffId(int staffId) {
        return enquiryDatabase.getUnansweredEnquiryByStaffId(staffId);

    }
    /**
     * Retrieves answered enquiries for a specific student.
     *
     * @param studentId The ID of the student.
     * @return An ArrayList of Enquiry objects.
     */


    public ArrayList<Enquiry> getAnsweredEnquiryByStudentId(int studentId) {
        return enquiryDatabase.getAnsweredEnquiryByStudentId(studentId);

    }
    /**
     * Retrieves enquiries related to a specific camp.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of Enquiry objects.
     */

    public ArrayList<Enquiry> getEnquiriesByCampId(int campId) {
        return enquiryDatabase.getEnquiriesByCampId(campId);
    }

    /**
     * Retrieves all enquiries.
     *
     * @return An ArrayList of Enquiry objects.
     */
    public ArrayList<Enquiry> getAllEnquiries()
    {
        return enquiryDatabase.getList();
    }
    /**
     * Submits a new enquiry to the database.
     *
     * @param enquiry The Enquiry object to be added.
     * @return true if the submission was successful, false otherwise.
     */

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
    /**
     * Deletes an enquiry from the database.
     *
     * @param enquiry The Enquiry object to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */

    public boolean delete(Enquiry enquiry) {
        return enquiryDatabase.delete(enquiry.getId());

    }

    /**
     * Edits the details of an existing enquiry.
     *
     * @param enquiry The Enquiry object with updated details.
     * @return true if the edit was successful, false otherwise.
     */
    public boolean editEnquiryDetails(Enquiry enquiry) {
        return enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_DETAILS, enquiry.getDetails());
    }
    /**
     * Edits the answer of an existing enquiry.
     *
     * @param enquiry The Enquiry object with updated answer.
     * @return true if the edit was successful, false otherwise.
     */


    public boolean editEnquiryAnswer(Enquiry enquiry) {


        enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_ANSWER, enquiry.getAnswer());
        return enquiryDatabase.editRow(enquiry.getId(), enquiryDatabase.COLUMN_ANSWEREDBY, enquiry.getAnsweredBy());

    }

}
