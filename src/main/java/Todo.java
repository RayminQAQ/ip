/**
 * Represents a todo task without any date/time attached to it.
 * A todo is a simple task that needs to be done eventually.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * Format: [T][status] description
     *
     * @return A formatted string showing the todo with its type and status.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
