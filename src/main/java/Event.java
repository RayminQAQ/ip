import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from, to;
    protected LocalDateTime fromDateTime, toDateTime;
    protected LocalDate fromDate, toDate;

    public Event(String description) {
        super(description);
        this.from = "";
        this.to = "";
        this.fromDateTime = null;
        this.toDateTime = null;
        this.fromDate = null;
        this.toDate = null;
    }

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

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getFromString() {
        return from;
    }

    public String getToString() {
        return to;
    }

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