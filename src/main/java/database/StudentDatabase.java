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
 *
 * @author weiya
 */
public class StudentDatabase extends UserDatabase {
   
    public StudentDatabase(String filePath){
        super(filePath);
    }
    
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
    int id;
   
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
            students.add(new Student(id, name,email,password,faculty));
         
    }
   }
   return students;    
    }
    
    @Override
    public boolean changePassword(User user, String newPassword)
    {      
        return super.changePassword(user, newPassword);
    }
    
    
}
