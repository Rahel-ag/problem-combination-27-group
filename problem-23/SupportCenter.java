package src;

import java.util.ArrayDeque;
import java.util.Deque;

public class SupportCenter {

    // queues[0] = Platinum, queues[1] = Gold, queues[2] = Silver
    private Deque<String>[] queues;

    // WRR quotas
    private int[] quotas = {3, 2, 1};
    private String[] tierNames = {"Platinum", "Gold", "Silver"};

    // Cycle state
    private int currentTier = 0;
    private int currentCount = 0;

    @SuppressWarnings("unchecked")
    public SupportCenter() {
        queues = new ArrayDeque[3];
        for (int i = 0; i < 3; i++) {
            queues[i] = new ArrayDeque<>();
        }
    }

    // ARRIVE <user> <tier>
    public void arrive(String user, String tier) {
        if (tier.equals("Platinum")) {
            queues[0].add(user);
        } else if (tier.equals("Gold")) {
            queues[1].add(user);
        } else if (tier.equals("Silver")) {
            queues[2].add(user);
        } else {
            System.out.println("Error: Tier does not exist.");
        }
    }

    // PROCESS_NEXT
    public void processNext() {
        int attempts = 0;

        while (attempts < 3) {
            boolean quotaReached = currentCount >= quotas[currentTier];
            boolean queueEmpty = queues[currentTier].isEmpty();

            if (quotaReached || queueEmpty) {
                currentTier = (currentTier + 1) % 3;
                currentCount = 0;
                attempts++;
                continue;
            }

            String user = queues[currentTier].poll();
            currentCount++;

            System.out.println(
                user + " (" +
                tierNames[currentTier].substring(0, 4) +
                " #" + currentCount + ")"
            );
            return;
        }

        System.out.println("All queues are empty.");
    }

    // STATUS
    public void status() {
        System.out.println("\n--- Current Queues ---");
        for (int i = 0; i < 3; i++) {
            System.out.println(tierNames[i] + ": " + queues[i]);
        }
        System.out.println("----------------------\n");
    }
}
