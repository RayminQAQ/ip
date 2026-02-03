public class Event extends Task {

    protected String from, to;

    public Event(String description) {
        super(description);
        this.from = "";
        this.to = "";
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        if (this.from.equals("") && this.to.equals("")) {
            return "[E][" + getStatusIcon() + "] " + description;
        }
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + this.from + " to: " + this.to + ")";
    }
}