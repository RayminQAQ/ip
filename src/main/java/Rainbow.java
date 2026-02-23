import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Rainbow {

    static ArrayList<Task> storeItems = new ArrayList<>();
    static DataManager dataManager = new DataManager("./data/duke.txt");
    static UIManager uiManager = new UIManager();

    private static void greet() {
        uiManager.showGreeting();
    }

    private static void run(){
        // Input scanner
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Process input
            String userinput = scanner.nextLine();
            String[] partstest = userinput.trim().split(" ", 2);
            String command = partstest[0];
            String details = partstest.length > 1 ? partstest[1] : "";

            // Print user input
            uiManager.showUserInput(userinput);

            // Exit
            if (command.equals("bye")) { // Exit
                // Print message
                uiManager.showGoodbye();

                // Exit
                break;
            }

            // Other operations
            if (command.equals("list")) {
                // Print message
                uiManager.showTaskList(storeItems);
            } else if (command.startsWith("todo")) {
                // Error checking
                if (details.isEmpty()) {
                    uiManager.showEmptyTodoError();
                    continue;
                }

                // Extract description after "todo "
                String description = userinput.substring(5);

                // Create Todo task
                Todo todo = new Todo(description);
                storeItems.add(todo);

                // Save to file
                dataManager.saveTasks(storeItems);

                // Print message
                uiManager.showTaskAdded(todo, storeItems.size());
            } else if (command.startsWith("deadline")) {
                // Error cheching if description is empty
                if (details.isEmpty()) {
                    uiManager.showEmptyDeadlineError();
                    continue;
                }

                // Parse input example: deadline return book /by Sunday
                String[] parts = userinput.split(" /by ");
                String description = parts[0].substring(9); // Remove "deadline "
                String by = parts.length > 1 ? parts[1] : "";

                // Create Deadline task
                Deadline deadline = new Deadline(description, by);
                storeItems.add(deadline);

                // Save to file
                dataManager.saveTasks(storeItems);

                // Print message
                uiManager.showTaskAdded(deadline, storeItems.size());
            } else if (command.startsWith("event")) {
                // Error checking if description is empty
                if (details.isEmpty()) {
                    uiManager.showEmptyEventError();
                    continue;
                }


                // Parse input example: event project meeting /from Mon 2pm /to 4pm
                String[] parts = userinput.split(" /from ");
                String description = parts[0].substring(6); // Remove "event "

                String from = "";
                String to = "";
                if (parts.length > 1) {
                    String[] timeParts = parts[1].split(" /to ");
                    from = timeParts[0];
                    to = timeParts.length > 1 ? timeParts[1] : "";
                }

                // Create Event task
                Event event = new Event(description, from, to);
                storeItems.add(event);

                // Save to file
                dataManager.saveTasks(storeItems);

                // Print message
                uiManager.showTaskAdded(event, storeItems.size());
            } else if (command.startsWith("mark")) {
                // Operation
                String[] parts = userinput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsDone();

                // Save to file
                dataManager.saveTasks(storeItems);

                // Print
                uiManager.showTaskMarked(storeItems.get(index));
            } else if (command.startsWith("unmark")) {
                // Operation
                String[] parts = userinput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsNotDone();

                // Save to file
                dataManager.saveTasks(storeItems);

                // Print
                uiManager.showTaskUnmarked(storeItems.get(index));
            } else if (command.startsWith("delete")) {
                // Error checking if index is missing
                if (details.isEmpty()) {
                    uiManager.showMissingDeleteIndexError();
                    continue;
                }

                try {
                    // Parse the task index
                    int index = Integer.parseInt(details) - 1;

                    // Check if index is valid
                    if (index < 0 || index >= storeItems.size()) {
                        uiManager.showInvalidTaskNumberError();
                        continue;
                    }

                    // Get the task before removing it
                    Task removedTask = storeItems.get(index);

                    // Remove the task
                    storeItems.remove(index);

                    // Save to file
                    dataManager.saveTasks(storeItems);

                    // Print message
                    uiManager.showTaskDeleted(removedTask, storeItems.size());
                } catch (NumberFormatException e) {
                    uiManager.showInvalidNumberFormatError();
                }
            } else {
                // Unknown command
                uiManager.showUnknownCommandError();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Load tasks from file
        storeItems = dataManager.loadTasks();

        // Rename & Greet
        greet();

        // Runner class
        run();
    }
}
