public class SupportCenter {

    // Simple queue implementation
    private static class SimpleQueue {
        private static class Node {
            String value;
            Node next;
            Node(String value) { this.value = value; }
        }

        private Node head = null;
        private Node tail = null;

        public void add(String value) {
            Node node = new Node(value);
            if (tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        public String poll() {
            if (head == null) return null;
            String val = head.value;
            head = head.next;
            if (head == null) tail = null;
            return val;
        }

        public boolean isEmpty() {
            return head == null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Node current = head;
            while (current != null) {
                sb.append(current.value);
                if (current.next != null) sb.append(", ");
                current = current.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    // queues[0] = Platinum, queues[1] = Gold, queues[2] = Silver
    private SimpleQueue[] queues;

    // WRR quotas
    private int[] quotas = {3, 2, 1};
    private String[] tierNames = {"Platinum", "Gold", "Silver"};

    // Cycle state
    private int currentTier = 0;
    private int currentCount = 0;

    public SupportCenter() {
        queues = new SimpleQueue[3];
        for (int i = 0; i < 3; i++) {
            queues[i] = new SimpleQueue();
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
