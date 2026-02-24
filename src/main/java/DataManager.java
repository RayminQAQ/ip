import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages the saving and loading of tasks to/from a file.
 * Handles file I/O operations and data persistence for the task list.
 * The file format stores each task as: TYPE | DONE | DESCRIPTION | [EXTRA_INFO]
 */
public class DataManager {

    private final String filePath;

    /**
     * Creates a new DataManager with the default file path "./data/duke.txt".
     */
    public DataManager() {
        this.filePath = "./data/duke.txt";
    }

    /**
     * Creates a new DataManager with a custom file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves all tasks to the data file.
     * Creates the parent directory if it doesn't exist.
     * Each task is saved on a separate line in the file.
     *
     * @param tasks The list of tasks to save to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            // Create directory if it doesn't exist
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (!created) {
                    System.out.println(" Error: Failed to create data directory.");
                    return;
                }
            }

            // Write tasks to file
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
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
     * - TYPE: T for Todo, D for Deadline, E for Event
     * - DONE: 1 if done, 0 if not done
     * - DESCRIPTION: The task description
     * - EXTRA_INFO: Additional info like deadline date or event times
     *
     * @param task The task to convert to file format.
     * @return A string representation of the task suitable for file storage.
     */
    private String taskToFileFormat(Task task) {
        String type = "";
        String isDone = task.isDone ? "1" : "0";
        String details = "";

        if (task instanceof Todo) {
            type = "T";
            details = task.description;
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            // Store the original string to preserve the format
            details = task.description + " | " + deadline.getByString();
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            // Store the original strings to preserve the format
            details = task.description + " | " + event.getFromString() + " | " + event.getToString();
        }

        return type + " | " + isDone + " | " + details;
    }

    /**
     * Loads tasks from the data file.
     * Returns an empty list if the file doesn't exist.
     * Each line in the file is parsed to create a Task object.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty list if file doesn't exist.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks; // No file to load, return empty list
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File doesn't exist, return empty list
        } catch (Exception e) {
            System.out.println(" Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the file and creates a Task object.
     * Format: TYPE | DONE | DESCRIPTION | [EXTRA_INFO]
     * Supports parsing Todo, Deadline, and Event tasks.
     *
     * @param line The line from the file to parse.
     * @return A Task object created from the line, or null if parsing fails.
     */
    private Task parseTaskFromFile(String line) {
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
}
