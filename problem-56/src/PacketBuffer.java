import java.util.HashMap;
import java.util.Map;


public class PacketBuffer{
    private MinHeap bufferHeap;
    private CircularQueue playbackQueue;
    private int nextExpected;
    private int currentTime;
    private int timeout;
    private Map<Integer, Integer> missingSince;
    
    // Metrics
    private int packetsReceived;
    private int packetsLost;
    private int totalBufferSize;
    private int tickCount;

     private class CircularQueue {
        private Packet[] queue;
        private int front;
        private int rear;
        private int size;
        private int capacity;
        
        public CircularQueue(int capacity) {
            this.capacity = capacity;
            this.queue = new Packet[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }
        
        public void enqueue(Packet packet) {
            if (size == capacity) {
                System.out.println("Queue is full!");
                return;
            }
            rear = (rear + 1) % capacity;
            queue[rear] = packet;
            size++;
        }
        
        public Packet dequeue() {
            if (size == 0) return null;
            Packet packet = queue[front];
            front = (front + 1) % capacity;
            size--;
            return packet;
        }
        public Packet peek() {
            if (size == 0) return null;
            return queue[front];
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public int size() {
            return size;
        }
    }
    
    public PacketBuffer(int initialCapacity, int defaultTimeout) {
        this.bufferHeap = new MinHeap(initialCapacity);
        this.playbackQueue = new CircularQueue(initialCapacity);
        this.nextExpected = 1;
        this.currentTime = 0;
        this.timeout = defaultTimeout;
        this.missingSince = new HashMap<>();
        
        this.packetsReceived = 0;
        this.packetsLost = 0;
        this.totalBufferSize = 0;
        this.tickCount = 0;
    }
    
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    
    public void receivePacket(int seqNum, String data) {
        Packet packet = new Packet(seqNum, data, currentTime);
        bufferHeap.insert(packet);
        packetsReceived++;
        System.out.println("Received: " + packet);
    }
    
    public void tick() {
        currentTime++;
        tickCount++;
        
        // Check for timeouts
        checkTimeouts();
        
        // Try to release consecutive packets
        releaseConsecutivePackets();
        
        // Update metrics
        totalBufferSize += bufferHeap.size();
    }
    
    private void checkTimeouts() {
    // Keep checking while there are missing packets
    while (true) {
        if (bufferHeap.isEmpty()) break;
        
        Packet nextPacket = bufferHeap.peek();
        if (nextPacket == null) break;
        
        // If next packet in heap is what we expect, no timeout needed
        if (nextPacket.getSequenceNumber() == nextExpected) break;
        
        // We have a missing packet
        if (!missingSince.containsKey(nextExpected)) {
            missingSince.put(nextExpected, currentTime);
        }
        
        int firstMissingTime = missingSince.get(nextExpected);
        if (currentTime - firstMissingTime >= timeout) {
            // Timeout!
            System.out.println("Timeout for Seq " + nextExpected + "! Marked as LOST.");
            packetsLost++;
            nextExpected++;  // Skip missing packet
            missingSince.remove(nextExpected - 1);
        } else {
            // Not timed out yet
            break;
        }
    }
}
    private void releaseConsecutivePackets() {
    while (!bufferHeap.isEmpty() && 
           bufferHeap.peek().getSequenceNumber() == nextExpected) {
        Packet packet = bufferHeap.extractMin();
        playbackQueue.enqueue(packet);
        nextExpected++;
        missingSince.remove(nextExpected - 1); // Clean up if was tracked as missing
        System.out.println("Released for playback: " + packet);
    }
}
    
    public void play() {
        System.out.println("\n=== Playing Packets ===");
        while (!playbackQueue.isEmpty()) {
            Packet packet = playbackQueue.dequeue();
            System.out.println("Playing: " + packet.getData());
        }
        System.out.println("=== End of Playback ===\n");
    }
    
    public void printMetrics() {
        double avgBufferSize = (tickCount > 0) ? 
            (double) totalBufferSize / tickCount : 0;
        double lossRate = (packetsReceived > 0) ? 
            (double) packetsLost / packetsReceived * 100 : 0;
        
        System.out.println("\n=== Metrics ===");
        System.out.println("Packets Received: " + packetsReceived);
        System.out.println("Packets Lost: " + packetsLost);
        System.out.printf("Loss Rate: %.1f%%\n", lossRate);
        System.out.printf("Avg Buffer Size: %.1f\n", avgBufferSize);
        System.out.println("================\n");
    }
    
    // Getters for testing
    public int getNextExpected(){
         return nextExpected;
          }
    public int getBufferSize() {
         return bufferHeap.size();
          }
    public int getCurrentTime(){ 
        return currentTime;
         }
}



