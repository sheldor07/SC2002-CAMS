/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;


import entity.Faculty;
import entity.Student;
import entity.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * This class represents a specific database for managing Student records in an Excel file.
 * It extends the UserDatabase class and provides methods for querying and manipulating Student data.
 *
 * @author weiya
 */
public class StudentDatabase extends UserDatabase {

    public final String COLUMN_POINTS = "Points";
    /**
     * Constructor for the StudentDatabase class.
     *
     * @param filePath The path to the Excel file used as the Student database.
     */
    public StudentDatabase(String filePath){
        super(filePath);
    }
    /**
     * Retrieves a list of Student objects from the database.
     *
     * @return An ArrayList containing the list of Student objects retrieved from the database.
     */

    @Override
    public ArrayList getList() {

        //Workbook wb = new XSSFWorkbook();
        creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Student> students = new ArrayList();
        while(rowIterator.hasNext()) {
            row = rowIterator.next();

            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String name, email, password;
            Faculty faculty;
            int id, points;

            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                id = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                name = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                email = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                faculty = Faculty.valueOf(cell.getStringCellValue());
                cell = cellIterator.next();
                password = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                points = (int) cell.getNumericCellValue();
                students.add(new Student(id, name,email,password,faculty, points));

            }
        }
        return students;
    }
    /**
     * Retrieves a Student object by their student ID from the database.
     *
     * @param studentId The ID of the student to be retrieved.
     * @return The retrieved Student object, or null if not found.
     */

    public Student getStudentById(int studentId) {

        //Workbook wb = new XSSFWorkbook();
        creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Student> students = new ArrayList();
        while(rowIterator.hasNext()) {
            row = rowIterator.next();

            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String name, email, password;
            Faculty faculty;
            int id, points;

            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                id = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                name = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                email = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                faculty = Faculty.valueOf(cell.getStringCellValue());
                cell = cellIterator.next();
                password = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                points = (int) cell.getNumericCellValue();
                if(studentId == id)
                    return new Student(id, name,email,password,faculty,points);

            }
        }
        return null;
    }
    /**
     * Changes the password for a User (Student) in the database.
     *
     * @param user The User (Student) whose password needs to be changed.
     * @param newPassword The new password to set for the User.
     * @return true if the password is successfully changed, false otherwise.
     */
    @Override
    public boolean changePassword(User user, String newPassword)
    {
        return super.changePassword(user, newPassword);
    }


}
