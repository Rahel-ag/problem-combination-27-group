public class MinHeap{
    private Packet[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Packet[capacity];
    }
    private int parent(int i){
        return (i-1)/2;
    }
    private int leftchild(int i){
        return(i*2)+1;
    }
    private int rightchild(int i){
        return(i*2)+2;
    }
    private void swap(int i, int j){
        Packet temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public void insert(Packet packet){
        if(size >= capacity){
            return;
        }
        heap[size] = packet;
        int current = size;
        size++;
     

        while (current > 0 && heap[current].getSequenceNumber() < heap[parent(current)].getSequenceNumber()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    public Packet peek() {
        if (size == 0) return null;
        return heap[0];
    }
    
    public Packet extractMin() {
        if (size == 0) return null;
        
        Packet min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        
        return min;
    }
    
    private void heapify(int i) {
        int smallest = i;
        int left = leftchild(i);
        int right = rightchild(i);
        
        if (left < size && 
            heap[left].getSequenceNumber() < heap[smallest].getSequenceNumber()) {
            smallest = left;
        }
        
        if (right < size && 
            heap[right].getSequenceNumber() < heap[smallest].getSequenceNumber()) {
            smallest = right;
        }
        
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }
    
    public boolean isEmpty() { 
        return size == 0; 
        }
    public int size() {
         return size; 
         }
}
