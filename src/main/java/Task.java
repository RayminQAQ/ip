/**
 * Represents a task with a description and completion status.
 * This is the base class for all types of tasks (Todo, Deadline, Event).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 'X' indicates the task is done, ' ' indicates it is not done.
     *
     * @return A string representing the status icon ("X" or " ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[Task][" + getStatusIcon() + "] " + description;
    }
}
