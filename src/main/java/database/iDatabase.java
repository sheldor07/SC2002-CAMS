/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public interface iDatabase {
    public Object getItem(int id);
    public ArrayList getList();
    public boolean add(Object o);
    public boolean delete(int id);
    public boolean editRow(int id, String column, Object newCellValue);
    
}
