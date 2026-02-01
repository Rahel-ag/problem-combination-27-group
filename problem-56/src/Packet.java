public class Packet {
    private int sequenceNumber;
    private String data;
    private int arrivalTime;
    private boolean lost;
    public Packet(int sequenceNumber, String data, int arrivalTime){
        this.sequenceNumber = sequenceNumber;
        this.data = data;
        this.arrivalTime = arrivalTime;
        this.lost = false;
    }
    public int getSequenceNumber(){
        return sequenceNumber;
    }
    public String getData(){
        return data;
    }
    public int getArrivalTime(){
        return arrivalTime;
    }
     public boolean isLost(){
         return lost; 
    }
    public void markAsLost(){ 
        this.lost = true; 
    }
    @Override
    public String toString(){
        return "seq " + sequenceNumber + ": " + data;
        }
}