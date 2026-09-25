import java.time.*;

// -------------------------------------------------------------------------
/**
 * This is the event class that will be used by the calendar class and extended
 * by the holiday class.
 * 
 * @author JC Valencia
 * @version Sep 24, 2026
 */
public class Event
{

    /**
     * Extended by holiday
     */
    protected LocalDateTime dateTime; // date and time that event is
    /**
     * Extended by holiday
     */
    protected String category; // optional category field
    /**
     * Extended by holiday
     */
    protected String name; // event name
    private int durationMin; // duration of event in minutes
    /**
     * Extended by holiday
     */
    protected int priority; // 1-5 signifying the importance of that event
    /**
     * Extended by holiday
     */
    protected boolean allDay; // true if even is all day false if not
    /*
     * This is the default constructor for the event class This does not include
     * the priority or the category.
     */

    public Event(LocalDateTime dateTime, String name, int durationMin)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.durationMin = durationMin;
        category = null;
        priority = 0;
        allDay = false;
    }
    /*
     * // ----------------------------------------------------------
    /**
     *  default constructor but it is all day.
     */
    public Event(LocalDateTime dateTime, String name, boolean allDay)
    {
        this.dateTime = dateTime;
        this.name = name;
        durationMin = 0;
        category = null;
        priority = 0;
        this.allDay = allDay;
    }
    /*
     * alternate constructor for event that does include category and priority
     */
    public Event(
        LocalDateTime dateTime,
        String name,
        int durationMin,
        String category,
        int priority)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.durationMin = durationMin;
        this.category = category;
        this.priority = priority;
        allDay = false;
    }


    // ----------------------------------------------------------
    /**
     * alternate event constructor for if it is all day and has category and priority. 
     */
    public Event(
        LocalDateTime dateTime,
        String name,
        boolean allDay,
        String category,
        int priority)
    {
        this.dateTime = dateTime;
        this.name = name;
        durationMin = 0;
        this.category = category;
        this.priority = priority;
        this.allDay = allDay;
    }


    // ----------------------------------------------------------
    /**
     * This method is to get the end time of any event
     * 
     * @returnr end time
     */
    public LocalDateTime getEndTime()
    {
        return dateTime.plusMinutes(durationMin);
    }


    // ----------------------------------------------------------
    /**
     * changes date of event
     * 
     * @param newDateTime
     */
    public void setDate(LocalDateTime newDateTime)
    {
        dateTime = newDateTime;
    }


    // ----------------------------------------------------------
    /**
     * changes name of event
     * 
     * @param newName
     */
    public void setName(String newName)
    {
        name = newName;
    }


    // ----------------------------------------------------------
    /**
     * Changes the duration of event
     * 
     * @param duration
     */
    public void setDuration(int duration)
    {
        durationMin = duration;
    }


    // ----------------------------------------------------------
    /**
     * changes the category of the event
     * 
     * @param newCategory
     */
    public void setCategory(String newCategory)
    {
        category = newCategory;
    }


    // ----------------------------------------------------------
    /**
     * Changes the priority of the event
     * 
     * @param newPriority
     */
    public void setPriority(int newPriority)
    {
        priority = newPriority;
    }

    public void setAllDay(boolean all)
    {
        allDay = all;
    }
    // ----------------------------------------------------------
    /**
     * returns the LocalDateTime dateTime of the event
     * 
     * @return dateTime
     */
    public LocalDateTime getDateTime()
    {
        return dateTime;
    }


    // ----------------------------------------------------------
    /**
     * returns the name of the event.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }
    

    // ----------------------------------------------------------
    /**
     * returns the duration of the event
     * 
     * @return durationMin
     */
    public int getDuration()
    {
        if (!allDay)
            return durationMin;
        else
            return 0;
    }


    // ----------------------------------------------------------
    /**
     * returns the category of the event
     * 
     * @return category
     */
    public String getCategory()
    {
        return category;
    }


    // ----------------------------------------------------------
    /**
     * returns the priority level of event
     * 
     * @return priority
     */
    public int getPriority()
    {
        return priority;
    }
    
    // ----------------------------------------------------------
    /**
     * returns if it is all day
     * @return allDay
     */
    public boolean getAllDay()
    {
        return allDay;
    }
}
