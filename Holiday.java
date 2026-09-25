import java.time.LocalDateTime;

// -------------------------------------------------------------------------
/** 
 * A built-in U.S. holiday. It is an all-day Event in the "Holiday" category
 * with the highest priority, and Calendar refuses to delete it.
 *
 * @author JC Valencia
 * @version Sep 24, 2026
 */
public class Holiday
    extends Event
{

    // ----------------------------------------------------------
    /**
     * Creates a holiday.
     *
     * @param dateTime
     *            the date of the holiday
     * @param name
     *            the name of the holiday
     */
    public Holiday(LocalDateTime dateTime, String name)
    {
        super(dateTime, name, true, "Holiday", 5);
    }
}
