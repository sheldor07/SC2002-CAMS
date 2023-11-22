/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.Camp;
import entity.Faculty;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author weiya
 */
public class Database implements iDatabase{
    protected String filePath;
    protected Workbook workbook;
    protected CreationHelper creationHelper;
    
    public Database(String filePath){
        this.filePath = filePath;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(filePath+".xlsx"));
            workbook = new XSSFWorkbook(fileInputStream);
            creationHelper = workbook.getCreationHelper();
        } catch (IOException ex) {
            System.out.println("File not found");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        //it is unsupported as it will be implemented by subclasses
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Object o) {
         //it is unsupported as it will be implemented by subclasses. Each database have different
         //columns, so we cannot implement here.
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
       Sheet sheet = workbook.getSheetAt(0);
       
        //Iterate through each rows from first sheet
       Iterator<Row> rowIterator = sheet.iterator();
       //Iterate one row to skip the column header
       Row row = rowIterator.next();
       int rowNum =  1;
      
   while(rowIterator.hasNext()) {

         row = rowIterator.next();
         rowNum++;
         Iterator<Cell> cellIterator = row.cellIterator();
         Cell cell;
        
         while(cellIterator.hasNext()) {
             
            cell = cellIterator.next();
            
            if(cell.getCellType().equals(CellType.NUMERIC) && (int)cell.getNumericCellValue() == id){
                sheet.removeRow(row);
                
                sheet.shiftRows(rowNum, rowNum, -1);
                FileOutputStream fileOut;
                try {
                    fileOut = new FileOutputStream(filePath+".xlsx");
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }

         }
        }
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    return false;
    }

    @Override
    public boolean editRow(int id, String column, Object newCellValue) {
        
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        int columnIndex = 0;
        int iterator = 0;
        if(rowIterator.hasNext()) {

        Row row = rowIterator.next();
          //Iterate through each columns
        Iterator<Cell> cellIterator = row.cellIterator();
        Cell cell;

        //looping through the columns in Database to find the column that we want to change
        while(cellIterator.hasNext()) {
            cell = cellIterator.next();
            
            if(!cell.getCellType().equals(CellType.NUMERIC) && !cell.getCellType().equals(CellType.BOOLEAN) && cell.getStringCellValue().equals(column)){
                columnIndex = iterator;
                break;
            }
            
            else
               iterator++;
            
        }
        }
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            
            
            if((int)cell.getNumericCellValue() == id){

               for(int i = 0; i < columnIndex; i++){
                    cell = cellIterator.next();
                }
                
               if(newCellValue instanceof String){
                   cell.setCellValue((String)newCellValue);
               }
               else if(newCellValue instanceof Integer){
                   cell.setCellValue((Integer)newCellValue);
               }
               else if(newCellValue instanceof Date){
                   CellStyle cellStyle = workbook.createCellStyle();
                   cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));
                   cell.setCellValue((Date)newCellValue);
                   cell.setCellStyle(cellStyle); 
               }
               
               else if(newCellValue instanceof Faculty){
                   cell.setCellValue((String)newCellValue);
               }
               else{
               }

               FileOutputStream fileOut;
                try {
                    fileOut = new FileOutputStream(filePath+".xlsx");
                    workbook.write(fileOut);
                    fileOut.close();
                    
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                return true;
                }
        }

           return false;

    }

    @Override
    public Object getItem(int id) {
                //it is unsupported as it will be implemented by subclasses
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
