import java.time.*;
import java.time.format.*;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author Leo, Nick, JC, Soren
 * @version Sep 24, 2026
 */
public class DateTimeUtil
{
    /**
     * The expected format and example when entering a date.
     */
    public static final String DATE_FORMAT_HINT =
        "yyyy-MM-dd (example: 2026-09-17)";
    /**
     * The expected format and example when entering a time.
     */
    public static final String TIME_FORMAT_HINT = "hh:mm a (example: 01:00 PM)";

    // STRICT resolver style rejects wrong dates like 2026-02-30.
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
        .ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
    
    // STRICT resolver style rejects wrong times like 13:00 PM.
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter
        .ofPattern("hh:mm a").withResolverStyle(ResolverStyle.STRICT);

    // ----------------------------------------------------------
    /**
     * Parses a date string and a time string into a single LocalDateTime,
     * throwing an IllegalArgumentException if either is missing or invalid..
     * 
     * @param date
     * @param time
     * @return the combined LocalDateTime built from the given date and time
     */
    public static LocalDateTime parseDateTime(String date, String time)
    {
        
        // Reject a null or blank date before attempting to parse it.
        if (date == null || date.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "There is no date entered. Expected " + DATE_FORMAT_HINT + ".");
        }
        
        // Reject a null or blank time before attempting to parse it.
        if (time == null || time.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "There is no time entered. Expected " + TIME_FORMAT_HINT + ".");
        }

        LocalDate d;
        try
        {
            // Converting the trimmed date text into a LocalDate.
            d = LocalDate.parse(date.trim(), DATE_FORMAT);
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException(
                "That date is not valid. Expected " + DATE_FORMAT_HINT + ".");
        }

        LocalTime t;
        try
        {
            // Converting the trimmed time text into a LocalDate.
            t = LocalTime.parse(time.trim(), TIME_FORMAT);
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException(
                "That time is not valid. Expected " + TIME_FORMAT_HINT + ".");
        }

        return d.atTime(t);
    }


    // ----------------------------------------------------------
    /**
     * Checks whether a given date/time occurs before the current date/time.
     * 
     * @param dt
     * @param now
     * @return true if dt is before now, false otherwise
     */
    public static boolean isPast(LocalDateTime dt, LocalDateTime now)
    {
        // dt counts as "past" only if it comes before now.
        if (dt.isBefore(now))
        {
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Checks whether two events, each with a start time and duration in
     * minutes, overlap in time.
     * 
     * @param start1
     * @param duration1
     * @param start2
     * @param duration2
     * @return boolean whether events overlap
     */
    public static boolean overlaps(
        LocalDateTime start1,
        int duration1,
        LocalDateTime start2,
        int duration2)
    {
        // Computes when each event ends based on its duration.
        LocalDateTime endTime1 = start1.plusMinutes(duration1);
        LocalDateTime endTime2 = start2.plusMinutes(duration2);
        
        // Two time ranges overlap if each one starts before the other ends.
        if (start1.isBefore(endTime2) && start2.isBefore(endTime1))
        {
            return true;
        }
        return false;
    }

}
