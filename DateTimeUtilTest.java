import java.time.*;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author JC, Leo, Soren, Nick
 * @version Sep 25, 2026
 */
public class DateTimeUtilTest
    extends TestCase
{
    // ----------------------------------------------------------

    // ~ Fields ................................................................
    private LocalDateTime fixedNow;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    public void setUp()
    {
        fixedNow = LocalDateTime.of(2026, 9, 25, 12, 0);
    }


    /**
     * Place a description of your method here.
     */
    public void testParseDateTime()
    {
        LocalDateTime result =
            DateTimeUtil.parseDateTime("2026-09-17", "01:00 PM");
        LocalDateTime expected = LocalDateTime.of(2026, 9, 17, 13, 0);
        assertEquals(expected, result);
        
        LocalDateTime result2 =
            DateTimeUtil.parseDateTime("  2026-09-17  ", "  01:00 PM  ");
        LocalDateTime expected2 = LocalDateTime.of(2026, 9, 17, 13, 0);
        assertEquals(expected2, result2);
    }
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testParseDateTimeNullDate()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime(null, "1:30 PM");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }
 
 
    // ----------------------------------------------------------
    /**
     * A blank (whitespace-only) date should be rejected with an
     * IllegalArgumentException.
     */
    public void testParseDateTimeBlankDate()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime("   ", "01:00 PM");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }
 
 
    // ----------------------------------------------------------
    /**
     * A null time should be rejected with an IllegalArgumentException.
     */

    public void testParseDateTimeNullTime()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime("2026-09-17", null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }
 
    // ----------------------------------------------------------
    /**
     * A blank (whitespace-only) time should be rejected with an
     * IllegalArgumentException.
     */
    public void testParseDateTimeBlankTime()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime("2026-09-17", "   ");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }
    
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testParseDateTimeInvalidDate()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime("2026-02-30", "01:00 PM");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }
    
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testParseDateTimeInvalidTime()
    {
        Exception exception = null;
        try
        {
            DateTimeUtil.parseDateTime("2026-09-17", "14:00"); 
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testIsPast()
    {
        assertFalse(DateTimeUtil.isPast(fixedNow, fixedNow));

        LocalDateTime future = fixedNow.plusDays(1);
        assertFalse(DateTimeUtil.isPast(future, fixedNow));

        LocalDateTime past = fixedNow.minusDays(1);
        assertTrue(DateTimeUtil.isPast(past, fixedNow));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testOverlaps()
    {
        LocalDateTime start1 = LocalDateTime.of(2026, 9, 17, 9, 0);
        LocalDateTime start2 = LocalDateTime.of(2026, 9, 17, 11, 0);
        assertFalse(DateTimeUtil.overlaps(start1, 30, start2, 30));
        
        LocalDateTime start3 = LocalDateTime.of(2026, 9, 17, 9, 0);
        LocalDateTime start4 = LocalDateTime.of(2026, 9, 17, 9, 30);
        assertTrue(DateTimeUtil.overlaps(start3, 60, start4, 60));
        
        LocalDateTime start5 = LocalDateTime.of(2026, 9, 17, 9, 0);
        LocalDateTime start6 = LocalDateTime.of(2026, 9, 17, 9, 15);
        assertTrue(DateTimeUtil.overlaps(start5, 120, start6, 15));
        
        LocalDateTime start7 = LocalDateTime.of(2026, 9, 17, 11, 0);
        LocalDateTime start8 = LocalDateTime.of(2026, 9, 17, 9, 0);
        assertFalse(DateTimeUtil.overlaps(start7, 30, start8, 30));
    }
}
