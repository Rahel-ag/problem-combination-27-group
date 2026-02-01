import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SupportCenter system = new SupportCenter();

        System.out.println(
            "Customer Support Center Loaded. " +
            "Commands: ARRIVE <user> <tier>, PROCESS_NEXT, STATUS, EXIT"
        );

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] command = input.split("\\s+");
            String cmd = command[0].toUpperCase();

            if (cmd.equals("ARRIVE") && command.length == 3) {
                system.arrive(command[1], command[2]);

            } else if (cmd.equals("PROCESS_NEXT")) {
                system.processNext();

            } else if (cmd.equals("STATUS")) {
                system.status();

            } else if (cmd.equals("EXIT")) {
                scanner.close();
                return;

            } else {
                System.out.println("Invalid Command.");
            }
        }
    }
}
