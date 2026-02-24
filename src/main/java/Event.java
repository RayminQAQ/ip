import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that occurs from a start date/time to an end date/time.
 * The event can be specified with dates only or include time.
 * Supports multiple date formats including yyyy-MM-dd and d/M/yyyy.
 */
public class Event extends Task {

    protected String from, to;
    protected LocalDateTime fromDateTime, toDateTime;
    protected LocalDate fromDate, toDate;

    /**
     * Creates a new Event with the given description and no date/time information.
     *
     * @param description The description of the event task.
     */
    public Event(String description) {
        super(description);
        this.from = "";
        this.to = "";
        this.fromDateTime = null;
        this.toDateTime = null;
        this.fromDate = null;
        this.toDate = null;
    }

    /**
     * Creates a new Event with the given description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param from The start date/time as a string (e.g., "2019-12-02", "2/12/2019 1400").
     * @param to The end date/time as a string (e.g., "2019-12-02", "2/12/2019 1600").
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = null;
        this.toDateTime = null;
        this.fromDate = null;
        this.toDate = null;
        parseDate(from, true);
        parseDate(to, false);
    }

    /**
     * Parses the date string and stores it as LocalDateTime or LocalDate if possible.
     * Supports multiple formats:
     * - yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)
     * - yyyy-MM-dd (e.g., 2019-12-02)
     * - d/M/yyyy HHmm (e.g., 2/12/2019 1800)
     * - d/M/yyyy (e.g., 2/12/2019)
     * If parsing fails, the original string is retained.
     *
     * @param dateStr The date string to parse.
     * @param isFrom True if this is the 'from' date, false if it's the 'to' date.
     */
    private void parseDate(String dateStr, boolean isFrom) {
        if (dateStr == null || dateStr.isEmpty()) {
            return;
        }

        // Try parsing as LocalDateTime with time
        String[] formats = {
            "yyyy-MM-dd HHmm",
            "d/M/yyyy HHmm",
            "yyyy-MM-dd'T'HH:mm"
        };

        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                if (isFrom) {
                    this.fromDateTime = dateTime;
                } else {
                    this.toDateTime = dateTime;
                }
                return;
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }

        // Try parsing as LocalDate without time
        String[] dateFormats = {
            "yyyy-MM-dd",
            "d/M/yyyy"
        };

        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDate date = LocalDate.parse(dateStr, formatter);
                if (isFrom) {
                    this.fromDate = date;
                } else {
                    this.toDate = date;
                }
                return;
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }

        // If all parsing fails, keep as string
    }

    /**
     * Returns the start date/time as a LocalDateTime object if it includes time.
     *
     * @return The start date/time as LocalDateTime, or null if not parsed or no time specified.
     */
    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Returns the end date/time as a LocalDateTime object if it includes time.
     *
     * @return The end date/time as LocalDateTime, or null if not parsed or no time specified.
     */
    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    /**
     * Returns the start date as a LocalDate object if it's a date only.
     *
     * @return The start date as LocalDate, or null if not parsed or time was specified.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the end date as a LocalDate object if it's a date only.
     *
     * @return The end date as LocalDate, or null if not parsed or time was specified.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Returns the original start date/time string as provided by the user.
     *
     * @return The original start date/time string.
     */
    public String getFromString() {
        return from;
    }

    /**
     * Returns the original end date/time string as provided by the user.
     *
     * @return The original end date/time string.
     */
    public String getToString() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     * Format: [E][status] description (from: formatted_from_date to: formatted_to_date)
     * Dates are formatted as "MMM d yyyy" or "MMM d yyyy, h:mma" if time is included.
     *
     * @return A formatted string showing the event with its type, status, and date range.
     */
    @Override
    public String toString() {
        if (this.from.equals("") && this.to.equals("")) {
            return "[E][" + getStatusIcon() + "] " + description;
        }

        String fromStr, toStr;

        // Format from date/time
        if (fromDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
            fromStr = fromDateTime.format(formatter);
        } else if (fromDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            fromStr = fromDate.format(formatter);
        } else {
            fromStr = from;
        }

        // Format to date/time
        if (toDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
            toStr = toDateTime.format(formatter);
        } else if (toDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            toStr = toDate.format(formatter);
        } else {
            toStr = to;
        }

        return "[E][" + getStatusIcon() + "] " + description + " (from: " + fromStr + " to: " + toStr + ")";
    }
}

