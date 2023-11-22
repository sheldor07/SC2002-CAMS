/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import java.util.ArrayList;
import java.util.Scanner;

import controller.EnquiryController;
import entity.Enquiry;
import entity.User;

public class EnquiryUI extends MainUI{
    
    public EnquiryUI(User user, int campId, String name) {//Initialise Enquiry lists + set up starting menu
		//TODO
                super(user);
		this.campId = campId;
		this.name = name;
                controller = new EnquiryController();
                listOfProcessEnquiry = new ArrayList<Enquiry>();
                listOfUserEnquiry = new ArrayList<Enquiry>();
                listOfUnprocessEnquiry = new ArrayList<Enquiry>();
		listOfUserEnquiry = controller.getEnquiryByStudent();
		listOfUnprocessEnquiry = controller.getPendingEnquiry(campId);
		listOfProcessEnquiry = controller.getProcessEnquiry(campId);
		
		
	}

	ArrayList<Enquiry> listOfProcessEnquiry;
	ArrayList<Enquiry> listOfUserEnquiry;
	ArrayList<Enquiry> listOfUnprocessEnquiry;
	
	private EnquiryController controller;
	
	int campId;
	String name;
	
	public void printListOfProcessEnquiry(){//Printing list of processed enquiries i.e replied enquiries
		System.out.println("List of Processed Enquiries for Camp " + name + " :");
		int counter;
		
		for(int i = 0; i < listOfProcessEnquiry.size(); i++) {
			counter = i + 1;
			System.out.println(counter + ") Enquiry by " + listOfProcessEnquiry.get(i).getAskedBy() + ":");
			System.out.println(listOfProcessEnquiry.get(i).getDetails());
			
			System.out.println("Answered by " + listOfProcessEnquiry.get(i).getAnsweredBy() + ":");
			System.out.println(listOfProcessEnquiry.get(i).getAnswer());
			
		}
		
		System.out.println("END OF LIST");
		
		
		//TODO RETURN TO ENQUIRY MENU
	}
	
	
	public void printListOfUserEnquiry(){//Printing list of enquiries that User asked
		System.out.println("List of Enquiries made by " + user.getEmail() + ":");
		int num;
		
		for(int i = 0; i < listOfUserEnquiry.size(); i++) {
			num = i + 1;
			System.out.println(num + ") Enquiry for " + listOfUserEnquiry.get(i).getCamp().getName());
			System.out.println(listOfUserEnquiry.get(i).getDetails());
			
			if(listOfUserEnquiry.get(i).getAnswer()== null) {
				System.out.println("Status: PENDING");
			}
			else{
				System.out.println(listOfUserEnquiry.get(i).getAnswer());
				System.out.println("Answered by " + listOfUserEnquiry.get(i).getAnsweredBy());
				System.out.println("Status: PROCESSED");
			}
		}
		
		System.out.println("END OF LIST");
		
		//TODO RETURN TO ENQUIRY MENU
	}
	
	public void printListOfUnprocessEnquiry() {
		System.out.println("List of Unprocessed Enquiries for " + name + ":");
		int counter;
		
		for(int i = 0; i < listOfUnprocessEnquiry.size(); i++) {
			counter = i + 1;
			System.out.println(counter + ") Enquiry by " + listOfUnprocessEnquiry.get(i).getAskedBy());
			System.out.println(listOfUnprocessEnquiry.get(i).getDetails());
		}
		
		System.out.println("END OF LIST");
		
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
			printListOfUnprocessEnquiry();
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
	
	public void staffCommitteeUI() {//Menu letting Staff/tagged committee members view and reply to enquiries that they created
		//TODO
	}
	
	public void deleteEnquiryUI() {//Process allowing Student to delete their enquiry
		Scanner s = new Scanner(System.in);
		String choice;
		int num; 
		
		printListOfUserEnquiry();
		System.out.println("Please enter the number next to the enquiry you would like to delete. Alternatively, enter '0' to cancel.");
		
		choice = s.nextLine();
		num = Integer.parseInt(choice);
		
		while(num < 0 || num >= listOfUserEnquiry.size() || listOfUserEnquiry.get(num - 1).getAnswer()!= null) {//error msg + re-prompt if index given out of range
			System.out.println("Input is either out of range or enquiry is already processed and undeletable. Please try again.");
			System.out.println("Please enter the number next to the enquiry you would like to delete. Alternatively, enter '0' to cancel.");
			choice = s.nextLine();
			num = Integer.parseInt(choice);
		}
		
		if(num != 0) {//Enquiry picked
			System.out.println("Are you sure you want to delete Enquiry " + num + "? Enter 'Y' to confirm, or 'N' to cancel.");
			choice = s.nextLine().toUpperCase();
			
			while(choice != "Y" && choice != "N") {
				System.out.println("Incorrect input. please try again.");
				System.out.println("Are you sure you want to delete Enquiry " + num + "? Enter 'Y' to confirm, or 'N' to cancel.");
				choice = s.nextLine().toUpperCase();
			}
			
			if(choice == "Y") {//Deleting Enquiry
				System.out.println("Deleting enquiry...");
				num = num - 1; //Converting interface position to index of Enquiry
				
				if(controller.delete(this.user.getId(), listOfUserEnquiry.get(num).getId())) {
					System.out.println("Enquiry deleted successfully!");
				}
				else {
					System.out.println("Enquiry deletion unsuccessful.");
				}
			}
			
			else if(choice == "N") {//Cancel process
				System.out.println("Cancelling deletion process...");
			}
		}
		else if(num == 0) {//User cancels
			System.out.println("Cancelling deletion process...");
		}
		
		//TODO RETURN TO ENQUIRY MENU
	}
	
	public void editEnquiryUI() {//Process allowing Student to edit their enquiry
		Scanner s = new Scanner(System.in);
		String choice;
		int num; 
		
		printListOfUserEnquiry();
		System.out.println("Please enter the number next to the enquiry you would like to delete. Alternatively, enter '0' to cancel.");
		
		choice = s.nextLine();
		num = Integer.parseInt(choice);
		
		while(num < 0 || num >= listOfUserEnquiry.size() || listOfUserEnquiry.get(num - 1).getAnswer()!= null) {
			System.out.println("Input is either out of range or enquiry is processed and uneditable. Please try again.");
			System.out.println("Please enter the number next to the enquiry you would like to delete. Alternatively, enter '0' to cancel.");
			choice = s.nextLine();
			num = Integer.parseInt(choice);
		}
		
		if(num != 0) {//Enquiry picked
			System.out.println("Please enter in its entirety your new enquiry for enquiry no. " + num + ") to update.");
			String edit = s.nextLine();
			
			
			System.out.println("Are you sure you would like to replace your old enquiry with this? Enter 'Y' to confirm, and 'N' to cancel.");
			choice = s.nextLine().toUpperCase();
			
			while(choice != "Y" && choice != "N") {
				System.out.println("Incorect input. Please try again.");
				choice = s.nextLine().toUpperCase();
			}
			
			if(choice == "Y") {//Edit process confirmed
				System.out.println("Changing your enquiry...");
				num = num - 1; //Converting to index position
				
				if(controller.edit(this.user.getId(), listOfUserEnquiry.get(num).getId(), edit)){
					System.out.println("Enquiry change successful!");
				}
				else {
					System.out.println("Enquiry change unsuccessful.");
				}
			}
			else if(choice == "N") {//Edit process cancelled
				System.out.println("Cancelling edit process...");
			}
			
		
		}
		else if(num == 0) {//User cancels
			System.out.println("Cancelling edit process...");
		}
		//TODO RETURN TO ENQUIRY MENU
	}
	
	public void replyEnquiryUI() {//Process allowing Staff/Committee member to reply to enquiry 
		
		Scanner s = new Scanner(System.in);
		String choice;
		int num;
		
		printListOfUnprocessEnquiry();
		System.out.println("Please enter the number next to the enquiry you would like to reply to. Alternatively, enter '0' to cancel.");
		
		choice = s.nextLine();
		num = Integer.parseInt(choice);
		
		while(num < 0 || num >= listOfUnprocessEnquiry.size()) {
			System.out.println("Input out of range. Please try again.");
			System.out.println("Please enter the number next to the enquiry you would like to reply to. Alternatively, enter '0' to cancel.");
			choice = s.nextLine();
			num = Integer.parseInt(choice);
		}
		
		if(num != 0) {//Enquiry picked
			System.out.println("Please enter your reply for enquiry no. " + num + ").");
			String reply = s.nextLine();
			
			System.out.println("Please confirm your reply by entering 'Y', or enter 'N' to cancel. ");
			choice = s.nextLine().toUpperCase();
			
			while(choice != "Y" && choice != "N") {//Error msg + re-prompt if user doesn't pick an option
				System.out.println("Incorrect input. please try again.");
				choice = s.nextLine().toUpperCase();
			}
			
			if(choice == "Y") {//Proceed with reply process
				System.out.println("Processing reply...");
				num = num - 1;
				if(controller.reply(this.user.getId(), listOfUnprocessEnquiry.get(num).getId(), reply)) {
					System.out.println("Reply successful!");
				}
				else {
					System.out.println("Reply unsuccessful.");
				}
			}
			else if(choice == "N") {//Cancel reply process
				System.out.println("Cancelling reply process...");
			}
		}
		
		else if(num == 0) {//User cancels
			System.out.println("Cancelling reply process...");
		}
		
		//TODO RETURN TO ENQUIRY MENU
	}
	
	
}
