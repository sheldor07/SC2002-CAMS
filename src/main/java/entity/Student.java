package entity;

public class Student extends User{
    
     public Student(int id, String name, String email, String password, Faculty faculty) {
        super(id, name, email, password, faculty);
        
    }
     
     
    
    int points;
    public int getPoints(){
        return this.points;
    }
    public void addPoints(){
        this.points +=1;
    }

}
