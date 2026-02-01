public class Main {

    public static void main(String[] args) throws java.io.IOException {
        SupportCenter system = new SupportCenter();

        System.out.println(
            "Support Center System Loaded. " +
            "Commands: ARRIVE <user> <tier>, PROCESS_NEXT, STATUS, EXIT"
        );

        java.io.BufferedReader reader = new java.io.BufferedReader(
            new java.io.InputStreamReader(System.in)
        );

        while (true) {
            System.out.print("> ");
            String input = reader.readLine();
            if (input == null) break;
            input = input.trim();
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
                System.out.println("Exiting system.");
                break;

            } else {
                System.out.println("Invalid Command.");
            }
        }
    }
}
