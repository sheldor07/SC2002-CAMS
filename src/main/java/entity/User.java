package entity;
/**
 * The `User` class represents a user entity in the system, including their identity, login credentials,
 * faculty affiliation, email address, and name. This class serves as a base class for various user types,
 * such as students and staff.
 *
 * Users can log in, change their email address and password, and access their profile information.
 * The `User` class implements the `Identity` interface to provide a unique identifier for users.
 *
 * @author weiya
 */

public class User implements Identity{

    private int userId;
    private String password = "password";
    private Faculty faculty;
    private String email;
    private String name;
    /**
     * Constructs a new `User` object with the specified attributes.
     *
     * @param id The unique identifier of the user.
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param faculty The faculty to which the user is affiliated.
     */
    public User(int id, String name, String email, String password, Faculty faculty){

        this.email = email;
        this.name = name;
        this.userId = id;
        this.password = password;
        this.faculty = faculty;
    }
    /**
     * Retrieves the unique identifier (ID) of the user.
     *
     * @return The user's ID.
     */
    @Override
    public int getId(){
        return this.userId;
    }
    /**
     * Retrieves the faculty to which the user is affiliated.
     *
     * @return The faculty affiliation of the user.
     */
    public Faculty getFaculty(){
        return this.faculty;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail(){
        return this.email;
    }    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Changes the user's email address to a new one.
     *
     * @param newEmail The new email address to set.
     * @return `true` if the email is successfully changed, `false` if it remains the same.
     */
    public boolean changeEmail(String newEmail){
        // checks if it's the same email and updates otherwise
        if(this.email.equals(newEmail)){
            return false;
        }
        this.email = newEmail;
        return true;

    }
    /**
     * Changes the user's password to a new one.
     *
     * @param newPassword The new password to set.
     * @return `true` if the password is successfully changed, `false` if it remains the same.
     */
    public boolean changePassword(String newPassword){
        // checks if it's the same password and updates otherwise
        if(checkPassword(newPassword)){
            return false;
        }
        this.password = newPassword;
        return true;

    }  /**
     * Checks if the provided password matches the user's current password.
     *
     * @param password The password to check.
     * @return `true` if the provided password matches the current password, `false` otherwise.
     */
    public boolean checkPassword(String password){
        /* checks if it's the same password*/
        return this.getPassword().equals(password);
    }
    /**
     * Retrieves the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }



}
