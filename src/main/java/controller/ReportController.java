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

public class ReportController{

    private final StudentController studentController;
    public ReportController() {
        studentController = new StudentController();
    }

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
