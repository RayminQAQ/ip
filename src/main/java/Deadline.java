public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (this.by.equals("")){
            return "[D][" + getStatusIcon() + "] " + description;
        }
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}
