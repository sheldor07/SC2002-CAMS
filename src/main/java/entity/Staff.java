package entity;


/**
 * The `Staff` class represents staff members in the system who can be associated with various activities and tasks.
 * Staff members are users with specific roles and responsibilities.
 *
 * Staff members inherit properties and methods from the `User` class.
 * They have attributes including an ID, name, email, password, and faculty affiliation.
 *
 * This class is used to create and manage staff members within the system.
 * Staff members can be associated with camps, committees, and other organizational aspects.
 *
 * @author sheldor07
 */
public class Staff extends User{
    /**
     * Constructs a new `Staff` object with the specified attributes.
     *
     * @param id The unique identifier of the staff member.
     * @param name The name of the staff member.
     * @param email The email address of the staff member.
     * @param password The password of the staff member.
     * @param faculty The faculty to which the staff member belongs.
     */
      public Staff(int id,String name, String email, String password, Faculty faculty) {
        super(id,name, email, password, faculty);

    }

}
