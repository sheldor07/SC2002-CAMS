/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Suggestion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author weiya
 */
public class SuggestionDatabase extends Database
{
    
    public final String COLUMN_ENQUIRYID = "ID";
    public final String COLUMN_CAMPID = "Camp ID";
    public final String COLUMN_STATUS = "Status";
    public final String COLUMN_DETAILS = "Details";
    public final String COLUMN_ASKEDBY = "Asked By";
    public final String COLUMN_ANSWEREDBY = "Answered By";
    
    public SuggestionDatabase(String filePath) {
        super(filePath);
    }
    
        @Override
    public ArrayList getList() {
        
        Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Suggestion> suggestions = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String details,answer = "";
    int suggestionId, campId, askedBy, answeredBy = 0; 
    boolean status;
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            suggestionId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            details = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            status = cell.getBooleanCellValue();
            cell = cellIterator.next();        
            askedBy = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            answeredBy = (int) cell.getNumericCellValue();
            
            suggestions.add(new Suggestion(suggestionId,campId,details,status,askedBy,answeredBy));

    
   }
        
    } 
            return suggestions;
    }
    
    public boolean add(Suggestion suggestion) {
        
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        
        Row row = sheet.createRow(++lastRow);
        
            Cell cell;

            // Create cells and put a value in it.
            //ID
            row.createCell(0).setCellValue(suggestion.getId());

            
            //Camp Id
            row.createCell(1).setCellValue(suggestion.getCampId());
            
            //Details
            row.createCell(2).setCellValue(suggestion.getDetails());
            
            //Status
            row.createCell(3).setCellValue(false);
            
            //askedBy
            row.createCell(4).setCellValue(suggestion.getAskedBy());
            
            //answereddBy
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
    
 
            
    public ArrayList<Suggestion> getUnapprovedSuggestionsByCampId(int cId) {
        
        Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Suggestion> suggestions = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String details,answer = "";
    boolean status;
    int suggestionId, campId, askedBy, answeredBy = 0; 
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            suggestionId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            details = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            status = cell.getBooleanCellValue();
            cell = cellIterator.next();        
            askedBy = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            answeredBy = (int) cell.getNumericCellValue();
            
            if(campId == cId && answeredBy == -1)
            suggestions.add(new Suggestion(suggestionId,campId,details,status,askedBy,answeredBy));
            
    }
   }
        return suggestions;
    }
    
    public ArrayList<Suggestion> getApprovedSuggestionsByCampId(int cId) {
        
    Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Suggestion> suggestions = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String details,answer = "";
    boolean status;
    int suggestionId, campId, askedBy, answeredBy = 0; 
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            suggestionId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            details = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            status = cell.getBooleanCellValue();
            cell = cellIterator.next();        
            askedBy = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            answeredBy = (int) cell.getNumericCellValue();
            
            if(campId == cId && answeredBy != -1 && status == true)
            suggestions.add(new Suggestion(suggestionId,campId,details,status,askedBy,answeredBy));
            
    }
   }
        return suggestions;
    }
    
    public ArrayList<Suggestion> getRejectedSuggestionsByCampId(int cId) {
        
    Sheet sheet = workbook.getSheetAt(0);
    
    //Iterate through each rows from first sheet
   Iterator<Row> rowIterator = sheet.iterator();
   //Iterate one row to skip the column header
   Row row = rowIterator.next();
   
   ArrayList<Suggestion> suggestions = new ArrayList();

   while(rowIterator.hasNext()) {
    row = rowIterator.next();
     //For each row, iterate through each columns
    Iterator<Cell> cellIterator = row.cellIterator();
    Cell cell;
    String details,answer = "";
    boolean status;
    int suggestionId, campId, askedBy, answeredBy = 0; 
    while(cellIterator.hasNext()) {
        cell = cellIterator.next();
            
            suggestionId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            campId = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            details = cell.getStringCellValue().trim();
            cell = cellIterator.next();
            status = cell.getBooleanCellValue();
            cell = cellIterator.next();        
            askedBy = (int) cell.getNumericCellValue();
            cell = cellIterator.next();
            answeredBy = (int) cell.getNumericCellValue();
            
            if(campId == cId && answeredBy != -1 && status == false)
            suggestions.add(new Suggestion(suggestionId,campId,details,status,askedBy,answeredBy));
            
    }
   }
        return suggestions;
    }
}
    

