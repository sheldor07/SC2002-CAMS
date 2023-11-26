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
 */
public class SuggestionController implements iSuggestionController {
    
     public SuggestionDatabase suggestionDatabase;
    
    public SuggestionController(){
        suggestionDatabase = new SuggestionDatabase("suggestion_list");
    }
    
    public void refreshCampDatabase(){
        suggestionDatabase = new SuggestionDatabase("suggestion_list");
    }

    @Override
	public ArrayList<Suggestion> getAllSuggestions() {
        return suggestionDatabase.getList();

        }
        
    @Override
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
        
    @Override
        public ArrayList<Suggestion> getUnansweredSuggestionByCampId(int campId){
            return suggestionDatabase.getUnapprovedSuggestionsByCampId(campId);

        }
        
    @Override
        public ArrayList<Suggestion> getApprovedSuggestionsByCampId(int campId){
            return suggestionDatabase.getApprovedSuggestionsByCampId(campId);

        }
        
    @Override
        public ArrayList<Suggestion> getRejectedSuggestionsByCampId(int campId){
            return suggestionDatabase.getRejectedSuggestionsByCampId(campId);

        }
        
    @Override
        public boolean editSuggestionDetail(Suggestion suggestion){
            return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_DETAILS,suggestion.getDetails());
            
        }
        
    @Override
        public boolean approveSuggestion(Suggestion suggestion){
            suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_ANSWEREDBY,suggestion.getAnsweredBy());
            return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_STATUS,suggestion.getStatus());

        }
        
    @Override
        public boolean rejectSuggestion(Suggestion suggestion){
            suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_ANSWEREDBY,suggestion.getAnsweredBy());
            return suggestionDatabase.editRow(suggestion.getId(),suggestionDatabase.COLUMN_STATUS,suggestion.getStatus());

        }
        
    @Override
        public boolean deleteSuggestionDetail(Suggestion suggestion){
        return suggestionDatabase.delete(suggestion.getId());

        }
        
        }
    

