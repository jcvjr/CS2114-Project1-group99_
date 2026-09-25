import java.time.*;
import java.util.List;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author lquezadr
 * @version Sep 24, 2026
 */
public class Nudge
{

    private Calendar activeCalendar = new Calendar();
    private boolean running = true;
    private Scanner scan = new java.util.Scanner(System.in);

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Nudge nudge = new Nudge();
        nudge.activeCalendar.loadHolidays();
        nudge.runFirstLaunchSetup();
        nudge.menuLoop();
    }


    private void runFirstLaunchSetup()
    {
        System.out.println("Welcome to Nudge! Let's add your first event.");
        Event event = promptForEvent();

        while (true)
        {
            String result = activeCalendar.addEvent(event);
            System.out.println(result);
            if (!result.contains("Rejected: conflicts"))
            {
                break;
            }
            System.out.println("1) Change the time");
            System.out.println("2) Cancel");
            System.out.println("3) Delete the conflicting event");
            System.out.print("Choice: ");

            String choice = scan.nextLine();

            if (choice.equals("2"))
            {
                break;
            }
            else if (choice.equals("1"))
            {
                System.out.print("New date: ");
                String date = scan.nextLine();
                System.out.print("New time: ");
                String time = scan.nextLine();
                
                try
                {
                    event.setDate(DateTimeUtil.parseDateTime(date, time));
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if (choice.equals("3"))
            {
                List<Event> all = activeCalendar.viewAll();
                for (int i = 0; i < all.size(); i++)
                {
                    Event existing = all.get(i);
                    
                    boolean overlap = DateTimeUtil.overlaps(
                        event.getDateTime(),
                        event.getDuration(),
                        existing.getDateTime(),
                        existing.getDuration());
                    if (overlap)
                    {
                        activeCalendar.deleteEvent(i);
                        break;
                    }
                }
            }
        }
    }


    private Event promptForEvent()
    {
        while (true)
        {
            try
            {
                System.out.print("Title: ");
                String title = scan.nextLine();

                System.out.print("Date (yyyy-MM-dd): ");
                String date = scan.nextLine();

                System.out.print("Time (hh:mm a, ex. 01:00 PM): ");
                String time = scan.nextLine();

                LocalDateTime dateTime = DateTimeUtil.parseDateTime(date, time);

                System.out.print("Duration (minutes): ");
                int duration = scan.nextInt();

                System.out.print("Category: ");
                String category = scan.nextLine();

                System.out.print("Priority (1-5): ");
                int priority = scan.nextInt();

                return new Event(dateTime, title, priority,category,duration);

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }


    private void menuLoop()
    {
        while (running)
        {
            System.out.println();
            System.out.println("1) Add event");
            System.out.println("2) Edit event");
            System.out.println("3) Delete event");
            System.out.println("4) View all events");
            System.out.println("5) View events on a date");
            System.out.println("6) View week");
            System.out.println("7) Sort by priority");
            System.out.println("8) Filter by category");
            System.out.println("9) Set category description");
            System.out.println("10) Quit");
            System.out.print("Choose an option: ");

            int choice;

            try
            {
                choice = scan.nextInt();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == 1)
            {
                Event event = promptForEvent();
                while (true)
                {
                    String result = activeCalendar.addEvent(event);
                    System.out.println(result);
                    
                    if (!result.startsWith("Rejected: conflicts"))
                    {
                        break;
                    }
                    System.out.println("1) Change the time");
                    System.out.println("2) Cancel");
                    System.out.println("3) Delete the conflicting event");
                    System.out.print("Choice: ");

                    String subChoice = scan.nextLine();

                    if (subChoice.equals("2"))
                    {
                        break;
                    }
                    else if (subChoice.equals("1"))
                    {
                        System.out.print("New date: ");
                        String date = scan.nextLine();
                        System.out.print("New time: ");
                        String time = scan.nextLine();
                        try
                        {
                            event.setDate(
                                DateTimeUtil.parseDateTime(date, time));
                        }
                        catch (IllegalArgumentException e2)
                        {
                            System.out.println(e2.getMessage());
                        }
                    }
                    else if (subChoice.equals("3"))
                    {
                        java.util.List<Event> all = activeCalendar.viewAll();
                        for (int i = 0; i < all.size(); i++)
                        {
                            Event existing = all.get(i);
                            boolean overlap = DateTimeUtil.overlaps(
                                event.getDateTime(),
                                event.getDuration(),
                                existing.getDateTime(),
                                existing.getDuration());
                            if (overlap)
                            {
                                activeCalendar.deleteEvent(i);
                                break;
                            }
                        }
                    }
                }

            }
            else if (choice == 2)
            {
                java.util.List<Event> forEdit = activeCalendar.viewAll();
                for (int i = 0; i < forEdit.size(); i++)
                {
                    System.out.println(i + ": " + forEdit.get(i));
                }
                System.out.print("Number of event in list to edit: ");
                int editIndex;
                try
                {
                    editIndex = scan.nextInt();
                }
                catch (NumberFormatException e)
                {
                    editIndex = -1;
                }
                Event updated = promptForEvent();
                if (activeCalendar.editEvent(editIndex, updated))
                {
                    System.out.println("Updated.");
                }
                else
                {
                    System.out.println("No such event.");
                }

            }
            else if (choice == 3)
            {
                java.util.List<Event> forDelete = activeCalendar.viewAll();
                
                for (int i = 0; i < forDelete.size(); i++)
                {
                    System.out.println(i + ": " + forDelete.get(i));
                }
                System.out.print("Number of event in list to delete: ");
                int deleteIndex;
                try
                {
                    deleteIndex = scan.nextInt();
                }
                catch (NumberFormatException e)
                {
                    deleteIndex = -1;
                }
                if (activeCalendar.deleteEvent(deleteIndex))
                {
                    System.out.println("Deleted.");
                }
                else
                {
                    System.out.println("Couldn't delete that event.");
                }

            }
            else if (choice == 4)
            {
                java.util.List<Event> all4 = activeCalendar.viewAll();
                for (int i = 0; i < all4.size(); i++)
                {
                    System.out.println(i + ": " + all4.get(i));
                }

            }
            else if (choice == 5)
            {
                System.out.print("Date (yyyy-MM-dd): ");
                String dateInput = scan.nextLine();
                try
                {
                    LocalDateTime dt =
                        DateTimeUtil.parseDateTime(dateInput, "12:00 AM");
                    java.util.List<Event> byDate =
                        activeCalendar.viewByDate(dt);
                    for (int i = 0; i < byDate.size(); i++)
                    {
                        System.out.println(i + ": " + byDate.get(i));
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else if (choice == 6)
            {
                System.out.print("Week start date (yyyy-MM-dd): ");
                
                String weekInput = scan.nextLine();
                
                try
                {
                    LocalDateTime dt =
                        DateTimeUtil.parseDateTime(weekInput, "12:00 AM");
                    java.util.List<Event> week = activeCalendar.viewWeek(dt);
                    for (int i = 0; i < week.size(); i++)
                    {
                        System.out.println(i + ": " + week.get(i));
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else if (choice == 7)
            {
                System.out.print("Descending? (y/n): ");
                
                boolean descending = scan.nextLine().equalsIgnoreCase("y");
                
                List<Event> sorted = activeCalendar.sortByPriority(descending);
                
                for (int i = 0; i < sorted.size(); i++)
                {
                    System.out.println(i + ": " + sorted.get(i));
                }

            }
            else if (choice == 8)
            {
                System.out.print("Category: ");
                
                String filterCategory = scan.nextLine();
                
                java.util.List<Event> filtered =
                    activeCalendar.filterByCategory(filterCategory);
                
                for (int i = 0; i < filtered.size(); i++)
                {
                    System.out.println(i + ": " + filtered.get(i));
                }

            }
            else if (choice == 9)
            {
                System.out.print("Category: ");
                String category = scan.nextLine();
                
                System.out.print("Description: ");
                String description = scan.nextLine();
                
                activeCalendar.setCategoryDescription(category, description);
                
                System.out.println("Done!");

            }
            else if (choice == 10)
            {
                running = false;
                System.out.print("Goodbye!");
                break;
            }
            else
            {
                System.out.println("Invalid choice.");
            }
        }
    }
}
