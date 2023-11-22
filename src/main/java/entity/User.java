package entity;

public class User implements Identity{

    private int userId;
    private String password = "password";
    private Faculty faculty;
    private String email;
    private String name;
    
    public User(int id, String name, String email, String password, Faculty faculty){
        
    this.email = email;
    this.name = name;
    this.userId = id;
    this.password = password;
    this.faculty = faculty;
    }
    
    @Override
    public int getId(){
        return this.userId;
    }
    /*Getter and Setter Methods */
    public Faculty getFaculty(){
        return this.faculty;
    }
    public String getEmail(){
        return this.email;
    }    public void setEmail(String email){
        this.email = email;
    }

    /* Editing Email and Password */

    public boolean changeEmail(String newEmail){
        // checks if it's the same email and updates otherwise
        if(this.email.equals(newEmail)){
            return false;
        }
        this.email = newEmail;
        return true;

    }
    public boolean changePassword(String newPassword){
        // checks if it's the same password and updates otherwise
        if(checkPassword(newPassword)){
            return false;
        }
        this.password = newPassword;
        return true;

    }
    public boolean checkPassword(String password){
        /* checks if it's the same password*/
        return this.getPassword().equals(password);
    }
    
     /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    


}
