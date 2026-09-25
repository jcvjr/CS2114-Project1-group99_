import java.time.LocalDateTime;
import java.util.List;

/**
 * Tests the Calendar class.
 *
 * @author Nicolas Marchese (nmarchese)
 * @version 09.24.2026
 */

public class CalendarTest extends student.TestCase {

    private Calendar calendar;

    /**
     * Creates a new Calendar before each test.
     */
    public void setUp() {
        calendar = new Calendar();
    }


    /**
     * Tests adding a valid event.
     */
    public void testAddEvent() {
        Event event = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            60);

        assertEquals("Event added successfully.", calendar.addEvent(event));

        assertEquals(1, calendar.viewAll().size());
    }


    /**
     * Tests adding an event in the past.
     */
    public void testAddEventPast() {
        Event event = new Event(LocalDateTime.of(2020, 1, 1, 12, 0),
            "Old Event", 60);

        assertEquals("Event cannot be added because it is in the past.",
            calendar.addEvent(event));

        assertEquals(0, calendar.viewAll().size());
    }


    /**
     * Tests adding an overlapping event.
     */
    public void testAddEventOverlap() {
        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            60);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 10, 12, 30),
            "Meeting", 60);

        calendar.addEvent(event1);

        assertEquals("Event conflicts with Lunch.", calendar.addEvent(event2));

        assertEquals(1, calendar.viewAll().size());
    }


    /**
     * Tests editing an event.
     */
    public void testEditEvent() {
        Event original = new Event(LocalDateTime.of(2099, 1, 10, 12, 0),
            "Lunch", 60);

        Event updated = new Event(LocalDateTime.of(2099, 1, 10, 13, 0),
            "Dinner", 60);

        calendar.addEvent(original);

        assertTrue(calendar.editEvent(0, updated));
        assertEquals("Dinner", calendar.viewAll().get(0).getName());
    }


    /**
     * Tests editing using a negative index.
     */
    public void testEditEventNegativeIndex() {
        Event updated = new Event(LocalDateTime.of(2099, 1, 10, 13, 0),
            "Dinner", 60);

        assertFalse(calendar.editEvent(-1, updated));
    }


    /**
     * Tests editing using an index that is too large.
     */
    public void testEditEventLargeIndex() {
        Event updated = new Event(LocalDateTime.of(2099, 1, 10, 13, 0),
            "Dinner", 60);

        assertFalse(calendar.editEvent(5, updated));
    }


    /**
     * Tests deleting a normal event.
     */
    public void testDeleteEvent() {
        Event event = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            60);

        calendar.addEvent(event);

        assertTrue(calendar.deleteEvent(0));
        assertEquals(0, calendar.viewAll().size());
    }


    /**
     * Tests deleting using a negative index.
     */
    public void testDeleteEventNegativeIndex() {
        assertFalse(calendar.deleteEvent(-1));
    }


    /**
     * Tests deleting using an index that is too large.
     */
    public void testDeleteEventLargeIndex() {
        assertFalse(calendar.deleteEvent(5));
    }


    /**
     * Tests that a holiday cannot be deleted.
     */
    public void testDeleteHoliday() {
        calendar.loadHolidays();

        assertFalse(calendar.deleteEvent(0));
        assertEquals(15, calendar.viewAll().size());
    }


    /**
     * Tests viewing all events.
     */
    public void testViewAll() {
        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            60);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 11, 12, 0),
            "Meeting", 60);

        calendar.addEvent(event1);
        calendar.addEvent(event2);

        List<Event> result = calendar.viewAll();

        assertEquals(2, result.size());
        assertEquals("Lunch", result.get(0).getName());
        assertEquals("Meeting", result.get(1).getName());
    }


    /**
     * Tests viewing events on a certain date.
     */
    public void testViewByDate() {
        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            60);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 10, 15, 0), "Class",
            60);

        Event event3 = new Event(LocalDateTime.of(2099, 1, 11, 12, 0),
            "Meeting", 60);

        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);

        List<Event> result = calendar.viewByDate(LocalDateTime.of(2099, 1, 10,
            0, 0));

        assertEquals(2, result.size());
        assertEquals("Lunch", result.get(0).getName());
        assertEquals("Class", result.get(1).getName());
    }


    /**
     * Tests viewing a date that has no events.
     */
    public void testViewByDateEmpty() {
        List<Event> result = calendar.viewByDate(LocalDateTime.of(2099, 1, 10,
            0, 0));

        assertEquals(0, result.size());
    }


    /**
     * Tests viewing events within a week.
     */
    public void testViewWeek() {
        LocalDateTime weekStart = LocalDateTime.of(2099, 1, 10, 0, 0);

        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Lunch",
            30);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 15, 12, 0),
            "Meeting", 30);

        Event event3 = new Event(LocalDateTime.of(2099, 1, 20, 12, 0),
            "Too Late", 30);

        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);

        List<Event> result = calendar.viewWeek(weekStart);

        assertEquals(2, result.size());
    }


    /**
     * Tests the boundaries of the weekly view.
     */
    public void testViewWeekBoundaries() {
        LocalDateTime weekStart = LocalDateTime.of(2099, 1, 10, 0, 0);

        Event before = new Event(LocalDateTime.of(2099, 1, 9, 23, 0), "Before",
            30);

        Event atStart = new Event(LocalDateTime.of(2099, 1, 10, 0, 0), "Start",
            30);

        Event inside = new Event(LocalDateTime.of(2099, 1, 15, 12, 0), "Inside",
            30);

        Event atEnd = new Event(LocalDateTime.of(2099, 1, 17, 0, 0), "End", 30);

        Event after = new Event(LocalDateTime.of(2099, 1, 18, 0, 0), "After",
            30);

        calendar.addEvent(before);
        calendar.addEvent(atStart);
        calendar.addEvent(inside);
        calendar.addEvent(atEnd);
        calendar.addEvent(after);

        List<Event> result = calendar.viewWeek(weekStart);

        assertEquals(2, result.size());
        assertEquals("Start", result.get(0).getName());
        assertEquals("Inside", result.get(1).getName());
    }


    /**
     * Tests sorting priority from low to high.
     */
    public void testSortByPriorityAscending() {
        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 10, 0), "One",
            30, "School", 1);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 10, 11, 0), "Three",
            30, "School", 3);

        Event event3 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Two",
            30, "School", 2);

        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);

        List<Event> result = calendar.sortByPriority(false);

        assertEquals(1, result.get(0).getPriority());
        assertEquals(2, result.get(1).getPriority());
        assertEquals(3, result.get(2).getPriority());
    }


    /**
     * Tests sorting priority from high to low.
     */
    public void testSortByPriorityDescending() {
        Event event1 = new Event(LocalDateTime.of(2099, 1, 10, 10, 0), "One",
            30, "School", 1);

        Event event2 = new Event(LocalDateTime.of(2099, 1, 10, 11, 0), "Three",
            30, "School", 3);

        Event event3 = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Two",
            30, "School", 2);

        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);

        List<Event> result = calendar.sortByPriority(true);

        assertEquals(3, result.get(0).getPriority());
        assertEquals(2, result.get(1).getPriority());
        assertEquals(1, result.get(2).getPriority());
    }


    /**
     * Tests sorting an empty calendar.
     */
    public void testSortByPriorityEmpty() {
        assertEquals(0, calendar.sortByPriority(false).size());
    }


    /**
     * Tests filtering events by category.
     */
    public void testFilterByCategory() {
        Event school = new Event(LocalDateTime.of(2099, 1, 10, 10, 0), "Class",
            60, "School", 3);

        Event personal = new Event(LocalDateTime.of(2099, 1, 10, 12, 0), "Gym",
            60, "Personal", 2);

        calendar.addEvent(school);
        calendar.addEvent(personal);

        List<Event> result = calendar.filterByCategory("School");

        assertEquals(1, result.size());
        assertEquals("Class", result.get(0).getName());
    }


    /**
     * Tests filtering a category with no matches.
     */
    public void testFilterByCategoryEmpty() {
        assertEquals(0, calendar.filterByCategory("School").size());
    }


    /**
     * Tests setting and getting a category description.
     */
    public void testCategoryDescription() {
        calendar.setCategoryDescription("School", "Classes and assignments");

        assertEquals("Classes and assignments", calendar.getCategoryDescription(
            "School"));
    }


    /**
     * Tests getting a missing category description.
     */
    public void testCategoryDescriptionMissing() {
        assertNull(calendar.getCategoryDescription("School"));
    }


    /**
     * Tests finding an event due exactly now.
     */
    public void testCheckDueNow() {
        LocalDateTime now = LocalDateTime.of(2099, 1, 10, 12, 0);

        Event event = new Event(now, "Lunch", 60);

        calendar.addEvent(event);

        List<Event> result = calendar.checkDueNow(now);

        assertEquals(1, result.size());
        assertEquals("Lunch", result.get(0).getName());
    }


    /**
     * Tests when no events are due.
     */
    public void testCheckDueNowEmpty() {
        LocalDateTime now = LocalDateTime.of(2099, 1, 10, 12, 0);

        Event event = new Event(LocalDateTime.of(2099, 1, 10, 13, 0), "Lunch",
            60);

        calendar.addEvent(event);

        assertEquals(0, calendar.checkDueNow(now).size());
    }


    /**
     * Tests loading the built-in holidays.
     */
    public void testLoadHolidays() {
        calendar.loadHolidays();

        assertEquals(15, calendar.viewAll().size());

        assertTrue(calendar.viewAll().get(0) instanceof Holiday);

        assertEquals("Holiday", calendar.viewAll().get(0).getCategory());

        assertEquals(5, calendar.viewAll().get(0).getPriority());

        assertTrue(calendar.viewAll().get(0).getAllDay());
    }
}
