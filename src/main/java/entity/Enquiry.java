/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
/**
 * The `Enquiry` class represents an inquiry related to a camp, including its unique identifier, camp ID,
 * details, answer (if available), and information about the user who asked and answered the inquiry.
 * This class is used to store and retrieve information about camp-related inquiries.
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
    /**
     * Constructs a new `Enquiry` object with the specified attributes.
     *
     * @param id The unique identifier of the inquiry.
     * @param campId The ID of the camp to which the inquiry is related.
     * @param details The details of the inquiry.
     * @param answer The answer to the inquiry (if available).
     * @param askedBy The ID of the user who asked the inquiry.
     * @param answeredBy The ID of the user who answered the inquiry (if answered).
     */
    public Enquiry(int id, int campId, String details, String answer, int askedBy, int answeredBy) {
        this.id = id;
        this.campId = campId;
        this.details = details;
        this.answer = answer;
        this.askedBy = askedBy;
        this.answeredBy = answeredBy;

    }

    /**
     * Constructs a new `Enquiry` object with the specified attributes, excluding an answer.
     *
     * @param id The unique identifier of the inquiry.
     * @param campId The ID of the camp to which the inquiry is related.
     * @param details The details of the inquiry.
     * @param askedBy The ID of the user who asked the inquiry.
     */
    public Enquiry(int id, int campId, String details, int askedBy) {
        this.id = id;
        this.campId = campId;
        this.details = details;
        this.askedBy = askedBy;

    }
    /**
     * Constructs a new `Enquiry` object without a unique identifier and an answer.
     *
     * @param campId The ID of the camp to which the inquiry is related.
     * @param details The details of the inquiry.
     * @param askedBy The ID of the user who asked the inquiry.
     */
    public Enquiry(int campId, String details, int askedBy) {
        this.campId = campId;
        this.details = details;
        this.askedBy = askedBy;

    }

    /**
     * Gets the unique identifier of the inquiry.
     *
     * @return The unique identifier of the inquiry.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the inquiry.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the details of the inquiry.
     *
     * @return The details of the inquiry.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details of the inquiry.
     *
     * @param details The details to set.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the ID of the camp to which the inquiry is related.
     *
     * @return The camp ID.
     */
    public int getCampId() {
        return campId;
    }

    /**
     * Sets the ID of the camp to which the inquiry is related.
     *
     * @param campId The camp ID to set.
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }

    /**
     * Gets the answer to the inquiry (if available).
     *
     * @return The answer to the inquiry.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer to the inquiry.
     *
     * @param answer The answer to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Gets the ID of the user who asked the inquiry.
     *
     * @return The ID of the user who asked the inquiry.
     */
    public int getAskedBy() {
        return askedBy;
    }

    /**
     * Sets the ID of the user who asked the inquiry.
     *
     * @param askedBy The ID of the user who asked the inquiry.
     */
    public void setAskedBy(int askedBy) {
        this.askedBy = askedBy;
    }

    /**
     * Gets the ID of the user who answered the inquiry (if answered).
     *
     * @return The ID of the user who answered the inquiry.
     */
    public int getAnsweredBy() {
        return answeredBy;
    }

    /**
     * Sets the ID of the user who answered the inquiry (if answered).
     *
     * @param answeredBy The ID of the user who answered the inquiry.
     */
    public void setAnsweredBy(int answeredBy) {
        this.answeredBy = answeredBy;
    }
}

