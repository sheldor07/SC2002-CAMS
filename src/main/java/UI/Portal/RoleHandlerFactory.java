package UI.Portal;

import controller.UserController;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;
/**
 * The `RoleHandlerFactory` class is responsible for creating and returning the appropriate `RoleHandler` based on the type of user logged in.
 * It checks the user's role and instantiates the corresponding handler to manage role-specific interactions.
 *
 * @author yajatgulati
 */
public class RoleHandlerFactory {
    /**
     * Checks if a student is part of the camp committee.
     *
     * @param student The student to check.
     * @return `true` if the student is a camp committee member, `false` otherwise.
     */
    private static boolean checkIfStudentIsCampCommittee(Student student) {
        UserController userController = new UserController();
        User user = userController.checkIfStudentIsCampCommittee(student);
        return user instanceof CampCommittee;
    }
    /**
     * Returns the appropriate `RoleHandler` based on the user's role.
     *
     * @param user The logged-in user.
     * @param userC The user controller.
     * @return The corresponding `RoleHandler` for the user's role.
     */

    public static RoleHandler getHandler(User user, UserController userC) {
        if(user instanceof Staff){
            return new StaffPageHandler(user, userC);
        }
        else if (checkIfStudentIsCampCommittee((Student) user)){
            return new CampCommiteePageHandler(user, userC);
        }
        else{
            return new StudentPageHandler(user, userC);
        } 

    }
}