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
 *
 * @author weiya
 */
public class StaffDatabase extends UserDatabase {
    
    public StaffDatabase(String filePath){
        super(filePath);
    }
    
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
    
    @Override
    public boolean changePassword(User user, String newPassword)
    {      
        return super.changePassword(user, newPassword);
    }
}
    

