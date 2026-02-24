import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that needs to be completed by a specific date/time.
 * The deadline can be specified as a date only or with time.
 * Supports multiple date formats including yyyy-MM-dd and d/M/yyyy.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDateTime;
    protected LocalDate byDate;

    /**
     * Creates a new Deadline with the given description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string (e.g., "2019-12-02", "2/12/2019 1800").
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = null;
        this.byDate = null;
        parseDate(by);
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
     */
    private void parseDate(String dateStr) {
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
                this.byDateTime = LocalDateTime.parse(dateStr, formatter);
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
                this.byDate = LocalDate.parse(dateStr, formatter);
                return;
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }

        // If all parsing fails, keep as string
    }

    /**
     * Returns the deadline as a LocalDateTime object if it includes time.
     *
     * @return The deadline as LocalDateTime, or null if not parsed or no time specified.
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    /**
     * Returns the deadline as a LocalDate object if it's a date only.
     *
     * @return The deadline as LocalDate, or null if not parsed or time was specified.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the original deadline string as provided by the user.
     *
     * @return The original deadline string.
     */
    public String getByString() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     * Format: [D][status] description (by: formatted_date)
     * Dates are formatted as "MMM d yyyy" or "MMM d yyyy, h:mma" if time is included.
     *
     * @return A formatted string showing the deadline with its type, status, and due date.
     */
    @Override
    public String toString() {
        if (this.by.equals("")) {
            return "[D][" + getStatusIcon() + "] " + description;
        }

        String dateStr;
        if (byDateTime != null) {
            // Format as "MMM d yyyy, h:mma" (e.g., Dec 2 2019, 6:00PM)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
            dateStr = byDateTime.format(formatter);
        } else if (byDate != null) {
            // Format as "MMM d yyyy" (e.g., Dec 2 2019)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            dateStr = byDate.format(formatter);
        } else {
            // Use original string if parsing failed
            dateStr = by;
        }

        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dateStr + ")";
    }
}
