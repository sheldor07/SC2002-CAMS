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
	private String details;
	private int campId;
	private String answer;
        private int askedBy;
        private int answeredBy;
        
	public Enquiry(int id, int campId, String details, String answer, int askedBy, int answeredBy) {
		this.id = id;
                this.campId = campId;
                this.details = details;
                this.answer = answer;
                this.askedBy = askedBy;
                this.answeredBy = answeredBy;
		
	}
        
        //this constructor will be used to get enquiries that have not been answered.
        public Enquiry(int id, int campId, String details, int askedBy) {
		this.id = id;
                this.campId = campId;
                this.details = details;
                this.askedBy = askedBy;
		
	}
        //this constructor will be used to create enquiries
        public Enquiry(int campId, String details, int askedBy) {
                this.campId = campId;
                this.details = details;
                this.askedBy = askedBy;
		
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
        
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the campId
     */
    public int getCampId() {
        return campId;
    }

    /**
     * @param campId the campId to set
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the askedBy
     */
    public int getAskedBy() {
        return askedBy;
    }

    /**
     * @param askedBy the askedBy to set
     */
    public void setAskedBy(int askedBy) {
        this.askedBy = askedBy;
    }

    /**
     * @return the answeredBy
     */
    public int getAnsweredBy() {
        return answeredBy;
    }

    /**
     * @param answeredBy the answeredBy to set
     */
    public void setAnsweredBy(int answeredBy) {
        this.answeredBy = answeredBy;
    }
    

	
}

