/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.ArrayList;

/**
 * This interface defines the basic operations that a database class should implement.
 * Implementing classes should provide concrete implementations for these methods.
 *
 * @author weiya
 */

public interface iDatabase {
    /**
     * Retrieves an item from the database based on the provided ID.
     *
     * @param id The ID of the item to be retrieved.
     * @return The retrieved item.
     */
    public Object getItem(int id);
    /**
     * Retrieves a list of items from the database.
     *
     * @return An ArrayList containing the list of items retrieved from the database.
     */

    public ArrayList getList();
    /**
     * Adds an item to the database.
     *
     * @param o The item to be added to the database.
     * @return true if the item is successfully added, false otherwise.
     */
    public boolean add(Object o);
    /**
     * Deletes an item from the database based on the provided ID.
     *
     * @param id The ID of the item to be deleted.
     * @return true if the item is successfully deleted, false otherwise.
     */

    public boolean delete(int id);
    /**
     * Edits a specific column in a row of the database.
     *
     * @param id The ID of the row to be edited.
     * @param column The name of the column to be edited.
     * @param newCellValue The new value to be set in the specified column.
     * @return true if the column is successfully edited, false otherwise.
     */

    public boolean editRow(int id, String column, Object newCellValue);
    
}
