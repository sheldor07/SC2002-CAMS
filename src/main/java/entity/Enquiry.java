/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author weiya
 */
public class Enquiry {
    
        private int id;
	private Student student;
	private String details;
	private Camp camp;
	private String answer;
        private String askedBy;
        private String answeredBy;
        
	public Enquiry(Student s,String d, Camp c) {
		this.student = s;
		this.details = d;
		this.camp = c;
		this.answer = "";
		
	}
	
	public String getDetails() {
		return details;
	}

	public Camp getCamp() {
		return camp;
	}

	public Student getStudent() {
		return student;
	}
	
	public void editDetails(String edit) {
		this.details = edit;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
        
        
    /**
     * @return the askedBy
     */
    public String getAskedBy() {
        return askedBy;
    }

    /**
     * @param askedBy the askedBy to set
     */
    public void setAskedBy(String askedBy) {
        this.askedBy = askedBy;
    }
        
            /**
     * @return the answeredBy
     */
    public String getAnsweredBy() {
        return answeredBy;
    }

    /**
     * @param answeredBy the answeredBy to set
     */
    public void setAnsweredBy(String answeredBy) {
        this.answeredBy = answeredBy;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

	
}

