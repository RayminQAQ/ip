import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDateTime;
    protected LocalDate byDate;

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

    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String getByString() {
        return by;
    }

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
