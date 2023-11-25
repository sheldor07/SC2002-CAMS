/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Camp;
import entity.CampInformation;
import entity.Faculty;
import entity.Staff;
import entity.User;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author weiya
 */
public class CampDatabase extends Database{
    
    public final String COLUMN_CAMPNAME = "Name";
    public final String COLUMN_ID = "ID";
    public final String COLUMN_STARTDATE = "Start Date";
    public final String COLUMN_ENDDATE = "End Date";
    public final String COLUMN_REGISTRATIONDEADLINE = "Registration Deadline";
    public final String COLUMN_FACULTY = "Faculty";
    public final String COLUMN_LOCATION = "Location";
    public final String COLUMN_SLOTS = "Slots";
    public final String COLUMN_COMMITTEESLOTS = "Committee Slots";
    public final String COLUMN_DESCRIPTION = "Description";
    public final String COLUMN_STAFFINCHARGE = "Staff In Charge";
    public final String COLUMN_VISIBILITY = "Visibility";
    
    
    public CampDatabase(String filePath){
        
        super(filePath);
    }
    
        public Camp getCampsById(int campId){
        
         Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Camp> camps = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String name,location,description;
    int staffInCharge, totalSlots, id, campCommitteeSlots;
    boolean visibility;
    Date startDate = null, endDate = null, registrationDeadline = null; 
    Faculty faculty;    
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
//        System.out.println(cell.getStringCellValue());
            
            id = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            name = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            startDate = cell.getDateCellValue();
            cell = cellIterator.next();
            endDate = cell.getDateCellValue();
            cell = cellIterator.next();
            registrationDeadline = cell.getDateCellValue();
            cell = cellIterator.next();
            faculty = Faculty.valueOf(cell.getStringCellValue());
            cell = cellIterator.next();
            location = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            totalSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campCommitteeSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            description = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            staffInCharge = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            visibility = (boolean) cell.getBooleanCellValue();

            //camp committee constructor is null for now.
            if(id == campId)
                return new Camp(id,name,location, description, startDate,endDate,registrationDeadline,totalSlots, campCommitteeSlots, staffInCharge, faculty,visibility);
//        public Camp(int id, String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Faculty facultyOpenTo) {

    }
   }
        return null;
    }
    
    public ArrayList getCampsByStaffInCharge(int staffId){
        
         Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Camp> camps = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String name,location,description;
    int staffInCharge, totalSlots, id, campCommitteeSlots;
    boolean visibility;
    Date startDate = null, endDate = null, registrationDeadline = null; 
    Faculty faculty;
    
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
//        System.out.println(cell.getStringCellValue());
            
            id = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            name = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            startDate = cell.getDateCellValue();
            cell = cellIterator.next();
            endDate = cell.getDateCellValue();
            cell = cellIterator.next();
            registrationDeadline = cell.getDateCellValue();
            cell = cellIterator.next();
            faculty = Faculty.valueOf(cell.getStringCellValue());
            cell = cellIterator.next();
            location = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            totalSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campCommitteeSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            description = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            staffInCharge = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            visibility = (boolean) cell.getBooleanCellValue();

            //camp committee constructor is null for now.
            if(staffId == staffInCharge)
            camps.add(new Camp(id,name,location, description, startDate,endDate,registrationDeadline,totalSlots, campCommitteeSlots, staffInCharge, faculty,visibility));
//        public Camp(int id, String name, String location, String description, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Faculty facultyOpenTo) {

    }
   }
        return camps;
    }
    
    //This method will take in user object and return camps that is same as his faculty or open to NTU.
    public ArrayList getCampsByUserFaculty(User user) {
        
        Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Camp> camps = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String name,location,description;
    int staffInCharge, totalSlots, id, campCommitteeSlots;
    boolean visibility;
    Date startDate = null, endDate = null, registrationDeadline = null; 
    Faculty faculty;

    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            id = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            name = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            startDate = cell.getDateCellValue();
            cell = cellIterator.next();
            endDate = cell.getDateCellValue();
            cell = cellIterator.next();
            registrationDeadline = cell.getDateCellValue();
            cell = cellIterator.next();
            faculty = Faculty.valueOf(cell.getStringCellValue());
            cell = cellIterator.next();
            location = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            totalSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campCommitteeSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            description = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            staffInCharge = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            visibility = (boolean) cell.getBooleanCellValue();
            //camp committee constructor is null for now.
            if(user.getFaculty() == faculty || Faculty.NTU == faculty)
            camps.add(new Camp(id,name,location, description, startDate,endDate,registrationDeadline,totalSlots, campCommitteeSlots, staffInCharge, faculty, visibility));

    }
   }
        return camps;
    
    
    }
    
    //overload
    public int add(Camp camp) {
        
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        
        Row row = sheet.createRow(++lastRow);
        
            Cell cell;
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(
            creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));

            // Create cells and put a value in it.
            //ID
            row.createCell(0).setCellValue(camp.getId());

            
            //CampName
            row.createCell(1).setCellValue(camp.getName());
            
            //StartDate
            cell = row.createCell(2);
            cell.setCellValue(camp.getStartDate());
            cell.setCellStyle(cellStyle); 
            
            //EndDate
            cell = row.createCell(3);
            cell.setCellValue(camp.getEndDate());
            cell.setCellStyle(cellStyle); 
            
            //RegistrationDeadline
            cell = row.createCell(4);
            cell.setCellValue(camp.getClosingDate());
            cell.setCellStyle(cellStyle); 

            //Faculty
            row.createCell(5).setCellValue(camp.getOpenTo().toString());
            
            //Location
            row.createCell(6).setCellValue(camp.getLocation());
            
            //participant slots
            row.createCell(7).setCellValue(camp.getParticipantSlots());
            
            //Camp Committee slots
            row.createCell(8).setCellValue(camp.getCampCommSlots());
            
            //Description
            row.createCell(9).setCellValue(camp.getDescription());
            
            //Staff in charge
            row.createCell(10).setCellValue(camp.getStaffInCharge());
            
            //Visibility
            row.createCell(11).setCellValue(camp.isVisible());

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
              
            return 1;
        
    }
    
        @Override
    public ArrayList getList() {
        
        Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Camp> camps = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String name,location,description;
    int staffInCharge, totalSlots, id, campCommitteeSlots;
    boolean visibility;
    Date startDate = null, endDate = null, registrationDeadline = null; 
    Faculty faculty;
    
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            id = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            name = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            startDate = cell.getDateCellValue();
            cell = cellIterator.next();
            endDate = cell.getDateCellValue();
            cell = cellIterator.next();
            registrationDeadline = cell.getDateCellValue();
            cell = cellIterator.next();
            faculty = Faculty.valueOf(cell.getStringCellValue());
            cell = cellIterator.next();
            location = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            totalSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campCommitteeSlots = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            description = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            staffInCharge = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            visibility = (boolean) cell.getBooleanCellValue();
            //camp committee constructor is null for now.
            camps.add(new Camp(id,name,location, description, startDate,endDate,registrationDeadline,totalSlots, campCommitteeSlots, staffInCharge, faculty, visibility));

    }
   }
        return camps;
    
    
    }
    
    @Override
    public boolean delete(int id) {
    return super.delete(id);
    }
    
    @Override
    public boolean editRow(int id, String column, Object newCellValue) {
        return super.editRow(id, column, newCellValue);
    
    }
    
    
    
}
