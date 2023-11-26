/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Student;
import entity.User;

/**
 *
 * @author weiya
 */
public interface iUserInterface {

    public User changePassword();

    public User checkIfStudentIsCampCommittee(Student student);

    public User login();

    public User logout();
    
}
