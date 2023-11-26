/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 * The `CampCommittee` class represents a member of the camp committee who is also a student.
 * It extends the `Student` class and inherits its properties and behaviors.
 *
 * @author weiya
 */
public class CampCommittee extends Student {

    /**
     * Creates a new `CampCommittee` object with the specified attributes.
     *
     * @param id The unique identifier of the camp committee member.
     * @param name The name of the camp committee member.
     * @param email The email address of the camp committee member.
     * @param password The password of the camp committee member.
     * @param faculty The faculty to which the camp committee member belongs.
     */
    public CampCommittee(int id, String name, String email, String password, Faculty faculty) {
        super(id, name, email, password, faculty);
    }
}
