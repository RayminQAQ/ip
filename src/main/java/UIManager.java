import java.util.ArrayList;

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
     */
    public void showTaskMarked(Task task) {
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    /**
     * Prints message when a task is unmarked.
     */
    public void showTaskUnmarked(Task task) {
        printHorizontalLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    /**
     * Prints message when a task is deleted.
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
}
