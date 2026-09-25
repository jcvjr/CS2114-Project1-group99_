# Nudge

Nudge is a console calendar application written in Java for CS2114 Project 1
(group 99). You can add, edit, delete, view, sort and filter events from a text
menu. The calendar comes preloaded with a set of built-in U.S. holidays.

## Team

- Leo: `Nudge` (the menu and program flow)
- Nicolas Marchese: `Calendar`
- JC Valencia and Xinchen "Soren": `Event` and `Holiday`

`DateTimeUtil` was built by the whole group.

## Running the program

Requires Java 8 or later. No external libraries are needed to run Nudge.

**In Eclipse:** import the project (File > Import > General > Existing Projects
into Workspace), then right-click `Nudge.java` and choose
Run As > Java Application. Type your input in the Console tab.

**From a terminal:**

```
javac Nudge.java
java Nudge
```

## Using Nudge

On startup, Nudge loads the holidays and asks you to create your first event.
After that, the main menu appears:

| Option | What it does |
|---|---|
| 1 | Add an event |
| 2 | Edit an event (pick it by its number in the list) |
| 3 | Delete an event (holidays cannot be deleted) |
| 4 | View all events |
| 5 | View the events on one date |
| 6 | View a 7-day week, starting from a date you enter |
| 7 | Sort events by priority (ascending or descending) |
| 8 | Filter events by category |
| 9 | Set a description for a category |
| 10 | View a category's description |
| 11 | Quit |

### Entering an event

| Field | Format |
|---|---|
| Title | Any text |
| Date | `yyyy-MM-dd`, for example `2026-09-17` |
| Time | `hh:mm a`, for example `01:00 PM` |
| Duration | A positive whole number of minutes |
| Category | Any text, used for filtering |
| Priority | A whole number from 1 (low) to 5 (high) |

Nudge checks your input and asks again if something is wrong:

- **Not a real date or time.** Dates such as `2026-02-31` or `2029-02-29` (2029
  is not a leap year) are rejected and you are shown the expected format.
  `2028-02-29` is accepted.
- **Date in the past.** New events must be in the future.
- **Time conflict.** If a new event overlaps an existing one, Nudge shows the
  conflict and lets you change the time, cancel, or delete the conflicting
  event. An event that starts exactly when another ends does not conflict.
- **Bad menu choice.** You are shown the valid options and asked again.
- **Holidays.** They are protected and cannot be deleted.

## Project structure

| File | Role |
|---|---|
| `Nudge.java` | Program entry point: first-launch setup, prompts and the menu loop |
| `Calendar.java` | Holds all events and category descriptions: add, edit, delete, view, sort, filter, `checkDueNow`, `loadHolidays` |
| `Event.java` | One event: date and time, name, duration, category, priority, all-day flag |
| `Holiday.java` | An `Event` subclass for built-in holidays (all day, category "Holiday", priority 5) |
| `DateTimeUtil.java` | The one place that parses typed date and time text and compares times (`parseDateTime`, `isPast`, `overlaps`) |

Design choices:

- An event's date and time are stored in one `LocalDateTime`, so "is it
  before?" and "do these overlap?" are simple built-in comparisons.
- Duration is stored as a plain number of minutes, so the end time is the start
  plus the duration.
- `Calendar` keeps events in an `ArrayList<Event>` and category notes in a
  `Map<String, String>`, so a category can have a description without
  repeating it on every event.

## Tests

Tests are in `DateTimeUtilTest.java`, `EventTest.java` and `CalendarTest.java`.
They extend `student.TestCase`, so the project needs the course's `student.jar`
on its build path (in Eclipse: Project > Properties > Java Build Path, then add
the `CS2-Support` project or `student.jar`). Run a test class with
Run As > JUnit Test.

## Things to know

- Events are kept in memory only. Closing the program discards anything you
  added, and the built-in holidays are reloaded on the next start.
- The built-in holidays cover October 2026 through March 2027.
- Of the stretch goals, we chose one: sorting and filtering by priority and
  category.
