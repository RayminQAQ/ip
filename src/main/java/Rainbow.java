import java.util.Scanner;
import java.util.ArrayList;

public class Rainbow {

    static ArrayList<Task> storeItems = new ArrayList<>();

    private static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    private static void greet() {
        printHorizontalLine();
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");
    }

    public static void main(String[] args) {
        // Rename & Greet
        greet();

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

                // Print message
                printHorizontalLine();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + todo);
                System.out.println(" Now you have " + storeItems.size() + " tasks in the list.");
                printHorizontalLine();
            } else if (command.startsWith("deadline")) {
                // Parse input example: deadline return book /by Sunday
                String[] parts = userinput.split(" /by ");
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
            } else if (command.startsWith("event")) {
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

                // Print
                printHorizontalLine();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(storeItems.get(index));
                printHorizontalLine();
            } else {
                // Unknown command
                System.out.println(" I'm sorry, I don't understand that command.");
            }
        }
        scanner.close();
    }
}
