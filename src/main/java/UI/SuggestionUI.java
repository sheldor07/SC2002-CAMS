/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import controller.CampController;
import controller.CampParticipantController;
import controller.StudentController;
import controller.SuggestionController;
import entity.Camp;
import entity.CampCommittee;
import entity.CampParticipant;
import entity.Faculty;
import entity.Staff;
import entity.Student;
import entity.Suggestion;
import entity.User;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author weiya
 */
public class SuggestionUI extends UI{
    SuggestionController suggestionController;
    CampParticipantController campParticipantController;
    CampController campController;
    ArrayList<Suggestion> unansweredSuggestions;
    public SuggestionUI(User user) {
        super(user);
        suggestionController = new SuggestionController();
        campParticipantController = new CampParticipantController();
        campController = new CampController();
        unansweredSuggestions = new ArrayList();
    }
    
    public void submitSuggestionUI(){
        CampParticipant participant = campParticipantController.getCommitteeByStudentId(user.getId());
        Camp camp = campController.getCampById(participant.getCampId());
        
        String message = "What suggestion do you want to submit for "+camp.getName()+": ";
        String input = stringValidator(message);
        
        message = "Are you sure you want to submit the suggestion? Enter 'Y' to confirm, or 'N' to cancel.";        
        String choice = stringValidator(message);
			
                    while(!choice.toUpperCase().equals("Y") && !choice.toUpperCase().equals("N")) {
                            System.out.println("Incorect input. Please try again.");
                            choice = stringValidator(message);
                    }
			
                if(choice.toUpperCase().equals("Y")) {//submit suggestion
                        System.out.println("Submitting Suggestion...");

                        if(suggestionController.submitSuggestion(new Suggestion(camp.getId(),input,user.getId()))) {
                                System.out.println("Submitted Suggestion!");
                        }
                        else {
                                System.out.println("Enquiry deletion unsuccessful.");
                        }
                }
			
                else if(choice.toUpperCase().equals("N")) {//Cancel process
                        System.out.println("Cancelling deletion process...");
                }
        
}
        
    
    public void printUnansweredSuggestionUI() {
                unansweredSuggestions = new ArrayList();
                
                //if user is camp committee, we display the suggestion based on student id.
                Student student;
                if(user instanceof CampCommittee){
                CampParticipant participant = campParticipantController.getCommitteeByStudentId(user.getId());
                unansweredSuggestions = suggestionController.getUnansweredSuggestionByCampId(participant.getCampId());
                }
                
                //if user is staff, we display the suggestion based on staff's perspective.
                ArrayList<Camp> camps = new ArrayList();
                if(user instanceof Staff){
                camps = campController.getCampsByStaff(user.getId());
                
                for(Camp c : camps){
                    
                    unansweredSuggestions.addAll(suggestionController.getUnansweredSuggestionByCampId(c.getId()));
                }
                }
                if(!unansweredSuggestions.isEmpty()){
                System.out.println("");
                    
                System.out.println("List of pending suggestions:");
                int counter = 1;
                for(Suggestion s: unansweredSuggestions){
                StudentController sc = new StudentController();
                student = sc.getStudentById(s.getAskedBy());
                System.out.println(counter+") Suggestion by " + student.getName());
                System.out.println("Details : "+s.getDetails());
                counter++;
                }
                
                }else{
                    System.out.println("You have no pending suggestion.");
                }
                System.out.println("");
		
		//TODO RETURN TO ENQUIRY MENU
	}
    
       public void printApprovedSuggestionUI() {
                ArrayList<Suggestion> approvedSuggestions = new ArrayList();
                Student student = null;

                CampParticipant participant = campParticipantController.getCommitteeByStudentId(user.getId());
                StudentController sc = new StudentController();
                student = sc.getStudentById(participant.getStudentId());
                approvedSuggestions = suggestionController.getApprovedSuggestionsByCampId(participant.getCampId());
                
                if(!approvedSuggestions.isEmpty()){
                System.out.println("");
                
                System.out.println("List of approved suggestions:");
                int counter = 1;
                for(Suggestion s: approvedSuggestions){
               
                System.out.println(counter+") Suggestion by " + student.getName());
                System.out.println("Details : "+s.getDetails());
                counter++;
                }
                
                }else{
                    System.out.println("You have no approved suggestion.");
                }
                System.out.println("");
		
		//TODO RETURN TO ENQUIRY MENU
	}
       
        public void printRejectedSuggestionUI() {
                ArrayList<Suggestion> rejectedSuggestions = new ArrayList();
                CampParticipant participant = campParticipantController.getCommitteeByStudentId(user.getId());
                StudentController sc = new StudentController();
                Student student = sc.getStudentById(participant.getStudentId());
                rejectedSuggestions = suggestionController.getRejectedSuggestionsByCampId(participant.getCampId());
                
                if(!rejectedSuggestions.isEmpty()){
                System.out.println("");    
                
                System.out.println("List of rejected suggestions:");
                int counter = 1;
                for(Suggestion s: rejectedSuggestions){

                System.out.println(counter+") Suggestion by " + student.getName());
                System.out.println("Details : "+s.getDetails());
                 
                counter++;
                }
                
                }else{
                    System.out.println("You have no rejected suggestion.");
                }
                System.out.println("");
		
		//TODO RETURN TO ENQUIRY MENU
	}

    public void editUnansweredSuggestionUI(){ 
        printUnansweredSuggestionUI();
        
       if(!unansweredSuggestions.isEmpty()){
           
                System.out.println("Which suggestion would you like to edit?: ");
                int selection = integerValidator("Select the suggestion number: ");
                
                while(selection > unansweredSuggestions.size() && selection < 0){
                    System.out.println("Please select a valid suggestion number");
                    selection = integerValidator("Select the suggestion number: ");
                }    
                
            Suggestion selectedSuggestion = unansweredSuggestions.get(selection-1);
            String message = "Please input your suggestion edits: ";
            String edittedSuggestion = stringValidator(message);

            message = "Are you sure you would like to replace your old suggestion with this? Enter 'Y' to confirm, and 'N' to cancel.";
            String choice = stringValidator(message);

            while(!choice.toUpperCase().equals("Y") && !choice.toUpperCase().equals("N")) {
                    System.out.println("Incorect input. Please try again.");
                    choice = stringValidator(message);
            }

            if(choice.equals("Y")) {//Edit process confirmed
                    System.out.println("Changing your suggestion details...");
                    selectedSuggestion.setDetails(edittedSuggestion);

                    if(suggestionController.editSuggestionDetail(selectedSuggestion)){
                            System.out.println("Suggestion details changed successful!");
                    }
                    else {
                            System.out.println("Suggestion details change is unsuccessful.");
                    }
            }
            else if(choice.equals("N")) {//Edit process cancelled
                    System.out.println("Cancelling edit process...");
            }
			
		
		
		//TODO RETURN TO ENQUIRY MENU
	}
                else{
                    System.out.println("You have no pending suggestion");
                }
        
    }
    
    public void deleteUnansweredSuggestionUI(){
        
        printUnansweredSuggestionUI();
        
        if(!unansweredSuggestions.isEmpty()){
            
            System.out.println("Which suggestion would you like to delete?: ");
                int selection = integerValidator("Select the suggestion number: ");
                
                while(selection > unansweredSuggestions.size() && selection < 0){
                    System.out.println("Please select a valid suggestion number");
                    selection = integerValidator("Select the suggestion number: ");
                }    
                
            Suggestion selectedSuggestion = unansweredSuggestions.get(selection-1);

            String message = "Are you sure you would like to delete your suggestion? Enter 'Y' to confirm, and 'N' to cancel.";
            String choice = stringValidator(message);

            while(!choice.toUpperCase().equals("Y") && !choice.toUpperCase().equals("N")) {
                    System.out.println("Incorect input. Please try again.");
                    choice = stringValidator(message);
            }

            if(choice.toUpperCase().equals("Y")) {//Edit process confirmed
                    System.out.println("Deleting your enquiry...");

                    if(suggestionController.deleteSuggestionDetail(selectedSuggestion)){
                            System.out.println("Suggestion details deleted successful!");
                    }
                    else {
                            System.out.println("Suggestion details deletion is unsuccessful.");
                    }
            }
            else if(choice.toUpperCase().equals("N")) {//Edit process cancelled
                    System.out.println("Cancelling edit process...");
            }
			
		
		
		//TODO RETURN TO ENQUIRY MENU
	}
                else{
                    System.out.println("You have no pending suggestion");
                }
        
    }
    
    
    public void updateUnansweredSuggestionsUI(){
        
        printUnansweredSuggestionUI();
        
         if(!unansweredSuggestions.isEmpty()){

            System.out.println("Which suggestion would you like to approve/reject?: ");
                int selection = integerValidator("Select the suggestion number: ");
                
                while(selection > unansweredSuggestions.size() && selection < 0){
                    System.out.println("Please select a valid suggestion number");
                    selection = integerValidator("Select the suggestion number: ");
                }    
                
            Suggestion selectedSuggestion = unansweredSuggestions.get(selection-1);

            String message = "Would you like to accept or reject this suggestion? Enter 'Y' to Accept, and 'N' to Reject.";
            String choice = stringValidator(message);

            while(!choice.toUpperCase().equals("Y") && !choice.toUpperCase().equals("N")) {
                    System.out.println("Incorect input. Please try again.");
                    choice = stringValidator(message);
            }

            if(choice.toUpperCase().equals("Y")) {//Approving suggestion
                    System.out.println("Approving suggestion...");
                    selectedSuggestion.setAnsweredBy(user.getId());
                    selectedSuggestion.setStatus(true);

                    if(suggestionController.approveSuggestion(selectedSuggestion)){
                            System.out.println("Suggestion approved!");
                            
                            StudentController controller = new StudentController();
                            controller.addStudentPoint(selectedSuggestion.getAskedBy());
                    }
                    else {
                            System.out.println("Something went wrong.");
                    }
            }
            else if(choice.toUpperCase().equals("N")) {//Rejecting suggestion
                
                    System.out.println("Rejecting suggestion...");
                    selectedSuggestion.setAnsweredBy(user.getId());
                    selectedSuggestion.setStatus(false);

                    if(suggestionController.rejectSuggestion(selectedSuggestion)){
                            System.out.println("Suggestion rejected!");
                    }
                    else {
                            System.out.println("Something went wrong.");
                    }
            }
			
		
		
		//TODO RETURN TO ENQUIRY MENU
	}
                else{
                    System.out.println("You have no pending suggestion");
        }
    }
    }
    

