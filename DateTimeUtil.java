import java.time.*;
import java.time.format.*;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author Leo, Nick, JC, Xinchen(Soren)
 *  @version Sep 24, 2026
 */
public class DateTimeUtil
{

    // ----------------------------------------------------------
    /**
     * Have date and time input as String and convert String input into 
     * LocalDateTime Object
     * 
     * Expected Format:
     * date: yyyy-mm-dd
     * time: hh:mm
     * 
     * @param date
     * @param time
     * @return LocalDateTime Object of entered date and time
     */
    public LocalDateTime parseDateTime(String date, String time)
    {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        
        return LocalDateTime.of(localDate, localTime);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param dt
     * @param now
     * @return
     */
    public boolean isPast(LocalDateTime dt, LocalDateTime now)
    {
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param start1
     * @param duration1
     * @param start2
     * @param duration2
     * @return
     */
    public boolean overlaps (LocalDateTime start1, int duration1,
        LocalDateTime start2, int duration2)
    {
        LocalDateTime endTime1 = start1.plusMinutes(duration1);
        LocalDateTime endTime2 = start2.plusMinutes(duration2);
        
        if (start1.isBefore(endTime2) && start2.isBefore(endTime1))
            {
            return true;
            }
        return false;
    }


}
