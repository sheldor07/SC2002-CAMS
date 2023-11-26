/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 * The `Suggestion` class represents suggestions made by users regarding a specific camp.
 * Each suggestion includes details, a status indicating whether it has been answered or not,
 * and information about the user who made the suggestion and the camp to which it is related.
 *
 * Suggestions can be created, retrieved, and tracked within the system.
 * Users can submit suggestions for improvement or feedback on camp-related matters.
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
    /**
     * Constructs a new `Suggestion` object with the specified attributes.
     *
     * @param id The unique identifier of the suggestion.
     * @param campId The ID of the camp to which the suggestion is related.
     * @param details The details or content of the suggestion.
     * @param status The status indicating whether the suggestion has been answered (true) or not (false).
     * @param askedBy The ID of the user who made the suggestion.
     * @param answeredBy The ID of the user who answered the suggestion (if applicable).
     */
    public Suggestion(int id, int campId, String details, boolean status, int askedBy, int answeredBy) {
        this.id = id;
        this.campId = campId;
        this.details = details;
        this.status = status;
        this.askedBy = askedBy;
        this.answeredBy = answeredBy;

    }

    /**
     * Constructs a new `Suggestion` object with the specified attributes, excluding the status and answeredBy fields.
     * This constructor is used to get suggestions that have not been answered yet.
     *
     * @param id The unique identifier of the suggestion.
     * @param campId The ID of the camp to which the suggestion is related.
     * @param details The details or content of the suggestion.
     * @param askedBy The ID of the user who made the suggestion.
     */
    public Suggestion(int id, int campId, String details, int askedBy) {
        this.id = id;
        this.campId = campId;
        this.details = details;
        this.askedBy = askedBy;

    }
    /**
     * Constructs a new `Suggestion` object with the specified attributes, excluding the status and answeredBy fields.
     * This constructor is used to create new suggestions.
     *
     * @param campId The ID of the camp to which the suggestion is related.
     * @param details The details or content of the suggestion.
     * @param askedBy The ID of the user who made the suggestion.
     */
    public Suggestion(int campId, String details, int askedBy) {
        this.campId = campId;
        this.details = details;
        this.askedBy = askedBy;

    }

    /**
     * Retrieves the unique identifier of the suggestion.
     *
     * @return The suggestion's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the unique identifier of the suggestion.
     *
     * @param id The suggestion's ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the details or content of the suggestion.
     *
     * @return The suggestion's details.
     */
    public String getDetails() {
        return details;
    }
    /**
     * Sets the details or content of the suggestion.
     *
     * @param details The suggestion's details to set.
     */
    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * Retrieves the ID of the camp to which the suggestion is related.
     *
     * @return The camp's ID.
     */
    public int getCampId() {
        return campId;
    }
    /**
     * Sets the ID of the camp to which the suggestion is related.
     *
     * @param campId The camp's ID to set.
     */
    public void setCampId(int campId) {
        this.campId = campId;
    }
    /**
     * Retrieves the status of the suggestion, indicating whether it has been answered.
     *
     * @return `true` if the suggestion has been answered; `false` otherwise.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the status of the suggestion, indicating whether it has been answered.
     *
     * @param status The suggestion's status to set (`true` for answered, `false` for unanswered).
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Retrieves the ID of the user who made the suggestion.
     *
     * @return The user's ID who made the suggestion.
     */
    public int getAskedBy() {
        return askedBy;
    }

    /**
     * Sets the ID of the user who made the suggestion.
     *
     * @param askedBy The user's ID who made the suggestion to set.
     */
    public void setAskedBy(int askedBy) {
        this.askedBy = askedBy;
    }

    /**
     * Retrieves the ID of the user who answered the suggestion (if applicable).
     *
     * @return The user's ID who answered the suggestion.
     */
    public int getAnsweredBy() {
        return answeredBy;
    }

    /**
     * Sets the ID of the user who answered the suggestion (if applicable).
     *
     * @param answeredBy The user's ID who answered the suggestion to set.
     */

    public void setAnsweredBy(int answeredBy) {
        this.answeredBy = answeredBy;
    }

}
