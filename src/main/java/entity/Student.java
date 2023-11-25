package entity;

public class Student extends User{
    
     public Student(int id, String name, String email, String password, Faculty faculty) {
        super(id, name, email, password, faculty);
        this.points = 0;
        
    }
     
     public Student(int id, String name, String email, String password, Faculty faculty, int points) {
        super(id, name, email, password, faculty);
        this.points = points;
        
    }
     
     
    
    int points;
    public int getPoints(){
        return this.points;
    }
    public void addPoints(){
        this.points +=1;
    }

}
