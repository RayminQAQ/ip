import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataManager {

    private final String filePath;

    // Initialize data manager with the specified file path
    public DataManager() {
        this.filePath = "./data/duke.txt";
    }

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves all tasks to the data file.
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
