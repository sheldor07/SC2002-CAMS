/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.CampCommittee;
import entity.CampParticipant;
import entity.Student;

import java.util.ArrayList;

/**
 * Controller class responsible for checking if a student is part of the camp committee.
 * Implements the iCampCommitteeChecker interface to provide specific functionality
 * for checking committee membership status of students in a camp.
 * This class collaborates with the CampParticipantController to access participant data.
 *
 * @author weiya
 */
public class CampCommitteeChecker implements iCampCommitteeChecker {

    /**
     * Controller for managing camp participants.
     */
    private CampParticipantController campParticipantController;

    /**
     * Constructor to initialize CampCommitteeChecker.
     * Instantiates a new CampParticipantController to manage participant data.
     */
    public CampCommitteeChecker() {
        campParticipantController = new CampParticipantController();
    }

    /**
     * Checks if the provided student is a member of the camp committee.
     * If the student is found in the list of camp participants with a committee status,
     * a new CampCommittee object is returned. Otherwise, the original student object is returned.
     *
     * @param student The student to be checked for committee membership.
     * @return A CampCommittee object if the student is a committee member, else the original Student object.
     */
    @Override
    public Student studentChecker(Student student) {
        ArrayList<CampParticipant> campParticipants = campParticipantController.getAllParticipants();

        for (CampParticipant c : campParticipants) {
            if (c.getStudentId() == student.getId() && c.isCampCommittee()) {
                return new CampCommittee(student.getId(), student.getName(), student.getEmail(), student.getPassword(), student.getFaculty());
            }
        }

        return student;
    }
}
