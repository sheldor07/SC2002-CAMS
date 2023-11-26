/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.SuggestionDatabase;
import entity.Suggestion;
import java.util.ArrayList;

/**
 *
 * @author weiya
 *//**
 * Controller class for managing operations related to suggestions.
 * It facilitates the submission, retrieval, and modification of suggestions in a database.
 */

public class SuggestionController {

    public SuggestionDatabase suggestionDatabase;
    /**
     * Constructor to initialize the SuggestionController with a suggestion database.
     */

    public SuggestionController(){
        suggestionDatabase = new SuggestionDatabase("suggestion_list");
    }
    /**
     * Refreshes the current state of the suggestion database.
     */
    public void refreshCampDatabase(){
        suggestionDatabase = new SuggestionDatabase("suggestion_list");
    }
    /**
     * Retrieves all suggestions from the database.
     *
     * @return An ArrayList of Suggestion objects.
     */
    public ArrayList<Suggestion> getAllSuggestions() {
        return suggestionDatabase.getList();

    }
    /**
     * Submits a new suggestion to the database.
     *
     * @param suggestion The Suggestion object to be added.
     * @return true if the submission is successful, false otherwise.
     */
    public boolean submitSuggestion(Suggestion suggestion){
        ArrayList<Suggestion> suggestions = getAllSuggestions();

        int max = 0;
        for(Suggestion s : suggestions){
            if(s.getId() > max)
                max = s.getId();
        }
        suggestion.setId(max+1);
        boolean result = suggestionDatabase.add(suggestion);
        refreshCampDatabase();

        return result;

    }
    /**
     * Retrieves a list of unanswered suggestions for a specific camp.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of Suggestion objects.
     */
    public ArrayList<Suggestion> getUnansweredSuggestionByCampId(int campId){
        return suggestionDatabase.getUnapprovedSuggestionsByCampId(campId);

    }
    /**
     * Retrieves a list of approved suggestions for a specific camp.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of Suggestion objects.
     */
    public ArrayList<Suggestion> getApprovedSuggestionsByCampId(int campId){
        return suggestionDatabase.getApprovedSuggestionsByCampId(campId);

    }

    /**
     * Retrieves a list of rejected suggestions for a specific camp.
     *
     * @param campId The ID of the camp.
     * @return An ArrayList of Suggestion objects.
     */

    public ArrayList<Suggestion> getRejectedSuggestionsByCampId(int campId){
        return suggestionDatabase.getRejectedSuggestionsByCampId(campId);

    }
    /**
     * Edits the details of an existing suggestion.
     *
     * @param suggestion The Suggestion object with updated details.
     * @return true if the edit is successful, false otherwise.
     */
    public boolean editSuggestionDetail(Suggestion suggestion){
        return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_DETAILS,suggestion.getDetails());

    }
    /**
     * Approves a suggestion in the database.
     *
     * @param suggestion The Suggestion object to be approved.
     * @return true if the approval is successful, false otherwise.
     */
    public boolean approveSuggestion(Suggestion suggestion){
        suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_ANSWEREDBY,suggestion.getAnsweredBy());
        return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_STATUS,suggestion.getStatus());

    }
    /**
     * Rejects a suggestion in the database.
     *
     * @param suggestion The Suggestion object to be rejected.
     * @return true if the rejection is successful, false otherwise.
     */
    public boolean rejectSuggestion(Suggestion suggestion){
        suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_ANSWEREDBY,suggestion.getAnsweredBy());
        return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_STATUS,suggestion.getStatus());

    }
    /**
     * Deletes the details of a suggestion from the database.
     *
     * @param suggestion The Suggestion object to be deleted.
     * @return true if the deletion is successful, false otherwise.
     */

    public boolean deleteSuggestionDetail(Suggestion suggestion){
        return suggestionDatabase.delete(suggestion.getId());

    }

}
    

