/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StaffDatabase;
import database.StudentDatabase;
import entity.Staff;
import entity.Student;
import entity.User;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author weiya
 *//**
 * Controller class for managing user authentication and related operations.
 * It includes functionalities for user login, logout, and changing passwords.
 */
public class UserController {

    User user;
    StudentDatabase studentDatabase;
    StaffDatabase staffDatabase;
    /**
     * Handles the login process for users, either students or staff.
     * It authenticates users based on their credentials and differentiates between students and staff.
     *
     * @return The authenticated User object if successful, null otherwise.
     */
    public User login() {


        staffDatabase = new StaffDatabase("staff_list");
        studentDatabase = new StudentDatabase("student_list");


        ArrayList<Student> students = studentDatabase.getList();

        ArrayList<Staff> staffs = staffDatabase.getList();


        Scanner sc = new Scanner(System.in);

        String userID, password;
        boolean successful = false;

        while (!successful) {
            int wrongPassword = 0;
            System.out.print("Enter your UserID: ");
            userID = sc.nextLine();
            System.out.print("Enter your Password: ");
            password = sc.nextLine();


            for (Student student : students) {
                String email = student.getEmail();
                String regex = "(@e.ntu.edu.sg;)$";
                String processedEmail = email.replaceAll(regex, "");
                if (processedEmail.equals(userID)) {
                    wrongPassword = 1;
                    if (student.getPassword().equals(password)) {
                        System.out.println("Login successful!");

                        user = checkIfStudentIsCampCommittee(student);

                        if (user.getPassword().equals("password")) {
                            System.out.println("You are required to change your default password: ");
                            return changePassword();
                        }
                        return user;
                    }

                }

            }

            for (Staff staff : staffs) {
                String email = staff.getEmail();
                String regex = "(@NTU.EDU.SG)$";
                String processedEmail1 = email.replaceAll(regex, "");

                String email2 = staff.getEmail();
                String regex2 = "(@ntu.edu.sg)$";
                String processedEmail2 = email.replaceAll(regex2, "");

                if ((processedEmail1.equals(userID) || processedEmail2.equals(userID))) {
                    wrongPassword = 1;
                    if (staff.getPassword().equals(password)) {
                        user = staff;
                        System.out.println("Login successful!");
                        if (user.getPassword().equals("password")) {
                            System.out.println("You are required to change your default password: ");
                            return changePassword();

                        }
                        return user;
                    }
                }
            }

            if (wrongPassword == 1)
                System.out.println("Wrong Password. Please try again.");
            else
                System.out.println("Login unsuccessful, try again");


        }
        return null;
    }

    /**
     * Checks if a student is also a member of the camp committee.
     *
     * @param student The Student object to be checked.
     * @return A User object, either as a Student or as a CampCommittee member.
     */
    public User checkIfStudentIsCampCommittee(Student student) {

        //checking campparticipant database to see if user is campcommitee

        iCampCommitteeChecker campCommitteeChecker = new CampCommitteeChecker();

        return campCommitteeChecker.studentChecker(student);

    }
    /**
     * Logs out the current user.
     *
     * @return null, indicating no user is currently logged in.
     */

    public User logout() {
        System.out.println("Logged out.");
        return null;

    }
    /**
     * Allows the user to change their password.
     * Ensures the new password is confirmed before updating it in the database.
     *
     * @return The User object with updated password, null if the password change process is not completed.
     */

    public User changePassword() {

        //make it strong password?
        Scanner sc = new Scanner(System.in);
        boolean passwordChanged = false;
        while (!passwordChanged) {
            System.out.print("Enter your New Password: ");
            String newPassword = sc.nextLine();
            System.out.print("Confirm your New Password: ");
            String confirmNewPassword = sc.nextLine();

            if (newPassword.equals(confirmNewPassword)) {

                boolean result = false;

                if (user instanceof Student)
                    result = studentDatabase.changePassword(user, confirmNewPassword);
                else if (user instanceof Staff)
                    result = staffDatabase.changePassword(user, confirmNewPassword);

                if (result) {
                    System.out.println("Password Changed succesfully.");
                    System.out.println("Please re-login to validate again.");
                    return logout();
                }

//            break;
            } else {
                System.out.println("The password does not match.");
            }
        }
        return null;
    }

}
