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

public class CampUI extends MainUI {

        public CampUI(User user){
            super(user);
            controller = new CampController();
            participantList = new ArrayList<Student>();
            committeeList = new ArrayList<Student>(); 

        }
	private CampInformation campInfo;
	private CampController controller;
	
	ArrayList<Student> participantList;
	ArrayList<Student> committeeList;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        public void setCamp(Camp c){
            campInfo = c.getInformation();
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
          
                message = "Input Faculty, Example: SCSE/SSS/NBS/NTU: ";
                Faculty faculty = facultyValidator(message);
           
                System.out.println("Input Description: ");
                String description = sc.nextLine();
           
                message = "Input Total Number of Slots: ";
                totalSlots = integerValidator(message);
           
                message = "Set Camp Visible?(Visible = 1, Not Visible = 0): ";
                visible = booleanValidator(message);
                
                
           
                int result = controller.createCamp(new Camp(campName,campLocation, description, startDate, endDate, registrationDeadline, totalSlots, 10, user.getId(), faculty,visible));
           
                return result;
        }
       
            
        
        public void printAllCamps(){
            ArrayList<Camp> camps = controller.getCamps();
            for(Camp c : camps){
                setCamp(c);
                printCampInformation();
            }
        }
        
        public void printCampCreatedByUser(User user){
               ArrayList<Camp> camps = controller.getCamps();
            for(Camp c : camps){
                if(c.getStaffInCharge() == user.getId()){
                setCamp(c);
                printCampInformation();  
                }
               
            }
        }
        
	
	public void printCampInformation() {//i just putting all info, for staff to view and use
            System.out.println("///////////////////////////////////////////////////////////////");
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
                System.out.println("///////////////////////////////////////////////////////////////");
	}
	
	public void registerCampUI() {//This method handles the user input and output for when they opt to register for a camp
		Scanner s = new Scanner(System.in);
		String choice;
		
		System.out.println("Are you sure you want to register for " + campInfo.getName() + "? Enter 'Y' if yes, 'N' if no.");
		choice = s.nextLine().toUpperCase();
		
		while(choice != "Y" && choice != "N") {//Loops error msg + re-prompt on invalid input
			System.out.println("Incorrect input. Please try again.");
			System.out.println("Are you sure you want to register for " + campInfo.getName() + "? Enter 'Y' if yes, 'N' if no.");
			choice = s.nextLine().toUpperCase();
		}
		
		
		if(choice == "Y") {//User confirms they want to register
			System.out.println("Are you registering as 1) Attendee or 2) Committee Member? Enter '1' or '2' for your choice. Alternatively, enter '3' to cancel.");
			choice = s.nextLine();	
			
			while(choice != "1" && choice != "2" && choice != "3") {//Loops error msg + re-prompt on invalid input
				System.out.println("Incorrect input. Please try again.");
				System.out.println("Are you registering as 1) Attendee or 2) Committee Member? Enter '1' or '2' for your choice. Alternatively, enter '3' to cancel.");
				choice = s.nextLine();
			}
			
			if(choice == "1") { //register as Attendee
				System.out.println("Registering you as an attendee for " + campInfo.getName() + "...");
				
				if(controller.registerAsParticipant(this.user.getId())) {
					System.out.println("Attendee registration successful!");
				}
				
				else {
					System.out.println("Registration unsuccessful.");
				}
				
			}
			
			else if(choice == "2") {//register as Committee Member
				
				if(controller.registerAsCommittee(this.user.getId())) {
					System.out.println("Committee registration successfu!");
				}
				
				else {
					System.out.println("Registration unsuccessful.");
				}
			
			}
			
			else if (choice == "3") {//Cancel registration
				System.out.println("Cancelling registration process...");
			}	
		}
		
		else if(choice == "N") {
			System.out.println("Cancelling registration process...");
		}
		
		s.close();
		
	}
	
	public void editCampUI(User user) {
		Scanner s = new Scanner(System.in);
		String choice;
		Date date;
                String message = "";
                
                System.out.println("These are the camps created by you.");
                int count = 1;
                
                ArrayList<Camp> campsCreatedByStaff = controller.getCampsByStaff(user.getId());
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
			choice = s.nextLine();
			num = Integer.parseInt(choice);
		}
		int input = 0;
		switch(num) {
		
		case 1: //Change Camp Name
			System.out.println("What would you like to change Camp Name to?");
			choice = s.nextLine();
			if(controller.changeName(campInfo.getCampId(), choice)) {
				System.out.println("Camp Name change successful!");
			}
			else {
				System.out.println("Error with Camp name change.");
			}
			break;
			
			
		case 2: //Change Start Date
			message = "What would you like to change Start Date to? Example: 10/08/2023";
                        Date startDate = dateValidator(message);
                        if(controller.changeStartDate(campInfo.getCampId(), startDate)) {
                                        System.out.println("Start Date change successful!");
                        }
                        break;
			
		case 3: //Change End Date
			message = "What would you like to change End Date to? Example: 10/08/2023";
                        Date endDate = dateValidator(message);
                        if(controller.changeEndDate(campInfo.getCampId(), endDate)) {
                                        System.out.println("End Date change successful!");
                        }
                        break;
			
		case 4: //Change Registration Close Date
			message = "What would you like to change Registraton Deadline to? Example: 10/08/2023";
                        Date regDate = dateValidator(message);
                        if(controller.changeRegCloseDate(campInfo.getCampId(), regDate)) {
                                        System.out.println("Registraton Deadline change successful!");
                        }
                        break;
			
		case 5: //Change availability
                        input = 0;
                        message = "Enter '1' to change to availability to all NTU, and '2' to change availability to own faculty only.";
                        input = integerValidator(message);

			
			while(input != 1 || input != 2) {//Error msg + re-prompt if user chooses outside options
				System.out.println("Incorrect input. Please try again.");
				input = integerValidator(message);
			}
                        
			
			if(input == 1) {//Change to NTU availability
				if(controller.setFacultyTo(campInfo.getCampId(), Faculty.NTU)) {
					System.out.println("Availability change successful!");
				}
				else {
					System.out.println("Availability change unsuccessful.");
				}
			}
			
			else if (input == 2) {//Change to faculty availability
				Faculty faculty = this.user.getFaculty();
				if(controller.setFacultyTo(campInfo.getCampId(), faculty)){
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
			if(controller.changeLocation(campInfo.getCampId(), choice)) {
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
			
			if(controller.changeCampParticipantSlots(campInfo.getCampId(), slots)){
				System.out.println("Total Slots change successful!");
			}
			else {
				System.out.println("Total Slots change unsuccessful.");
			}
			break;
			
		case 8: //Change Camp Description
			System.out.println("What would you like to change Camp Description to?");
			choice = s.nextLine();
			if(controller.changeDescription(campInfo.getCampId(), choice)) {
				System.out.println("Description change successful!");
			}
			else {
				System.out.println("Description change unsuccessful.");
		 	}
			break;

		case 9: //Change Visibility
			System.out.println("Enter '1' if you would like this camp to be visible to students. Enter '0' if you would like this camp to be hidden.");
			choice = s.nextLine();
			
			while(choice != "1" && choice != "0") {//Error msg + re-prompt if user chooses outside options
				System.out.println("Incorrect input. Please try again.");
				System.out.println("Enter '1' if you would like this camp to be visible to students. Enter '0' if you would like this camp to be hidden.");
				choice = s.nextLine();
			}
			
			if(choice == "1") {//Change to visible
				if(controller.changeVisibility(campInfo.getCampId(), true)){
					System.out.println("Visibility change successful!");
				}
				else {
					System.out.println("Visibility change unsuccessful.");
				}
			}
			else if(choice == "0") {//Change to invisible
				if(controller.changeVisibility(campInfo.getCampId(), false)) {
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
	
	public void viewEnquiry() {
		
	}
	
	public void viewSuggestion() {
		
	}
	
	public void printCampParticipants() {
		
	}
	
	public void studentUI() {//Shows what a regular student would see for the camp - Camp Name, description, remaining slots
		System.out.println("Camp Name: " + campInfo.getName());
		System.out.println("Description: " + campInfo.getDescription());
		System.out.println("Remaining slots: " + campInfo.getRemainingSlots());
	}
	
	public void staffUI() {//Shows what staff would see for the camp (full info)
		this.printCampInformation();
		
	}
	
	public void committeeUI() {//Shows what committee member would see for the camp - (full info also)>
		this.printCampInformation();
	}

	public void withdrawCampUI() {//Handles user input and output for withdrawing from a camp
		Scanner s = new Scanner(System.in);
		String choice;
		
		System.out.println("Are you sure you want to withdraw from " + campInfo.getName() + "?");
		System.out.println("Enter Y to confirm your decision, or N to cancel.");
		choice = s.nextLine().toUpperCase();
		
		while(choice != "Y" && choice != "N") {//Loops error msg + re-prompt on invalid input 
			System.out.println("Incorrect input. Please try again.");
			choice = s.nextLine().toUpperCase();
		}
		
		if(choice == "Y") {
			System.out.println("Withdrawing you from " + campInfo.getName() + "...");
			if(controller.withdraw(user.getId(), campInfo.getCampId())) {
				System.out.println("Withdrawal successful!");
			}
			
			else {
				System.out.println("Issue with withdrawal");
			}
		}
		
		else if(choice == "N") {
			System.out.println("Cancelling withdrawal process...");
		}
		
		s.close();
		
	}

}

