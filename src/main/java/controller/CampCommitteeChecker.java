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
 *
 * @author weiya
 */
public class CampCommitteeChecker implements iCampCommitteeChecker {
    
    CampParticipantController campParticipantController;
    
    public CampCommitteeChecker(){
         campParticipantController = new CampParticipantController();
    }
    
    @Override
    public Student studentChecker(Student student){
         ArrayList<CampParticipant> campParticipants = campParticipantController.getAllParticipants();
         
         for(CampParticipant c : campParticipants)
         {    
             if(c.getStudentId() == student.getId() && c.isCampCommittee()){                 
                 return new CampCommittee(student.getId(),student.getName(),student.getEmail(),student.getPassword(),student.getFaculty());
                 
             }
         }
             
        
        return student;
    }
}
