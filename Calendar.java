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
     * Replaces an existing event with an updated event.
     *
     * @param index
     *            the index of the event to edit
     * @param updated
     *            the updated event
     * @return true if the event was updated, false if the index is invalid
     */
    public boolean editEvent(int index, Event updated) {
        // Checks if the index is outside the valid range.
        if (index < 0 || index >= events.size()) {
            return false;
        }
        // Replaces the old event with the updated event.
        events.set(index, updated);
        return true;
    }


    /**
     * Removes an event from the calendar.
     *
     * @param index
     *            the index of the event to delete
     * @return true if the event was deleted, false otherwise
     */
    public boolean deleteEvent(int index) {
        // Checks if the index is invalid.
        if (index < 0 || index >= events.size()) {
            return false;
        }
        // Prevents holidays from being deleted.
        if (events.get(index) instanceof Holiday) {
            return false;
        }
        // Removes the event.
        events.remove(index);
        return true;
    }


    /**
     * Returns all events in the calendar.
     *
     * @return a list containing all events
     */
    public List<Event> viewAll() {
        return new ArrayList<Event>(events);
    }


    /**
     * Returns all events occurring on the given date.
     *
     * @param date
     *            the date to view
     * @return a list of events occurring on that date
     */
    public List<Event> viewByDate(LocalDateTime date) {
        ArrayList<Event> eventsOnDay = new ArrayList<Event>();
        // Adds events that occur on the same calendar date.
        for (Event e : events) {
            if (e.getDateTime().toLocalDate().equals(date.toLocalDate())) {
                eventsOnDay.add(e);
            }
        }
        return eventsOnDay;
    }


    /**
     * Returns all events occurring within a seven-day window.
     *
     * @param weekStart
     *            the beginning of the seven-day window
     * @return a list of events occurring during the week
     */
    public List<Event> viewWeek(LocalDateTime weekStart) {
        ArrayList<Event> eventsInWeek = new ArrayList<Event>();
        LocalDateTime weekEnd = weekStart.plusDays(7);

        // Adds events between the start of the week and seven days later.
        for (Event e : events) {
            LocalDateTime eventTime = e.getDateTime();

            if (!eventTime.isBefore(weekStart) && eventTime.isBefore(weekEnd)) {
                eventsInWeek.add(e);
            }
        }
        return eventsInWeek;
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
