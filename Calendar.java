import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a calendar containing events, categories, and holidays.
 * 
 * @author Nicolas Marchese (nmarchese)
 * @version 09.24.2026
 */
public class Calendar {

    private Map<String, String> categoryDescriptions; // Stores a description
                                                      // for each category.
    private ArrayList<Event> events; // Stores all events in the calendar.

    /**
     * Constructs an empty Calendar with no events or category descriptions.
     */
    public Calendar() {
        categoryDescriptions = new HashMap<String, String>();
        events = new ArrayList<Event>();
    }


    /**
     * Attempts to add an event to the calendar.
     *
     * @param e
     *            the event to add
     * @return a message indicating whether the event was added successfully
     */
    public String addEvent(Event e) {
        LocalDateTime now = LocalDateTime.now();
        // Prevents events from being scheduled in the past.
        if (DateTimeUtil.isPast(e.getDateTime(), now)) {
            return "Event cannot be added because it is in the past.";
        }
        // Checks the new event against every existing event for overlap.
        for (Event existingEvent : events) {
            if (DateTimeUtil.overlaps(e.getDateTime(), e.getDuration(),
                existingEvent.getDateTime(), existingEvent.getDuration())) {
                return "Event conflicts with " + existingEvent.getName() + ".";
            }
        }
        events.add(e);
        return "Event added successfully.";
    }


    /**
     * Replaces an existing event with an updated version.
     *
     * @param index
     *            the index of the event to edit
     * @param updated
     *            the updated event
     * @return true if the event was edited successfully, false otherwise
     */
    public boolean editEvent(int index, Event updated) {
        return false;
    }


    /**
     * Removes an event from the calendar.
     * Holidays cannot be deleted.
     *
     * @param index
     *            the index of the event to delete
     * @return true if the event was deleted successfully, false otherwise
     */
    public boolean deleteEvent(int index) {
        return false;
    }


    /**
     * Returns all events in the calendar in order.
     *
     * @return a list containing all events
     */
    public List<Event> viewAll() {
        return null;
    }


    /**
     * Returns all events occurring on a given day.
     *
     * @param date
     *            the date to view
     * @return a list of events occurring on that day
     */
    public List<Event> viewByDate(LocalDateTime date) {
        return null;
    }


    /**
     * Returns all events occurring within a seven-day window.
     *
     * @param weekStart
     *            the starting date and time of the week
     * @return a list of events occurring during the seven-day window
     */
    public List<Event> viewByWeek(LocalDateTime weekStart) {
        return null;
    }


    /**
     * Returns the events sorted by priority.
     *
     * @param descending
     *            true to sort from highest to lowest priority,
     *            false to sort from lowest to highest priority
     * @return a list of events sorted by priority
     */
    public List<Event> sortByPriority(boolean descending) {
        return null;
    }


    /**
     * Returns all events belonging to the specified category.
     *
     * @param category
     *            the category to filter by
     * @return a list of events in the specified category
     */
    public List<Event> filterByCategory(String category) {
        return null;
    }


    /**
     * Stores a description for a category.
     *
     * @param category
     *            the category name
     * @param description
     *            the description of the category
     */
    public void setCategoryDescription(String category, String description) {
    }


    /**
     * Returns the stored description for a category.
     *
     * @param category
     *            the category name
     * @return the description associated with the category
     */
    public String getCategoryDescription(String category) {
        return null;
    }


    /**
     * Returns events that are currently due.
     *
     * @param now
     *            the current date and time
     * @return a list of events that are due
     */
    public List<Event> checkDueNow(LocalDateTime now) {
        return null;
    }


    /**
     * Loads the standard U.S. holidays into the calendar.
     */
    public void loadHolidays() {
        return;
    }

}
