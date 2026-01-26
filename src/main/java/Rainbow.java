import java.util.Scanner;
import java.util.ArrayList;

public class Rainbow {

    static ArrayList<String> storeItems = new ArrayList<>();

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

        // Level 1. Echo + Level 2. Add, List
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String user_input = scanner.nextLine();
            printHorizontalLine();
            System.out.println(" " + user_input);

            if (user_input.equals("list")) {
                // Print message
                printHorizontalLine();
                System.out.println(" Here are the items in your list:");
                for (int i = 0; i < storeItems.size(); i++) { // Loop through internal storage
                    System.out.println(" " + (i + 1) + ". " + storeItems.get(i));
                }
                printHorizontalLine();

            } else if (user_input.equals("bye")) { // Exit
                // Print message
                printHorizontalLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printHorizontalLine();

                // Exit
                break;
            } else {
                // Print message
                printHorizontalLine();
                System.out.println(" added: " + user_input);
                printHorizontalLine();

                // Internal storage
                storeItems.add(user_input);
            }
        }
        scanner.close();
    }
}
