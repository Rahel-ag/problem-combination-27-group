import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacketBuffer buffer = new PacketBuffer(100, 5); // Initial capacity 100, timeout 5 ticks
         System.out.println("=== Video Streaming Packet Reassembler ===");
        System.out.println("Commands:");
        System.out.println("  SET_TIMEOUT <t>");
        System.out.println("  RECEIVE <seq> <data>");
        System.out.println("  TICK");
        System.out.println("  PLAY");
        System.out.println("  METRICS");
        System.out.println("  EXIT");
        System.out.println();
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            
            String[] parts = input.split(" ", 3);
            String command = parts[0].toUpperCase();
            
            try {
                switch (command) {
                    case "SET_TIMEOUT":
                        if (parts.length < 2) {
                            System.out.println("Usage: SET_TIMEOUT <timeout>");
                            break;
                        }
                        int timeout = Integer.parseInt(parts[1]);
                        buffer.setTimeout(timeout);
                        System.out.println("Timeout set to: " + timeout);
                        break;
                        
                    case "RECEIVE":
                        if (parts.length < 3) {
                            System.out.println("Usage: RECEIVE <seq> <data>");
                            break;
                        }
                        int seq = Integer.parseInt(parts[1]);
                        String data = parts[2];
                        buffer.receivePacket(seq, data);
                        break;
                        
                    case "TICK":
                        buffer.tick();
                        System.out.println("Time advanced. Current time: " + buffer.getCurrentTime());
                        break;
                        
                    case "PLAY":
                        buffer.play();
                        break;
                        
                    case "METRICS":
                        buffer.printMetrics();
                        break;
                        
                    case "EXIT":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}