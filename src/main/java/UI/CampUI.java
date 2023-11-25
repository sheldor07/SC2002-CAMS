/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import entity.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import controller.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CampUI extends UI {

        public CampUI(User user){
            super(user);
            campController = new CampController();
            campParticipantController = new CampParticipantController();
            participantList = new ArrayList<Student>();
            committeeList = new ArrayList<Student>(); 

        }
	private CampInformation campInfo;
        private Camp camp;
	private CampController campController;
        private CampParticipantController campParticipantController;
	
	ArrayList<Student> participantList;
	ArrayList<Student> committeeList;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        public void setCamp(Camp c){
            campInfo = c.getInformation();
            camp = c;
        }
        
        public int showCreateCamp(){
            String message = "";
               Date startDate = null, endDate = null, registrationDeadline = null;  
                int totalSlots = 0;
                boolean visible = false;
        
                System.out.println("Enter the Camp Details: ");     
                System.out.println("Input Camp Name: ");
                String campName = sc.nextLine();
                      
                message = "Input Start Date, Example: 10/08/1999: ";
                startDate = dateValidator(message);
                
                message = "Input End Date, Example: 10/08/1999: ";
                endDate = dateValidator(message);
           
                message = "Input Registration Deadline, Example: 10/08/1999: ";
                registrationDeadline = dateValidator(message);
           
                System.out.println("Input Camp Location: ");
                String campLocation = sc.nextLine();
          
//                message = "Input Faculty, Example: SCSE/SSS/NBS/NTU: ";
//                Faculty faculty = facultyValidator(message);
           
                System.out.println("Input Description: ");
                String description = sc.nextLine();
           
                message = "Input Total Number of Slots: ";
                totalSlots = integerValidator(message);
           
                message = "Set Camp Visible?(Visible = 1, Not Visible = 0): ";
                visible = booleanValidator(message);
                
                
           
                int result = campController.createCamp(new Camp(campName,campLocation, description, startDate, endDate, registrationDeadline, totalSlots, 10, user.getId(), user.getFaculty(),visible));
           
                return result;
        }
       
            
        
        public void printAllCamps(){
            ArrayList<Camp> camps = campController.getCamps();
            if(!camps.isEmpty()){
            for(Camp c : camps){
                setCamp(c);
                printCampInformation();
            }
            }
            else
                System.out.println("There are no camps available for view.");
        }
        
        public void printCampCreatedByUser(User user){
               ArrayList<Camp> camps = campController.getCamps();
               if(!camps.isEmpty()){
            for(Camp c : camps){
                if(c.getStaffInCharge() == user.getId()){
                setCamp(c);
                printCampInformation();  
                }
               
            }
               }
               else
                System.out.println("You have created no camps.");
        }
        
	
	public void printCampInformation() {//i just putting all info, for staff to view and use
            System.out.println("---------------------------------------------------------");
		System.out.println("Camp Name: " + campInfo.getName());
		System.out.println("Start Date: " + formatter.format(campInfo.getStartDate()));
		System.out.println("End Date: " + formatter.format(campInfo.getEndDate()));
		System.out.println("Registration Closing Date: " + formatter.format(campInfo.getClosingDate()));
		System.out.println("Availability: " + campInfo.getFacultyOpenTo());
		System.out.println("Location: " + campInfo.getLocation());
		System.out.println("Total slots: " + campInfo.getParticipantSlots());
		System.out.println("Committee Slots : " + campInfo.getCampCommSlots());
		System.out.println("Camp Description: " + campInfo.getDescription());
                
                StaffController staffController = new StaffController();
                Staff staff = staffController.getStaffById(campInfo.getStaffInCharge());
		System.out.println("Staff in Charge: " + staff.getName());
		
		System.out.println("Visibility: " + campInfo.isVisible());
                System.out.println("---------------------------------------------------------");
	}
	
	public boolean registerCampUI() {//This method handles the user input and output for when they opt to register for a camp
		String choice;
                System.out.println("These are the camps that are available to you: ");

                showAvailableCampsForStudent(user);
                ArrayList<Camp> camps = campController.getCampsByFaculty(user);
                boolean valid = false;
                Camp camp = null;
                
                do{
                    System.out.println("Please enter the camp you wish to join: ");
                    String campInput = sc.nextLine();
                    
                    for(Camp c: camps){
                        if(campInput.equals(c.getName()))
                        {   
                            camp = c;
                            setCamp(camp);
                            valid = true;
                            break;
                        }
                    }
                    if(!valid)
                        System.out.println("Please select a valid camp name.");
                }while(!valid);
		
		System.out.println("Are you sure you want to register for " + campInfo.getName() + "? Enter 'Y' if yes, 'N' if no.");
		choice = sc.nextLine().toUpperCase();
		
		while(!choice.equals("Y") && !choice.equals("N")) {//Loops error msg + re-prompt on invalid input
			System.out.println("Incorrect input. Please try again.");
			System.out.println("Are you sure you want to register for " + campInfo.getName() + "? Enter 'Y' if yes, 'N' if no.");
			choice = sc.nextLine().toUpperCase();
		}
		
		
		if(choice.equals("Y")) {//User confirms they want to register
			String message= "Are you registering as 1) Attendee or 2) Committee Member? Enter '1' or '2' for your choice. Alternatively, enter '3' to cancel.";
			int input = integerValidator(message);
			
			while(input != 1 && input != 2 && input != 3) {//Loops error msg + re-prompt on invalid input
				System.out.println("Incorrect input. Please try again.");
				System.out.println("Are you registering as 1) Attendee or 2) Committee Member? Enter '1' or '2' for your choice. Alternatively, enter '3' to cancel.");
				input = integerValidator(message);
			}
			
			if(input == 1) { //register as Attendee
				System.out.println("Registering you as an attendee for " + campInfo.getName() + "...");
				
				if(campParticipantController.registerAsParticipant(user,camp)) {
					System.out.println("Attendee registration successful!");
				}
				
				else {
					System.out.println("Registration unsuccessful.");
				}
				
			}
			
			else if(input == 2) {//register as Committee Member
				
				if(campParticipantController.registerAsCommittee(user, camp)) {
					System.out.println("Committee registration successful!");
                                        //return here so we can display camp committee page.
                                        return true;
    				}
				
				else {
					System.out.println("Registration unsuccessful.");
				}
			
			}
			
			else if (input == 3) {//Cancel registration
				System.out.println("Cancelling registration process...");
			}	
		}
		
		else if(choice == "N") {
			System.out.println("Cancelling registration process...");
		}
                
               return false;
				
	}
	
	public void editCampUI() {
		Scanner s = new Scanner(System.in);
		String choice;
		Date date;
                String message = "";
                
                System.out.println("These are the camps created by you.");
                int count = 1;
                
                ArrayList<Camp> campsCreatedByStaff = campController.getCampsByStaff(user.getId());
                for(Camp c : campsCreatedByStaff){
                if(c.getStaffInCharge() == user.getId()){
                    System.out.println(count+") "+c.getName());
                    count++;
                }
               
            }
                int validInput = 0;

                do{
                    
                System.out.println("Which camp would you like to edit?: ");
                String campName = s.nextLine();
                
                    for(Camp c : campsCreatedByStaff){
                if(campName.equals(c.getName())){
                    setCamp(c);
                    validInput = 1;
                    break;
                }   
                    
                    }
                 if(validInput == 0)
                     System.out.println("Please input a valid camp to edit");
                 
                }while(validInput == 0);
                
                validInput = 0;
                int num = 0;
		
                        message = ("Please enter the number of which piece of information you would like to edit: \n"
                                + "1) Camp Name\n"
                                + "2) Start Date\n"
                                + "3) End Date\n"
                                + "4) Registration Closing Date\n"
                                + "5) Availability\n"
                                + "6) Location\n"
                                + "7) Total slots\n"
                                + "8) Camp Description\n"
                                + "9) Visibility\n"
                                + "10) Cancel edit");
		
		num = integerValidator(message);
		
                //specific number validator.
		while(num < 1 || num > 10) {//Loops error msg + re-prompt if user picks anything outside of the range of choices
			System.out.println("Incorrect input. Please try again.");
			num = integerValidator(message);
		}
		int input = 0;
		switch(num) {
		
		case 1: //Change Camp Name
			System.out.println("What would you like to change Camp Name to?");
			choice = s.nextLine();
			if(campController.changeName(campInfo.getCampId(), choice)) {
				System.out.println("Camp Name change successful!");
			}
			else {
				System.out.println("Error with Camp name change.");
			}
			break;
			
			
		case 2: //Change Start Date
			message = "What would you like to change Start Date to? Example: 10/08/2023";
                        Date startDate = dateValidator(message);
                        if(campController.changeStartDate(campInfo.getCampId(), startDate)) {
                                        System.out.println("Start Date change successful!");
                        }
                        break;
			
		case 3: //Change End Date
			message = "What would you like to change End Date to? Example: 10/08/2023";
                        Date endDate = dateValidator(message);
                        if(campController.changeEndDate(campInfo.getCampId(), endDate)) {
                                        System.out.println("End Date change successful!");
                        }
                        break;
			
		case 4: //Change Registration Close Date
			message = "What would you like to change Registraton Deadline to? Example: 10/08/2023";
                        Date regDate = dateValidator(message);
                        if(campController.changeRegCloseDate(campInfo.getCampId(), regDate)) {
                                        System.out.println("Registraton Deadline change successful!");
                        }
                        break;
			
		case 5: //Change availability
                        input = 0;
                        message = "Enter '1' to change to availability to all NTU, and '2' to change availability to own faculty only.";
                        input = integerValidator(message);
			
			while(input != 1 && input != 2) {//Error msg + re-prompt if user chooses outside options
				System.out.println("Incorrect input. Please try again.");
				input = integerValidator(message);
			}
                        
			
			if(input == 1) {//Change to NTU availability
				if(campController.setFacultyTo(campInfo.getCampId(), Faculty.NTU)) {
					System.out.println("Availability change successful!");
				}
				else {
					System.out.println("Availability change unsuccessful.");
				}
			}
			
			else if (input == 2) {//Change to faculty availability
				Faculty faculty = this.user.getFaculty();
				if(campController.setFacultyTo(campInfo.getCampId(), faculty)){
					System.out.println("Availability change successful!");
				}
				else {
					System.out.println("Availability change unsuccessful.");
				}
			}
			break;
			
		case 6: //Change Location
			System.out.println("What would you like to change location to?");
			choice = s.nextLine();
			if(campController.changeLocation(campInfo.getCampId(), choice)) {
				System.out.println("Location change successful!");
			}
			else {
				System.out.println("Location change unsuccessful.");
			}
			break;
			
		case 7: //Change Total Slots
			System.out.println("What would you like to change Total Slots to?");
			choice = s.nextLine();
			int slots = Integer.parseInt(choice);
			
			while(slots < 0) {
				System.out.println("Total slots cannot be a negative number. Please try again.");
				System.out.println("What would you like to change Total Slots to?");
				choice = s.nextLine();
				slots = Integer.parseInt(choice);
			}
			
			if(campController.changeCampParticipantSlots(campInfo.getCampId(), slots)){
				System.out.println("Total Slots change successful!");
			}
			else {
				System.out.println("Total Slots change unsuccessful.");
			}
			break;
			
		case 8: //Change Camp Description
			System.out.println("What would you like to change Camp Description to?");
			choice = s.nextLine();
			if(campController.changeDescription(campInfo.getCampId(), choice)) {
				System.out.println("Description change successful!");
			}
			else {
				System.out.println("Description change unsuccessful.");
		 	}
			break;

		case 9: //Change Visibility
                      message = "Enter '1' if you would like this camp to be visible to students. Enter '0' if you would like this camp to be hidden.";
                        input = integerValidator(message);
                   
			
			while(input != 1 && input != 0) {//Error msg + re-prompt if user chooses outside options
				System.out.println("Incorrect input. Please try again.");
				 message = "Enter '1' if you would like this camp to be visible to students. Enter '0' if you would like this camp to be hidden.";
                                input = integerValidator(message);
			}
			
			if(input == 1) {//Change to visible
				if(campController.changeVisibility(campInfo.getCampId(), true)){
					System.out.println("Visibility change successful!");
				}
				else {
					System.out.println("Visibility change unsuccessful.");
				}
			}
			else if(input == 0) {//Change to invisible
				if(campController.changeVisibility(campInfo.getCampId(), false)) {
					System.out.println("Visibility change successful!");
				}
				else {
					System.out.println("Visibility change unsuccessful.");
				}
			}
			break;
			
		case 10:
			System.out.println("Cancelling edit process.");
			break;
			
		}
		
		
	}
        
        public void showAvailableCampsForStudent(User user) {//Shows what a regular student would see for the camp - Camp Name, description, remaining slots
                ArrayList<Camp> camps = campController.getCampsByFaculty(user);
                
                int counter = 1;
                if(!camps.isEmpty()){
                for(Camp c : camps){
                setCamp(c);
                System.out.println("");
                System.out.println(counter+")"+ "Camp Name: " + campInfo.getName());
		System.out.println("Description: " + campInfo.getDescription());
		System.out.println("Remaining slots: " + campInfo.getRemainingSlots());
                counter++;
                }
                }else{
                    System.out.println("There are no camps available at this moment.");
                }
                System.out.println("");
	}
        
	
	public void viewEnquiry() {
		
	}
	
	public void viewSuggestion() {
		
	}
	
	public void printCampParticipants() {
		
	}

	
	public void staffUI() {//Shows what staff would see for the camp (full info)
		this.printCampInformation();
		
	}
	
	public void showRegisteredCamps(){
            ArrayList<CampParticipant> campParticipants = campParticipantController.getListByStudentId(user.getId());
            
            int counter = 1;
            if(!campParticipants.isEmpty())
            {
                for(CampParticipant participant : campParticipants){
                    Camp camp = campController.getCampById(participant.getCampId());
                    System.out.println("");
                    System.out.println(counter+") Camp Name: "+camp.getName());
                    System.out.println("Description: "+camp.getDescription());
                    
                }
            }
            else
                System.out.println("You did not register for any camp.");
                
            
            
        }

	public void withdrawCampUI() {//Handles user input and output for withdrawing from a camp
                ArrayList<CampParticipant> participants = campParticipantController.getListByStudentId(user.getId());
            
                System.out.println("You have signed up for these camps: ");
                int count = 1;
               ArrayList<Camp> camps = new ArrayList();
                for(CampParticipant campParticipant : participants){
                    Camp camp = campController.getCampById(campParticipant.getCampId());
                    camps.add(camp);
                    System.out.println(count+") "+camp.getName());
                    count++;
   
            }
                int validInput = 0;
                CampParticipant chosenCampParticipant = null;
                do{
                    
                System.out.println("Which camp would you like to withdraw from?: ");
                String campName = sc.nextLine();
                
                    for(CampParticipant campParticipant : participants){
                       Camp camp = campController.getCampById(campParticipant.getCampId());
                    if(campName.equals(camp.getName())){
                        chosenCampParticipant = campParticipant;
                        setCamp(camp);
                        validInput = 1;
                        break;
                }   
                    
                    }
                 if(validInput == 0)
                     System.out.println("Please input a valid camp to edit");
                 
                }while(validInput == 0);
                
		String choice;
		System.out.println("Are you sure you want to withdraw from " + campInfo.getName() + "?");
		System.out.println("Enter Y to confirm your decision, or N to cancel.");
		choice = sc.nextLine().toUpperCase();
		
		while(!choice.equals("Y") && !choice.equals("N")) {//Loops error msg + re-prompt on invalid input 
			System.out.println("Incorrect input. Please try again.");
			choice = sc.nextLine().toUpperCase();
		}
		
		if(choice.equals("Y")) {
			System.out.println("Withdrawing you from " + campInfo.getName() + "...");
			if(campParticipantController.withdraw(chosenCampParticipant.getId())) {
				System.out.println("Withdrawal successful!");
			}
			
			else {
				System.out.println("Issue with withdrawal");
			}
		}
		
		else if(choice.equals("N")) {
			System.out.println("Cancelling withdrawal process...");
		}
		
		
	}

}

