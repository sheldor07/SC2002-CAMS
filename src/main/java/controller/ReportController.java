package controller;

import UI.ParticipantFilter;
import entity.Camp;
import entity.CampParticipant;
import entity.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportController {

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

}
