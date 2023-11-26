package controller;

import UI.ParticipantFilter;
import entity.Camp;
import entity.CampParticipant;
import entity.Enquiry;
import entity.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Controller class for generating various reports related to camps.
 * It includes functionalities to generate reports for students, performance, and enquiries.
 */
public class ReportController{

    private final StudentController studentController;
    /**
     * Constructor to initialize the ReportController with a student controller.
     */
    public ReportController() {
        studentController = new StudentController();
    }
    /**
     * Generates a report of students participating in a camp based on a specific filter.
     * The report includes details about the camp and the participants as per the specified filter.
     *
     * @param filter The filter to apply to the participants (e.g., Attendee, Committee).
     * @param camp The camp for which the report is being generated.
     * @param campParticipants The list of participants in the camp.
     */
    public void generateStudentReport(ParticipantFilter filter, Camp camp,ArrayList<CampParticipant> campParticipants ) {
//        Camp camp = campController.getCampById(campId);
        System.out.println("Generating report for camp: " + camp.getName());
        String folderName = "reports";
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String fileName = folderName + File.separator + camp.getName().replaceAll("\\s+", "_") + "_Report.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("============================================\n");
            writer.write("           CAMP REPORT DETAILS              \n");
            writer.write("============================================\n");
            writer.write("Camp Name: " + camp.getName() + "\n");
            writer.write("Camp Location: " + camp.getLocation() + "\n");
            writer.write("Camp Description: " + camp.getDescription() + "\n");
            writer.write("Camp Start Date: " + camp.getStartDate() + "\n");
            writer.write("Camp End Date: " + camp.getEndDate() + "\n");
            writer.write("\n--------------------------------------------\n");
            writer.write("              PARTICIPANTS                  \n");
            writer.write("--------------------------------------------\n");
            for (CampParticipant campParticipant : campParticipants) {
                if (shouldIncludeParticipant(campParticipant, filter)) {
                    Student student = studentController.getStudentById(campParticipant.getStudentId());
                    writer.write("Student Name: " + student.getName() + "\n");
                    writer.write("Student Role: " + (campParticipant.isCampCommittee() ? "Camp Committee" : "Attendee") + "\n");
                    writer.write("--------------------------------------------\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Determines whether a camp participant should be included in the report based on the provided filter.
     *
     * @param participant The camp participant to check.
     * @param filter The filter criteria (e.g., Attendee, Committee).
     * @return true if the participant should be included, false otherwise.
     */

    private boolean shouldIncludeParticipant(CampParticipant participant, ParticipantFilter filter) {
        switch (filter) {
            case ATTENDEE:
                return !participant.isCampCommittee();
            case CAMP_COMMITTEE:
                return participant.isCampCommittee();
            default:
                return true;
        }
    }
    /**
     * Generates a performance report for camp committee members.
     * The report includes details about the camp and performance metrics of the committee members.
     *
     * @param camp The camp for which the performance report is being generated.
     * @param campCommittee The list of camp committee members.
     */
        public void generatePerformanceReport(Camp camp, ArrayList<CampParticipant> campCommittee) {
        System.out.println("Generating performance report for camp: " + camp.getName());
        String folderName = "reports/performance";
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir();


        }
        String fileName = folderName + File.separator + camp.getName().replaceAll("\\s+", "_") + "_Performance_Report.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("============================================\n");
                writer.write("           CAMP REPORT DETAILS              \n");
                writer.write("============================================\n");
                writer.write("Camp Name: " + camp.getName() + "\n");
                writer.write("Camp Location: " + camp.getLocation() + "\n");
                writer.write("Camp Description: " + camp.getDescription() + "\n");
                writer.write("Camp Start Date: " + camp.getStartDate() + "\n");
                writer.write("Camp End Date: " + camp.getEndDate() + "\n");
                writer.write("\n--------------------------------------------\n");
                writer.write("              CAMP COMMITTEE                  \n");
                writer.write("--------------------------------------------\n");
                for (CampParticipant campParticipant : campCommittee) {

                        Student student = studentController.getStudentById(campParticipant.getStudentId());
                        writer.write("Student Name: " + student.getName() + "\n");
                        writer.write("Student Points: " + student.getPoints() + "\n");
                        writer.write("--------------------------------------------\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    /**
     * Generates a report of enquiries related to a camp.
     * The report includes details about the camp and all enquiries made, including unanswered ones.
     *
     * @param camp The camp for which the enquiry report is being generated.
     * @param enquiries The list of enquiries related to the camp.
     */
    public void generateEnquiryReport(Camp camp, ArrayList<Enquiry> enquiries) {
        System.out.println("Generating enquiry report for camp: " + camp.getName());
        String folderName = "reports/enquiry";
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String fileName = folderName + File.separator + camp.getName().replaceAll("\\s+", "_") + "_Enquiry_Report.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("============================================\n");
            writer.write("           ENQUIRY REPORT FOR CAMP          \n");
            writer.write("============================================\n");
            writer.write("Camp Name: " + camp.getName() + "\n");
            writer.write("Camp Location: " + camp.getLocation() + "\n");
            writer.write("Camp Description: " + camp.getDescription() + "\n");
            writer.write("Camp Start Date: " + camp.getStartDate() + "\n");
            writer.write("Camp End Date: " + camp.getEndDate() + "\n");
            writer.write("\n--------------------------------------------\n");
            writer.write("                   ENQUIRIES                \n");
            writer.write("--------------------------------------------\n");
            for (Enquiry enquiry : enquiries) {
                writer.write("Enquiry ID: " + enquiry.getId() + "\n");
                writer.write("Enquiry Details: " + enquiry.getDetails() + "\n");
                writer.write("Asked By (User ID): " + enquiry.getAskedBy() + "\n");
                if (enquiry.getAnswer() != null && !enquiry.getAnswer().isEmpty()) {
                    writer.write("Answer: " + enquiry.getAnswer() + "\n");
                    writer.write("Answered By (Staff ID): " + enquiry.getAnsweredBy() + "\n");
                } else {
                    writer.write("Answer: Pending\n");
                }
                writer.write("--------------------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
