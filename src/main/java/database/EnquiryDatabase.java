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
    
    public EnquiryDatabase(String filePath) {
        super(filePath);
    }
    
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
        
    }
    
}
