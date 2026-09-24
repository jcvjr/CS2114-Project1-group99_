/*
 * This is the event class that will be implemented by the holiday class
 */
public class Event{
    
    private LocalDateTime dateTime; //date and time that event is
    private String category; //optional category field
    private String name; //event name
    private int durationMin // duration of event in minutes
    private int priority // 1-5  signifying the importance of that event
    /*
     * This is the deafult constructor for the event class
     *  This does not include the priority or the category. 
     */
    public Event(LocalDateTime dateTime, String name, int durationMin)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.durationMin = durationMin;
        category = null;
        priority = 0;
    }
    /*
     * alternate constructor for event that does include category and priority
     */
    public Event(LocalDateTime dateTime, String name, int durationMin, String category, int priority)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.durationMin = durationMin;
        this.category = category;
        this.priority = priority;
    }
}


