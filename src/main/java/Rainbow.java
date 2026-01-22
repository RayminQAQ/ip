public class Rainbow {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horizontalLine = "____________________________________________________________";

        // 1. Rename & Greet (命名與問候)
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm Rainbow"); // 這裡可以改成您喜歡的名字
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        // 2. Exit (退出訊息)
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
