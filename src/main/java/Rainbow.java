import java.util.Scanner;
import java.util.ArrayList;

public class Rainbow {

    static ArrayList<Task> storeItems = new ArrayList<>();

    private static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");
    }

    public static void main(String[] args) {
        // Rename & Greet
        greet();

        // Level 1. Echo + Level 2. Add, List + Level 3. Mark as Done
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String user_input = scanner.nextLine();
            printHorizontalLine();
            System.out.println(" " + user_input);

            // Exit
            if (user_input.equals("bye")) { // Exit
                // Print message
                printHorizontalLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printHorizontalLine();

                // Exit
                break;
            }

            // Other operations
            if (user_input.equals("list")) {
                // Print message
                printHorizontalLine();
                System.out.println(" Here are the tasks in your list:");
                for (int index = 0; index < storeItems.size(); index++) { // Loop through internal storage
                    System.out.println(" " + (index + 1) + ". " + storeItems.get(index));
                }
                printHorizontalLine();
            } else if (user_input.startsWith("todo")) {
                // Extract description after "todo "
                String description = user_input.substring(5);

                // Create Todo task
                Todo todo = new Todo(description);
                storeItems.add(todo);

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + todo);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (user_input.startsWith("deadline")) {
                // Parse input: deadline return book /by Sunday
                String[] parts = user_input.split(" /by ");
                String description = parts[0].substring(9); // Remove "deadline "
                String by = parts.length > 1 ? parts[1] : "";

                // Create Deadline task
                Deadline deadline = new Deadline(description, by);
                storeItems.add(deadline);

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + deadline);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (user_input.startsWith("event")) {
                // Parse input: event project meeting /from Mon 2pm /to 4pm
                String[] parts = user_input.split(" /from ");
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

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + event);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (user_input.startsWith("mark")) {
                // Operation
                String[] parts = user_input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsDone();

                // Print
                printHorizontalLine();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(storeItems.get(index));
                printHorizontalLine();
            } else if (user_input.startsWith("unmark")) {
                // Operation
                String[] parts = user_input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsNoDone();

                // Print
                printHorizontalLine();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(storeItems.get(index));
                printHorizontalLine();
            } else {
                // Print message
                printHorizontalLine();
                System.out.println(" added: " + user_input);
                printHorizontalLine();

                // Internal storage
                Task item = new Task(user_input);
                storeItems.add(item);
            }
        }
        scanner.close();
    }
}
