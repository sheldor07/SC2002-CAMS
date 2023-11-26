/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StaffDatabase;
import database.StudentDatabase;
import entity.Camp;
import entity.Student;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public class StudentController {
          StudentDatabase studentDatabase;
    
    public StudentController(){
        studentDatabase = new StudentDatabase("student_list");
    }
    public String getStudentNameById(int id) {
        return studentDatabase.getStudentById(id).getName();
    }
     public Student getStudentById(int id){
            return studentDatabase.getStudentById(id);
        }
     
     public boolean addStudentPoint(int studentId){
            Student student =  studentDatabase.getStudentById(studentId);
            student.addPoints();
            return studentDatabase.editRow(studentId, studentDatabase.COLUMN_POINTS, student.getPoints());
        }

}
