package hw2;

public class Arrival extends Event {

    private static int totalArrivals = 0;
    private int arrivalID = 0;
    private boolean transferRequired = false;
    
    public Arrival(double timeArrived, double interArrivalTime, double serviceTime, double waitTime, boolean transferRequired) {
        super(timeArrived, interArrivalTime, serviceTime, waitTime);
        System.out.println("ARRIVAL CONSTRUCTED");
        this.transferRequired = transferRequired;
        this.arrivalID = ++totalArrivals;
    }

    public static int getTotalArrivals() {
        return totalArrivals;
    }

    public int getID() {
        return arrivalID;
    }

    public boolean mustTransfer() {
        return transferRequired;
    }
}