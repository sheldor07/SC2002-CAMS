/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Camp;
import entity.Enquiry;
import entity.Faculty;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * This class represents a specific database for managing Enquiry records in an Excel file.
 * It extends the generic Database class and provides methods for querying and manipulating Enquiry data.
 *
 * @author weiya
 */
public class EnquiryDatabase extends Database{

    public final String COLUMN_ENQUIRYID = "ID";
    public final String COLUMN_CAMPID = "Camp ID";
    public final String COLUMN_DETAILS = "Details";
    public final String COLUMN_ANSWER = "Answer";
    public final String COLUMN_ASKEDBY = "Asked By";
    public final String COLUMN_ANSWEREDBY = "Answered By";
    /**
     * Constructor for the EnquiryDatabase class.
     *
     * @param filePath The path to the Excel file used as the Enquiry database.
     */
    public EnquiryDatabase(String filePath) {
        super(filePath);
    }
    /**
     * Retrieves a list of Enquiry objects from the database.
     *
     * @return An ArrayList containing the list of Enquiry objects retrieved from the database.
     */
    @Override
    public ArrayList getList() {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Enquiry> enquries = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String details,answer = "";
            int enquiryId, campId, askedBy, answeredBy = 0;
            int enquiryIsReplied = 1;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                enquiryId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                details = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                if(!cell.getStringCellValue().isBlank() || !cell.getStringCellValue().isEmpty()){
                    enquiryIsReplied = 0;
                }

                answer = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                askedBy = (int) cell.getNumericCellValue();
                cell = cellIterator.next();

                answeredBy = (int) cell.getNumericCellValue();

                if(enquiryIsReplied == 1){
                    enquries.add(new Enquiry(enquiryId,campId,details,answer,askedBy,answeredBy));
                }
                else
                    enquries.add(new Enquiry(enquiryId,campId,details,askedBy));
            }
        }
        return enquries;


    }
    /**
     * Retrieves a list of unanswered Enquiry objects by a specific student's ID.
     *
     * @param studentId The ID of the student whose unanswered Enquiries are to be retrieved.
     * @return An ArrayList containing the list of unanswered Enquiry objects by the specified student.
     */

    public ArrayList getUnansweredEnquiryByStudentId(int studentId) {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Enquiry> enquries = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String details,answer = "";
            int enquiryId, campId, askedBy, answeredBy = 0;
            int enquiryIsReplied = 1;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                enquiryId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                details = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                if(!cell.getStringCellValue().isBlank()){
                    enquiryIsReplied = 0;
                }

                answer = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                askedBy = (int) cell.getNumericCellValue();
                cell = cellIterator.next();

                answeredBy = (int) cell.getNumericCellValue();

                if(askedBy == studentId && answeredBy == -1)
                    enquries.add(new Enquiry(enquiryId,campId,details,askedBy));

            }
        }
        return enquries;
    }
    /**
     * Retrieves a list of answered Enquiry objects by a specific student's ID.
     *
     * @param studentId The ID of the student whose answered Enquiries are to be retrieved.
     * @return An ArrayList containing the list of answered Enquiry objects by the specified student.
     */

    public ArrayList getAnsweredEnquiryByStudentId(int studentId){

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Enquiry> enquries = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String details,answer = "";
            int enquiryId, campId, askedBy, answeredBy = 0;
            int enquiryIsReplied = 1;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                enquiryId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                details = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                if(!cell.getStringCellValue().isBlank()){
                    enquiryIsReplied = 0;
                }

                answer = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                askedBy = (int) cell.getNumericCellValue();
                cell = cellIterator.next();

                answeredBy = (int) cell.getNumericCellValue();

                if(askedBy == studentId && answeredBy != -1)
                    enquries.add(new Enquiry(enquiryId,campId,details,answer,askedBy,answeredBy));
            }
        }
        return enquries;
    }

    /**
     * Retrieves a list of unanswered Enquiry objects by a specific staff member's ID.
     *
     * @param staffId The ID of the staff member whose unanswered Enquiries are to be retrieved.
     * @return An ArrayList containing the list of unanswered Enquiry objects by the specified staff member.
     */
    public ArrayList getUnansweredEnquiryByStaffId(int staffId) {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<Enquiry> enquries = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            String details,answer = "";
            int enquiryId, campId, askedBy, answeredBy = 0;
            int enquiryIsReplied = 1;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                enquiryId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                details = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                if(!cell.getStringCellValue().isBlank()){
                    enquiryIsReplied = 0;
                }

                answer = cell.getStringCellValue().trim();
                cell = cellIterator.next();

                askedBy = (int) cell.getNumericCellValue();
                cell = cellIterator.next();

                answeredBy = (int) cell.getNumericCellValue();

                if(askedBy == staffId)
                    enquries.add(new Enquiry(enquiryId,campId,details,askedBy));

            }
        }
        return enquries;
    }

    /**
     * Adds an Enquiry record to the database.
     *
     * @param enquiry The Enquiry object to be added to the database.
     * @return true if the Enquiry is successfully added, false otherwise.
     */

    public boolean add(Enquiry enquiry) {

        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();

        Row row = sheet.createRow(++lastRow);

        Cell cell;

        // Create cells and put a value in it.
        //ID
        row.createCell(0).setCellValue(enquiry.getId());


        //Camp Id
        row.createCell(1).setCellValue(enquiry.getCampId());

        //Details
        row.createCell(2).setCellValue(enquiry.getDetails());

        //Answer
        if(enquiry.getAnswer() != null)
            row.createCell(3).setCellValue(enquiry.getDetails());
        else
            row.createCell(3).setCellValue("");

        //askedBy
        row.createCell(4).setCellValue(enquiry.getAskedBy());

        //askedBy
        row.createCell(5).setCellValue(-1);


        // Write the output to a file
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(filePath+".xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CampDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CampDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    } /**
     * Retrieves a list of Enquiry objects by a specific camp's ID.
     *
     * @param campId The ID of the camp for which Enquiries are to be retrieved.
     * @return An ArrayList containing the list of Enquiry objects related to the specified camp.
     */


    public ArrayList<Enquiry> getEnquiriesByCampId(int campId) {
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip the header row
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            int enquiryId, fetchedCampId, askedBy, answeredBy;
            String details, answer;
            Cell cell;

            cell = cellIterator.next();
            enquiryId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            fetchedCampId = (int) cell.getNumericCellValue();

            if (fetchedCampId == campId) { // Check if the enquiry belongs to the given campId
                cell = cellIterator.next();
                details = cell.getStringCellValue();
                cell = cellIterator.next();
                answer = cell.getStringCellValue();
                cell = cellIterator.next();
                askedBy = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                answeredBy = (int) cell.getNumericCellValue();

                enquiries.add(new Enquiry(enquiryId, fetchedCampId, details, answer, askedBy, answeredBy));
            }
        }

        return enquiries;
    }

}
