/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Camp;
import entity.CampParticipant;
import entity.Enquiry;
import entity.Student;
import entity.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author weiya
 */
public class CampParticipantDatabase extends Database {

    public final String COLUMN_ID = "ID";
    public final String COLUMN_CAMPID = "Camp ID";
    public final String COLUMN_STUDENTID = "Student ID";
    public final String COLUMN_STAFFINCHARGEID = "Staff In Charge ID";
    public final String COLUMN_ISCAMPCOMMITTEE = "IsCampComittee";


    public CampParticipantDatabase(String filePath) {
        super(filePath);
    }

    public boolean add(CampParticipant campParticipant) {

        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();

        Row row = sheet.createRow(++lastRow);

        Cell cell;

        // Create cells and put a value in it.
        //ID
        row.createCell(0).setCellValue(campParticipant.getId());


        //Camp Id
        row.createCell(1).setCellValue(campParticipant.getCampId());

        //Student Id
        row.createCell(2).setCellValue(campParticipant.getStudentId());

        //Staff In charge Id
        row.createCell(3).setCellValue(campParticipant.getStaffInChargeId());

        //is student camp comittee
        row.createCell(4).setCellValue(campParticipant.isCampCommittee());


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

    }

    @Override
    public ArrayList getList() {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<CampParticipant> participants = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            int campParticipantRowID, campId, studentId, staffInChargeId;
            boolean isCampComittee;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                campParticipantRowID = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                studentId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                staffInChargeId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                isCampComittee = (boolean) cell.getBooleanCellValue();
                participants.add(new CampParticipant(campParticipantRowID,campId,studentId,staffInChargeId,isCampComittee));


            }
        }
        return participants;


    }

    public ArrayList getListByStudentId(int sID) {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<CampParticipant> participants = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            int campParticipantRowID, campId, studentId, staffInChargeId;
            boolean isCampComittee;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                campParticipantRowID = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                studentId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                staffInChargeId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                isCampComittee = (boolean) cell.getBooleanCellValue();
                if(sID == studentId)
                    participants.add(new CampParticipant(campParticipantRowID,campId,studentId,staffInChargeId,isCampComittee));


            }
        }
        return participants;


    }


    public CampParticipant getCommitteeByStudentId(int sID) {

        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //Iterate one row to skip the column header
        Row row = rowIterator.next();

        ArrayList<CampParticipant> participants = new ArrayList();

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            int campParticipantRowID, campId, studentId, staffInChargeId;
            boolean isCampComittee;
            while(cellIterator.hasNext()) {
                cell = cellIterator.next();

                campParticipantRowID = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                campId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                studentId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                staffInChargeId = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                isCampComittee = (boolean) cell.getBooleanCellValue();
                if(sID == studentId && isCampComittee == true)
                    return new CampParticipant(campParticipantRowID,campId,studentId,staffInChargeId,isCampComittee);


            }
        }
        return null;


    }public ArrayList<CampParticipant> getListByCampId(int campId) {
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through each row from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        // Iterate one row to skip the column header
        if(rowIterator.hasNext()) {
            rowIterator.next(); // Skip the header row
        }

        ArrayList<CampParticipant> participants = new ArrayList<>();

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // For each row, iterate through each column
            Iterator<Cell> cellIterator = row.cellIterator();

            int campParticipantRowID, fetchedCampId, studentId, staffInChargeId;
            boolean isCampCommittee;

            // Assuming the cells are in the order ID, Camp ID, Student ID, Staff In Charge ID, IsCampCommittee
            campParticipantRowID = (int) cellIterator.next().getNumericCellValue();
            fetchedCampId = (int) cellIterator.next().getNumericCellValue();
            studentId = (int) cellIterator.next().getNumericCellValue();
            staffInChargeId = (int) cellIterator.next().getNumericCellValue();
            isCampCommittee = cellIterator.next().getBooleanCellValue();

            if (fetchedCampId == campId) {
                participants.add(new CampParticipant(campParticipantRowID, fetchedCampId, studentId, staffInChargeId, isCampCommittee));
            }
        }

        return participants;
    }



}
