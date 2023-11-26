package UI.Portal;

import controller.UserController;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;

public class RoleHandlerFactory {
    private static boolean checkIfStudentIsCampCommittee(Student student) {
        UserController userController = new UserController();
        User user = userController.checkIfStudentIsCampCommittee(student);
        return user instanceof CampCommittee;
    }
    public static RoleHandler getHandler(User user, UserController userC) {
        if (user instanceof Student) {
            return new StudentPageHandler(user, userC);
        } else if (user instanceof Staff) {
            return new StaffPageHandler(user, userC);
        } else if (checkIfStudentIsCampCommittee((Student) user)){
            return new CampCommiteePageHandler(user, userC);
        }
        return null;
    }
}