/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import entity.Faculty;
import entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * The base user interface class that provides common utility methods for input validation.
 * Other user interface classes can inherit from this class to reuse these validation methods.
 */
public class UI {
    User user;
    Scanner sc;
    SimpleDateFormat dateFormat;
    /**
     * Constructs a new instance of the UI class.
     *
     * @param user The user for whom the user interface is created.
     */
    public UI(User user){
        this.user = user;
        sc = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
    /**
     * Validates and retrieves an integer input from the user.
     *
     * @param printMessage The message to display to the user when requesting input.
     * @return The validated integer input.
     */
    public int integerValidator(String printMessage){
        int someInt = 0;
        boolean valid = false;
        do{
            try{
                System.out.println(printMessage);
                someInt = Integer.parseInt(sc.nextLine());
                valid = true;
            }catch(Exception e){
                System.out.println("Please Enter an integer");

            }
        }while(!valid);

        return someInt;
    }
    /**
     * Validates and retrieves a string input from the user.
     *
     * @param printMessage The message to display to the user when requesting input.
     * @return The validated string input.
     */
    public String stringValidator(String printMessage){
        String someString = "";
        boolean valid = false;
        do{
            try{

                System.out.println(printMessage);
                someString = sc.nextLine();
                if(!someString.isBlank() && !someString.isEmpty())
                    valid = true;
                else
                    System.out.println("Please enter a proper input");
            }catch(Exception e){
                System.out.println("Please enter a proper input");

            }
        }while(!valid);

        return someString;
    }
    /**
     * Validates and retrieves a date input from the user.
     *
     * @param message The message to display to the user when requesting input.
     * @return The validated date input.
     */
    public Date dateValidator(String message){
        Date someDate = null;
        boolean valid = false;
        do{
            try{
                System.out.println(message);
                String dateInput = sc.nextLine();
                someDate = dateFormat.parse(dateInput);
                valid = true;
            }catch(ParseException e){
                System.out.println("Please enter a valid date.");
            }
        }while(!valid);
        return someDate;
    }
    /**
     * Validates and retrieves a faculty input from the user.
     *
     * @param message The message to display to the user when requesting input.
     * @return The validated faculty input.
     */

    public Faculty facultyValidator(String message){
        Faculty faculty = null;
        boolean valid = false;
        do{
            try{
                System.out.println(message);
                String facultyString = sc.nextLine();
                faculty = Faculty.valueOf(facultyString);
                valid = true;
            }catch(Exception e){
                System.out.println("Enter a valid user group");
            }
        }while(!valid);
        return faculty;
    }
    /**
     * Validates and retrieves a boolean input from the user (0 for false, 1 for true).
     *
     * @param message The message to display to the user when requesting input.
     * @return The validated boolean input.
     */
    public boolean booleanValidator(String message){
        boolean visible = false;
        boolean valid = false;
        do{
            try{
                System.out.println(message);
                int visibleInt = Integer.parseInt(sc.nextLine());
                if(visibleInt == 1 || visibleInt == 0){
                    if(visibleInt == 1)
                        visible = true;
                    else
                        visible = false;
                    valid = true;
                }
                else
                    System.out.println("Please enter a 1 or 0");
            }catch(Exception e){
                System.out.println("Please Enter an integer");
            }

        }while(!valid);

        return visible;
    }


}
