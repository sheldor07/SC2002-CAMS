/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Student;

/**
 *
 * @author weiya
 */
public interface iStudentController {

    boolean addStudentPoint(int studentId);

    Student getStudentById(int id);

    String getStudentNameById(int id);

    int getStudentPoints(int studentId);
    
}
