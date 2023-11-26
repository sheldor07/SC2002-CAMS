/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Faculty;
import entity.Staff;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
/**
 * This class represents a specific database for managing Staff records in an Excel file.
 * It extends the UserDatabase class and provides methods for querying and manipulating Staff data.
 *
 * @author weiya
 */
public class StaffDatabase extends UserDatabase {

    /**
     * Constructor for the StaffDatabase class.
     *
     * @param filePath The path to the Excel file used as the Staff database.
     */

    public StaffDatabase(String filePath){
        super(filePath);
    }
    /**
     * Retrieves a list of Staff objects from the database.
     *
     * @return An ArrayList containing the list of Staff objects retrieved from the database.
     */
    @Override
    public ArrayList<Staff> getList() {
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Staff> staffs = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String name, email, password;
            Faculty faculty;
            int id;

            while(cellIterator.hasNext()) {

                cell = cellIterator.next();
//        System.out.println(cell.getStringCellValue());

                id = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                name = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                email = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                faculty = Faculty.valueOf(cell.getStringCellValue());
                cell = cellIterator.next();
                password = cell.getStringCellValue().trim();
                staffs.add(new Staff(id,name,email,password,faculty));



            }
        }
        return staffs;
    }
    /**
     * Retrieves a Staff object by their staff ID from the database.
     *
     * @param staffID The ID of the staff member to be retrieved.
     * @return The retrieved Staff object, or null if not found.
     */
    public Staff getListByStaffId(int staffID) {
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Staff> staffs = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String name, email, password;
            Faculty faculty;
            int id;

            while(cellIterator.hasNext()) {

                cell = cellIterator.next();
//        System.out.println(cell.getStringCellValue());

                id = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                name = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                email = cell.getStringCellValue().trim();
                cell = cellIterator.next();
                faculty = Faculty.valueOf(cell.getStringCellValue());
                cell = cellIterator.next();
                password = cell.getStringCellValue().trim();

                if(staffID == id)
                    return new Staff(id,name,email,password,faculty);



            }
        }
        return null;

    }
    /**
     * Changes the password for a User (Staff) in the database.
     *
     * @param user The User (Staff) whose password needs to be changed.
     * @param newPassword The new password to set for the User.
     * @return true if the password is successfully changed, false otherwise.
     */

    @Override
    public boolean changePassword(User user, String newPassword)
    {
        return super.changePassword(user, newPassword);
    }
}
    

