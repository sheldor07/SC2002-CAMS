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
/**
 * Interface defining the contract for classes that check if a student is part of a camp committee.
 */
public interface iCampCommitteeChecker {
    /**
     * Checks if the given student is a member of the camp committee.
     * The implementation should determine whether the student is a committee member and
     * potentially return an updated Student instance or a different subclass of Student.
     *
     * @param student The Student instance to be checked.
     * @return A Student instance, potentially updated or of a specific subclass, based on the student's committee membership status.
     */
    Student studentChecker(Student student);
    
}
