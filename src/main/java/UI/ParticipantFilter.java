package UI;

/**
 * An enumeration representing different filters or categories for participants.
 * Use these filter options to categorize and retrieve participants in your application.
 */
public enum ParticipantFilter {
    /**
     * Represents a filter option to include all participants, regardless of their specific category.
     */
    ALL,

    /**
     * Represents a filter option to include only attendees or participants who are not part of the camp committee.
     */
    ATTENDEE,

    /**
     * Represents a filter option to include only participants who are part of the camp committee.
     */
    CAMP_COMMITTEE
}

