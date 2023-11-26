/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Suggestion;
import java.util.ArrayList;

/**
 *
 * @author weiya
 */
public interface iSuggestionController {

    boolean approveSuggestion(Suggestion suggestion);

    boolean deleteSuggestionDetail(Suggestion suggestion);

    boolean editSuggestionDetail(Suggestion suggestion);

    ArrayList<Suggestion> getAllSuggestions();

    ArrayList<Suggestion> getApprovedSuggestionsByCampId(int campId);

    ArrayList<Suggestion> getRejectedSuggestionsByCampId(int campId);

    ArrayList<Suggestion> getUnansweredSuggestionByCampId(int campId);

    void refreshCampDatabase();

    boolean rejectSuggestion(Suggestion suggestion);

    boolean submitSuggestion(Suggestion suggestion);
    
}
