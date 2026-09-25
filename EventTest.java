import java.time.*;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This is the test class for event class. It tests the constructors and methods
 * of the Event class to ensure that they behave as expected.
 * 
 * @author Xinchen Zhou (Soren)
 * @version Sep 25, 2026
 */


public class EventTest extends student.TestCase
{
    //~ Fields ................................................................
    
    private Event event;
    private Event allDayEvent;
    private Event priorityEvent;
    private Event  holidayEvent;
    private LocalDateTime dateTime;
    
    //~ Constructors ..........................................................
    
    // ----------------------------------------------------------
    /**
     * Create a new EventTest object.
     */
    public EventTest()
    {
        
    }

    //~Public  Methods ........................................................
    public void setUp()
    {
        dateTime = LocalDateTime.of(2026, 9, 24, 10, 30);
        event = new Event(dateTime, "Meeting", 60);
        //for covering 1st constructor
        allDayEvent = new Event(dateTime, "Holiday", true); 
        //for covering 2nd constructor
        priorityEvent = new Event(dateTime, "Exam", 120, "School", 4); 
        //for covering 3rd constructor
        holidayEvent = new Event(dateTime, "Christmas", true, "Holiday", 5);
        //for covering 4th constructor
    }
    
    
    //test methods for getters
    /**
     * test class for getEndTime()
     * */
    public void testGetEndTime()
    {
        LocalDateTime expected =LocalDateTime.of(2026, 9, 24, 11, 30);
        assertEquals(expected, event.getEndTime());
    }
    
    /**
     * test class for getDateTime()
     * */
    public void testGetDateTime()
    {
        assertEquals(dateTime, event.getDateTime());
    }
    
    /**
     * test class for getName()
     * */
    public void testGetName()
    {
        assertEquals("Meeting", event.getName());
    }
    
    /**
     * test class for getDuration() when the event is not an all day event
     * */
    public void testGetDurationWhenNotAllDay()
    {
        assertEquals(60, event.getDuration());
    }
    
    /**
     * test class for getDuration() when the event is an all day event
     * */
    public void testGetDurationWhenAllDay()
    {
        
        assertEquals(0, allDayEvent.getDuration());
    }
    
    /**
     * test class for getCategory() 
     * */
    public void testGetCategory()
    {
        assertEquals("Holiday", holidayEvent.getCategory());
    }
    
    /**
     * test class for getPriority()
     * */
    public void testGetPriority()
    {
        assertEquals(4, priorityEvent.getPriority());
    }
    
    /**
     * test class for getAllDay()
     * */
    public void testGetAllDay()
    {
        assertTrue(allDayEvent.getAllDay());
    }
    
    
  //test methods for setters, using getters for the methods returning void
    /**
     * test class for setDate()
     * */
    public void testSetDate()
    {
        LocalDateTime newDateTime = LocalDateTime.of(2026, 10, 1, 14, 0);
        event.setDate(newDateTime);
        assertEquals(newDateTime, event.getDateTime());
    }
    
    /**
     * test class for setName()
     * */
    public void testSetName()
    {
        event.setName("New Meeting");
        assertEquals("New Meeting", event.getName());
    }
    
    /**
     * test class for setDuration()
     * */
    public void testSetDuration()
    {
        event.setDuration(90);
        assertEquals(90, event.getDuration());
    }
    
    /**
     * test class for setCategory()
     * */
    public void testSetCategory()
    {
        event.setCategory("Work");
        assertEquals("Work", event.getCategory());
    }
    
    /**
     * test class for setPriority()
     * */   
    public void testSetPriority()
    {
        event.setPriority(4);
        assertEquals(4, event.getPriority());
    }
    
    /**
     * test class for setAllDay()
     * */
    public void testSetAllDay()
    {
        event.setAllDay(true);
        assertTrue(event.getAllDay());
    }
    
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testToString()
    {
        Event dogWalking =
            new Event(dateTime, "Dog Walking", 30, "Activity", 4);
        String expected =
            "Dog Walking on 2026-09-24 at 10:30 AM (Activity, priority 4)";
        assertEquals(expected, dogWalking.toString());
    }
}
