/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import controller.CampController;
import controller.CampParticipantController;
import java.util.ArrayList;
import java.util.Scanner;

import controller.EnquiryController;
import controller.StaffController;
import controller.StudentController;
import controller.UserController;
import entity.Camp;
import entity.CampCommittee;
import entity.CampParticipant;
import entity.Enquiry;
import entity.Staff;
import entity.Student;
import entity.User;

public class EnquiryUI extends UI{
    
    public EnquiryUI(User user) {//Initialise Enquiry lists + set up starting menu
		//TODO
                super(user);
                enquiryController = new EnquiryController();
                listOfUserEnquiry = new ArrayList<Enquiry>();
                listOfUnprocessedEnquiry = new ArrayList<Enquiry>();
                listOfUnprocessedUserEnquiries = new ArrayList<Enquiry>();
                listOfProcessedEnquiry = new ArrayList<Enquiry>();
                campController = new CampController();
                campParticipantController = new CampParticipantController();
                
		
		
	}

	ArrayList<Enquiry> listOfProcessedEnquiry;
	ArrayList<Enquiry> listOfUserEnquiry;
	ArrayList<Enquiry> listOfUnprocessedEnquiry;
        ArrayList<Enquiry> listOfUnprocessedUserEnquiries;
        CampController campController;
        CampParticipantController campParticipantController;
	
	private EnquiryController enquiryController;
	
	public void printListOfProcessedEnquiry(){//Printing list of processed enquiries i.e replied enquiries
		listOfProcessedEnquiry = enquiryController.getAnsweredEnquiryByStudentId(user.getId());
		int counter;
                StudentController studentController = new StudentController();
                StaffController staffController = new StaffController();
		
                if(!listOfProcessedEnquiry.isEmpty()){
                  System.out.println("List of Processed Enquiries: ");

		for(int i = 0; i < listOfProcessedEnquiry.size(); i++) {
                    Student student = studentController.getStudentById(listOfProcessedEnquiry.get(i).getAskedBy());
                    Staff staff = staffController.getStaffById(listOfProcessedEnquiry.get(i).getAnsweredBy());
			counter = i + 1;
			System.out.println(counter + ") Enquiry by " + student.getName());
			System.out.println("Details : "+listOfProcessedEnquiry.get(i).getDetails());
                        System.out.println("Reply: "+listOfProcessedEnquiry.get(i).getAnswer());
                        System.out.println("Replied by: "+staff.getName());
		}
		
		System.out.println("END OF LIST");
                    System.out.println("");
                }
                else{
                    System.out.println("You have no submitted any enquiries.");
                }
		
		
		
		//TODO RETURN TO ENQUIRY MENU
	}
	
	
        //print list of user by staffId
	public void printListOfUserEnquiry(){//Printing list of enquiries that User asked
            listOfUnprocessedUserEnquiries = new ArrayList<Enquiry>();
            CampController campController = new CampController();
           ArrayList<Camp> camps = new ArrayList();
           
           //is user is staff, we can just get camps by Id. With the camps, we can loop through the
           //enquries list and get the unprocessed enquiries.
                if(user instanceof Staff){
                 camps = campController.getCampsByStaff(user.getId());
                }
                
                //if user is a Camp committee, we have to find which camp they are in charge of.
                //Then we add the camp into a camp list that will be used later to find out 
                //which enquiry have not been answered.
                if(user instanceof CampCommittee){
                    CampParticipantController pC = new CampParticipantController();
                    ArrayList<CampParticipant> participants = pC.getListByStudentId(user.getId());
                    for(CampParticipant p: participants){
                        if(p.getStudentId() == user.getId() && p.isCampCommittee()){
                          Camp camp = campController.getCampById(p.getCampId());
                          camps.add(camp);
                        }
                    }
                }
                //get all enquires and loop through each one to see if the user is in charge of answering the enquiry.
		ArrayList<Enquiry> enquries = enquiryController.getAllEnquiries();
                
                
                for(Enquiry e: enquries){
                    for(Camp c : camps){
                        if(c.getId() == e.getCampId() && e.getAnsweredBy() == -1){
                            listOfUnprocessedUserEnquiries.add(e);
                        }
                    }
                }
                
		int counter;
                StudentController studentController = new StudentController();
		
                if(!listOfUnprocessedUserEnquiries.isEmpty()){
                  System.out.println("List of Unprocessed Enquiries: ");

		for(int i = 0; i < listOfUnprocessedUserEnquiries.size(); i++) {
                    Student s = studentController.getStudentById(listOfUnprocessedUserEnquiries.get(i).getAskedBy());
			counter = i + 1;
			System.out.println(counter + ") Enquiry by " + s.getName());
			System.out.println("Details : "+listOfUnprocessedUserEnquiries.get(i).getDetails());
		}
		
		System.out.println("END OF LIST");
                    System.out.println("");
                }
                else{
                    System.out.println("There is no enquiries pending.");
                }
		//TODO RETURN TO ENQUIRY MENU
	}
	
	public void printListOfUnprocessedEnquiry() {
                listOfUnprocessedEnquiry = enquiryController.getUnansweredEnquiriesByStudentId(user.getId());
		int counter;
                StudentController sC = new StudentController();
		
                if(!listOfUnprocessedEnquiry.isEmpty()){
                  System.out.println("List of Unprocessed Enquiries: ");

		for(int i = 0; i < listOfUnprocessedEnquiry.size(); i++) {
                    Student s = sC.getStudentById(listOfUnprocessedEnquiry.get(i).getAskedBy());
			counter = i + 1;
			System.out.println(counter + ") Enquiry by " + s.getName());
			System.out.println("Details : "+listOfUnprocessedEnquiry.get(i).getDetails());
		}
		
		System.out.println("END OF LIST");
                    System.out.println("");
                }
                else{
                    System.out.println("You have no submitted any enquiries.");
                }
		
		//TODO RETURN TO ENQUIRY MENU
	}
        
	
	public void studentUI() {//Menu letting them view their enquiries, and edit/delete those that are not processed
		//TODO
		Scanner s = new Scanner(System.in);
		String choice; 
		
		System.out.println("Welcome to Enquiry Menu. Please select your desired option by entering the respective number.");
		System.out.println("1) View Enquiries");
		System.out.println("2) Edit Enquiries");
		System.out.println("3) Delete Enquiries");
		System.out.println("4) Exit");
		
		choice = s.nextLine();
		
		while(choice != "1" && choice != "2" && choice != "3" && choice != "4") {
			System.out.println("Invalid input. Please try again.");
			System.out.println("Welcome to Enquiry Menu. Please select your desired option by entering the respective number.");
			System.out.println("1) View Enquiries");
			System.out.println("2) Edit Enquiries");
			System.out.println("3) Delete Enquiries");
			System.out.println("4) Exit");
			
			choice = s.nextLine();	
		}
		
		int num = Integer.parseInt(choice);
		
		switch(num) {
		
		case 1:
			printListOfUnprocessedEnquiry();
			break;
			
		case 2:
			editEnquiryUI();
			break;
			
		case 3:
			deleteEnquiryUI();
			break;
			
		case 4:
			//Go back to main menu but idk how to implement that?
			break;
			
		
		}
	}
        
        public void submitEnquiryUI(){

            // we are reusing one of CampUI method to show available camp for a particular student.
                CampUI campUI = new CampUI(user);
                 ArrayList<Camp> camps = campController.getCampsByFaculty(user);

                ArrayList<Enquiry> enquiries = enquiryController.getAllEnquiries();
                boolean valid = false;
                Camp camp = null;
                System.out.println("These are the camps that you can submit enquiry for:");
                campUI.showAvailableCampsForStudent(user);

                do{ 
                    System.out.println("Please enter the camp you wish to submit enquiry for: ");
                    String campInput = sc.nextLine();
                    
                    for(Camp c: camps){
                        if(campInput.equals(c.getName()))
                        {   
                            camp = c;
                            valid = true;
                            break;
                        }
                      
                    }
                    if(!valid)
                        System.out.println("Please select a valid camp name.");
                }while(!valid);
                
                System.out.println("");
               System.out.println("Please enter the details of your enquiry: ");
               String enquiryDetail = sc.nextLine();

               Enquiry enquiry = new Enquiry(camp.getId(),enquiryDetail,user.getId());
                
            
            boolean result = enquiryController.submitEnquiry(enquiry);
            
            if(result)
                System.out.println("We have submitted the enquiry");
            else
                System.out.println("There is an issue with submitting the enquiry.");
        }
	
	public void staffCommitteeUI() {//Menu letting Staff/tagged committee members view and reply to enquiries that they created
		//TODO
	}
	
	public void deleteEnquiryUI() {//Process allowing Student to delete their enquiry
                printListOfUnprocessedEnquiry();
                
                if(!listOfUnprocessedEnquiry.isEmpty()){
                    
                System.out.println("Which enquiry would you like to delete?: ");
                int selection = integerValidator("Select the enquiry number: ");
                
                while(selection > listOfUnprocessedEnquiry.size() && selection < 0){
                    System.out.println("Please select a valid enquiry number");
                    selection = integerValidator("Select the enquiry number: ");
                }    
                
                Enquiry selectedEnquiry = listOfUnprocessedEnquiry.get(selection-1);
                
		System.out.println("Please enter the number next to the enquiry you would like to delete. Alternatively, enter '0' to cancel.");
		
		if(selection != 0) {//Enquiry picked
			String message = "Are you sure you want to delete Enquiry " + selection + "? Enter 'Y' to confirm, or 'N' to cancel.";
			String choice = stringValidator(message);
			
			while(!choice.equals("Y") && !choice.equals("N")) {
				System.out.println("Incorect input. Please try again.");
				choice = stringValidator(message);
			}
			
			if(choice.equals("Y")) {//Delete enquiry
				System.out.println("Deleting enquiry...");
				
				if(enquiryController.delete(selectedEnquiry)) {
					System.out.println("Enquiry deleted successfully!");
				}
				else {
					System.out.println("Enquiry deletion unsuccessful.");
				}
			}
			
			else if(choice.equals("N")) {//Cancel process
				System.out.println("Cancelling deletion process...");
			}
		}
		else if(selection == 0) {//User cancels
			System.out.println("Cancelling deletion process...");
		}
		
		//TODO RETURN TO ENQUIRY MENU
	}
        }
	
	public void editEnquiryUI() {//Process allowing Student to edit their enquiry
            
                printListOfUnprocessedEnquiry();
                
                if(!listOfUnprocessedEnquiry.isEmpty()){
                    
                System.out.println("Which enquiry would you like to edit?: ");
                int selection = integerValidator("Select the enquiry number: ");
                
                while(selection > listOfUnprocessedEnquiry.size() && selection < 0){
                    System.out.println("Please select a valid enquiry number");
                    selection = integerValidator("Select the enquiry number: ");
                }    
                
                Enquiry selectedEnquiry = listOfUnprocessedEnquiry.get(selection-1);
        
		if(selection != 0) {//Enquiry picked
			String message = "Please input your detail edits: ";
			String edittedDetails = stringValidator(message);
			
			message = "Are you sure you would like to replace your old enquiry with this? Enter 'Y' to confirm, and 'N' to cancel.";
			String choice = stringValidator(message);
			
			while(!choice.equals("Y") && !choice.equals("N")) {
				System.out.println("Incorect input. Please try again.");
				choice = stringValidator(message);
			}
			
			if(choice.equals("Y")) {//Edit process confirmed
				System.out.println("Changing your enquiry...");
				selectedEnquiry.setDetails(edittedDetails);
				
				if(enquiryController.editEnquiryDetails(selectedEnquiry)){
					System.out.println("Enquiry change successful!");
				}
				else {
					System.out.println("Enquiry change unsuccessful.");
				}
			}
			else if(choice.equals("N")) {//Edit process cancelled
				System.out.println("Cancelling edit process...");
			}
			
		
		}
		else if(selection == 0) {//User cancels
			System.out.println("Cancelling edit process...");
		}
		//TODO RETURN TO ENQUIRY MENU
	}
                else{
                    System.out.println("You have no unprocessed enquiries");
                }
                
                }
	
	public void replyEnquiryUI() {//Process allowing Staff/Committee member to reply to enquiry 
                printListOfUserEnquiry();
                
                if(listOfUnprocessedUserEnquiries.size()!= 0){
                System.out.println("Which enquiry would you like to reply?: ");
                int selection = integerValidator("Select the enquiry number: ");
                
                while(selection > listOfUnprocessedUserEnquiries.size() && selection < 0){
                    System.out.println("Please select a valid enquiry number");
                    selection = integerValidator("Select the enquiry number: ");
                }    
                
                Enquiry selectedEnquiry = listOfUnprocessedUserEnquiries.get(selection-1);
        
		if(selection != 0) {//Enquiry picked
			String message = "Please input your reply: ";
			String reply = stringValidator(message);
			
				System.out.println("Replying student...");
				selectedEnquiry.setAnswer(reply);
				selectedEnquiry.setAnsweredBy(user.getId());
                                
				if(enquiryController.editEnquiryAnswer(selectedEnquiry)){
					System.out.println("Reply succesfully sent!");
                                        
                                        StudentController controller = new StudentController();
                                        controller.addStudentPoint(user.getId());
				}
				else {
					System.out.println("Reply is unsuccessful.");
				}
			
			
		
		}
		else if(selection == 0) {//User cancels
			System.out.println("Cancelling edit process...");
		}
	}
        }
	
}
