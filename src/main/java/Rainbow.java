import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Rainbow {

    static ArrayList<Task> storeItems = new ArrayList<>();
    private static final String FILE_PATH = "./data/duke.txt";

    private static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    /**
     * Saves all tasks to the data file.
     */
    private static void saveTasks() {
        try {
            // Create directory if it doesn't exist
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (!created) {
                    System.out.println(" Error: Failed to create data directory.");
                    return;
                }
            }

            // Write tasks to file
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : storeItems) {
                String line = taskToFileFormat(task);
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(" Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object to file format string.
     * Format: TYPE | DONE | DESCRIPTION | [EXTRA_INFO]
     */
    private static String taskToFileFormat(Task task) {
        String type = "";
        String isDone = task.isDone ? "1" : "0";
        String details = "";

        if (task instanceof Todo) {
            type = "T";
            details = task.description;
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            details = task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            details = task.description + " | " + event.from + " | " + event.to;
        }

        return type + " | " + isDone + " | " + details;
    }

    /**
     * Loads tasks from the data file.
     */
    private static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return; // No file to load, start fresh
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    storeItems.add(task);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File doesn't exist, start with empty list
        } catch (Exception e) {
            System.out.println(" Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and creates a Task object.
     * Format: TYPE | DONE | DESCRIPTION | [EXTRA_INFO]
     */
    private static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;

            switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String by = parts.length > 3 ? parts[3] : "";
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String from = parts.length > 3 ? parts[3] : "";
                    String to = parts.length > 4 ? parts[4] : "";
                    task = new Event(description, from, to);
                    break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }

            return task;
        } catch (Exception e) {
            System.out.println(" Error parsing task: " + line);
            return null;
        }
    }

    private static void greet() {
        printHorizontalLine();
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");
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
            printHorizontalLine();
            System.out.println(" " + userinput);

            // Exit
            if (command.equals("bye")) { // Exit
                // Print message
                printHorizontalLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printHorizontalLine();

                // Exit
                break;
            }

            // Other operations
            if (command.equals("list")) {
                // Print message
                printHorizontalLine();
                System.out.println(" Here are the tasks in your list:");
                for (int index = 0; index < storeItems.size(); index++) { // Loop through internal storage
                    System.out.println(" " + (index + 1) + ". " + storeItems.get(index));
                }
                printHorizontalLine();
            } else if (command.startsWith("todo")) {
                // Error checking
                if (details.isEmpty()) {
                    printHorizontalLine();
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    printHorizontalLine();
                    continue;
                }

                // Extract description after "todo "
                String description = userinput.substring(5);

                // Create Todo task
                Todo todo = new Todo(description);
                storeItems.add(todo);

                // Save to file
                saveTasks();

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + todo);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (command.startsWith("deadline")) {
                // Error cheching if description is empty
                if (details.isEmpty()) {
                    printHorizontalLine();
                    System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                    printHorizontalLine();
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
                saveTasks();

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + deadline);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (command.startsWith("event")) {
                // Error checking if description is empty
                if (details.isEmpty()) {
                    printHorizontalLine();
                    System.out.println(" OOPS!!! The description of an event cannot be empty.");
                    printHorizontalLine();
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
                saveTasks();

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + event);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (command.startsWith("mark")) {
                // Operation
                String[] parts = userinput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsDone();

                // Save to file
                saveTasks();

                // Print
                printHorizontalLine();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(storeItems.get(index));
                printHorizontalLine();
            } else if (command.startsWith("unmark")) {
                // Operation
                String[] parts = userinput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsNotDone();

                // Save to file
                saveTasks();

                // Print
                printHorizontalLine();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(storeItems.get(index));
                printHorizontalLine();
            } else if (command.startsWith("delete")) {
                // Error checking if index is missing
                if (details.isEmpty()) {
                    printHorizontalLine();
                    System.out.println(" OOPS!!! Please specify which task to delete.");
                    printHorizontalLine();
                    continue;
                }

                try {
                    // Parse the task index
                    int index = Integer.parseInt(details) - 1;

                    // Check if index is valid
                    if (index < 0 || index >= storeItems.size()) {
                        printHorizontalLine();
                        System.out.println(" OOPS!!! Invalid task number.");
                        printHorizontalLine();
                        continue;
                    }

                    // Get the task before removing it
                    Task removedTask = storeItems.get(index);

                    // Remove the task
                    storeItems.remove(index);

                    // Save to file
                    saveTasks();

                    // Print message
                    printHorizontalLine();
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                    printHorizontalLine();
                } catch (NumberFormatException e) {
                    printHorizontalLine();
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    printHorizontalLine();
                }
            } else {
                // Unknown command
                System.out.println(" I'm sorry, I don't understand that command.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // New code
        DataManager dataManager = new DataManager("./data/duke.txt");

        // Load tasks from file
        loadTasks();

        // Rename & Greet
        greet();

        // Runner class
        run();
    }
}
