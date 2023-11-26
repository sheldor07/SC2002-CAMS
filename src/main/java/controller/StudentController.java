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
 *//**
 * Controller class for managing student-related operations.
 * It includes functionality to access and update student information in a student database.
 */
public class StudentController {
          StudentDatabase studentDatabase;
    /**
     * Constructor to initialize the StudentController with a student database.
     */
    public StudentController(){
        studentDatabase = new StudentDatabase("student_list");
    }
    /**
     * Retrieves the name of a student by their unique ID.
     *
     * @param id The unique ID of the student.
     * @return The name of the student if found, null otherwise.
     */
    public String getStudentNameById(int id) {
        return studentDatabase.getStudentById(id).getName();
    }
    /**
     * Retrieves the name of a student by their unique ID.
     *
     * @param id The unique ID of the student.
     * @return The name of the student if found, null otherwise.
     */
     public Student getStudentById(int id){
            return studentDatabase.getStudentById(id);
        }
    /**
     * Adds a point to a student's existing points and updates it in the database.
     *
     * @param studentId The ID of the student whose points are to be incremented.
     * @return true if the update is successful, false otherwise.
     */
     public boolean addStudentPoint(int studentId){
            Student student =  studentDatabase.getStudentById(studentId);
            student.addPoints();
            return studentDatabase.editRow(studentId, studentDatabase.COLUMN_POINTS, student.getPoints());
        }
    /**
     * Retrieves the total points of a student by their ID.
     *
     * @param studentId The ID of the student.
     * @return The total points of the student.
     */
        public int getStudentPoints(int studentId){
            return studentDatabase.getStudentById(studentId).getPoints();
        }
}
