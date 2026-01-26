import java.util.Scanner;

public class Rainbow {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horizontalLine = "____________________________________________________________";

        // Rename & Greet
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm Rainbow");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        // Level 1. Echo
        Scanner scanner = new Scanner(System.in);
        while(true){
            String user_input = scanner.nextLine();
            System.out.println(horizontalLine);
            System.out.println(" " + user_input);
            System.out.println(horizontalLine);

            if (user_input.equals("bye")){
                break;
            }
        }
        scanner.close();

        // Exit
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
