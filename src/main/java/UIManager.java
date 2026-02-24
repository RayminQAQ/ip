import java.util.ArrayList;

/**
 * Manages all user interface interactions and output display.
 * Handles formatting and printing of messages, task lists, and error messages
 * to provide a consistent user experience throughout the application.
 */
public class UIManager {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints a horizontal line separator.
     */
    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message when the chatbot starts.
     */
    public void showGreeting() {
        printHorizontalLine();
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");
    }

    /**
     * Prints the user input with horizontal line.
     *
     * @param input The user's input command to display.
     */
    public void showUserInput(String input) {
        printHorizontalLine();
        System.out.println(" " + input);
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints the task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            System.out.println(" " + (index + 1) + ". " + tasks.get(index));
        }
        printHorizontalLine();
    }

    /**
     * Prints message when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the list after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Prints message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    /**
     * Prints message when a task is unmarked.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        printHorizontalLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    /**
     * Prints message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Prints error message for empty todo description.
     */
    public void showEmptyTodoError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! The description of a todo cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints error message for empty deadline description.
     */
    public void showEmptyDeadlineError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints error message for empty event description.
     */
    public void showEmptyEventError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! The description of an event cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints error message for missing delete index.
     */
    public void showMissingDeleteIndexError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! Please specify which task to delete.");
        printHorizontalLine();
    }

    /**
     * Prints error message for invalid task number.
     */
    public void showInvalidTaskNumberError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! Invalid task number.");
        printHorizontalLine();
    }

    /**
     * Prints error message for invalid number format.
     */
    public void showInvalidNumberFormatError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! Please provide a valid task number.");
        printHorizontalLine();
    }

    /**
     * Prints error message for unknown command.
     */
    public void showUnknownCommandError() {
        System.out.println(" I'm sorry, I don't understand that command.");
    }

    /**
     * Prints tasks occurring on a specific date.
     *
     * @param tasks The list of tasks occurring on the specified date.
     * @param dateStr The date string to display in the message.
     */
    public void showTasksOnDate(ArrayList<Task> tasks, String dateStr) {
        printHorizontalLine();
        if (tasks.isEmpty()) {
            System.out.println(" No tasks found on " + dateStr);
        } else {
            System.out.println(" Tasks occurring on " + dateStr + ":");
            for (int index = 0; index < tasks.size(); index++) {
                System.out.println(" " + (index + 1) + ". " + tasks.get(index));
            }
        }
        printHorizontalLine();
    }

    /**
     * Prints error message for invalid date format.
     */
    public void showInvalidDateFormatError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! Invalid date format. Please use yyyy-MM-dd or d/M/yyyy format.");
        printHorizontalLine();
    }

    /**
     * Prints matching tasks from find command.
     *
     * @param tasks The list of tasks matching the search keyword.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        printHorizontalLine();
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found in your list.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int index = 0; index < tasks.size(); index++) {
                System.out.println(" " + (index + 1) + "." + tasks.get(index));
            }
        }
        printHorizontalLine();
    }

    /**
     * Prints error message for empty find keyword.
     */
    public void showEmptyFindKeywordError() {
        printHorizontalLine();
        System.out.println(" OOPS!!! Please provide a keyword to search for.");
        printHorizontalLine();
    }
}
