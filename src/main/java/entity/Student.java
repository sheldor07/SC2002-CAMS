package entity;
/**
 * The `Student` class represents students in the system who can participate in various activities and events.
 * Students are users with specific attributes and points that can be earned.
 *
 * Students inherit properties and methods from the `User` class.
 * They have attributes including an ID, name, email, password, faculty affiliation, and points.
 * Points can be earned and added to a student's account.
 *
 * This class is used to create and manage student users within the system.
 * Students can participate in camps, committees, and other activities, and their points can be tracked.
 *
 * @author sheldor07
 */
public class Student extends User{
    /**
     * Constructs a new `Student` object with the specified attributes and initializes points to zero.
     *
     * @param id The unique identifier of the student.
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param password The password of the student.
     * @param faculty The faculty to which the student belongs.
     */
     public Student(int id, String name, String email, String password, Faculty faculty) {
        super(id, name, email, password, faculty);
        this.points = 0;
        
    }

    /**
     * Constructs a new `Student` object with the specified attributes, including points.
     *
     * @param id The unique identifier of the student.
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param password The password of the student.
     * @param faculty The faculty to which the student belongs.
     * @param points The number of points associated with the student.
     */
     public Student(int id, String name, String email, String password, Faculty faculty, int points) {
        super(id, name, email, password, faculty);
        this.points = points;
        
    }
     
     
    
    int points;
    /**
     * Retrieves the number of points associated with the student.
     *
     * @return The number of points.
     */
    public int getPoints(){
        return this.points;
    }
    /**
     * Adds one point to the student's point balance.
     */

    public void addPoints(){
        this.points +=1;
    }

}
