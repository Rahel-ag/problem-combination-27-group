import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands (type EXIT to quit):");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting.");
                break;
            }

            if (line.startsWith("VALIDATE")) {
                int firstQuote = line.indexOf("\"");
                int lastQuote = line.lastIndexOf("\"");

                if (firstQuote != -1 && lastQuote != -1 && firstQuote < lastQuote) {
                    String code = line.substring(firstQuote + 1, lastQuote);
                    SyntaxValidator.validate(code);

                } else {
                    System.out.println("Error: Please provide a string in quotes.");
                }
            } else {
                System.out.println("Unknown command. Use VALIDATE \"<code>\" or EXIT.");
            }
        }

        scanner.close();
    }
}