/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author weiya
 */
public class Suggestion {
    
    
        private int id;
	private String details;
	private int campId;
	private boolean status;
        private int askedBy;
        private int answeredBy;
        
	public Suggestion(int id, int campId, String details, boolean status, int askedBy, int answeredBy) {
		this.id = id;
                this.campId = campId;
                this.details = details;
                this.status = status;
                this.askedBy = askedBy;
                this.answeredBy = answeredBy;
		
	}
        
        //this constructor will be used to get suggestions that have not been answered.
        public Suggestion(int id, int campId, String details, int askedBy) {
		this.id = id;
                this.campId = campId;
                this.details = details;
                this.askedBy = askedBy;
		
	}
        //this constructor will be used to create suggestions
        public Suggestion(int campId, String details, int askedBy) {
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
    public boolean getStatus() {
        return status;
    }

    /**
     * @param answer the answer to set
     */
    public void setStatus(boolean status) {
        this.status = status;
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
