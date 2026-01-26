import java.util.Scanner;
import java.util.ArrayList;

public class Rainbow {

    static ArrayList<Task> storeItems = new ArrayList<>();

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNoDone() {
            this.isDone = false;
        }
    }

    private static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Rename & Greet
        printHorizontalLine();
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");

        // Level 1. Echo + Level 2. Add, List + Level 3. Mark as Done
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String user_input = scanner.nextLine();
            printHorizontalLine();
            System.out.println(" " + user_input);

            if (user_input.equals("list")) {
                // Print message
                printHorizontalLine();
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < storeItems.size(); i++) { // Loop through internal storage
                    System.out.println(" " + (i + 1) + ".[" + storeItems.get(i).getStatusIcon() + "] " + storeItems.get(i).description);
                }
                printHorizontalLine();

            } else if (user_input.equals("bye")) { // Exit
                // Print message
                printHorizontalLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printHorizontalLine();

                // Exit
                break;
            } else if (user_input.startsWith("mark")) {
                // Operation
                String[] parts = user_input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsDone();

                // Print
                printHorizontalLine();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" [" + storeItems.get(index).getStatusIcon() + "] " + storeItems.get(index).description);
                printHorizontalLine();
            } else if (user_input.startsWith("unmark")) {
                // Operation
                String[] parts = user_input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                storeItems.get(index).markAsNoDone();

                // Print
                printHorizontalLine();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" [" + storeItems.get(index).getStatusIcon() + "] " + storeItems.get(index).description);
                printHorizontalLine();
            }
            else {
                // Print message
                printHorizontalLine();
                System.out.println(" added: " + user_input);
                printHorizontalLine();

                // Internal storage
                Task item = new Rainbow().new Task(user_input);
                storeItems.add(item);
            }
        }
        scanner.close();
    }
}
