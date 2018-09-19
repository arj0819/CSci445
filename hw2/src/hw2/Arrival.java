package hw2;

public class Arrival extends Event {

    private static int totalArrivals = 0;
    private int arrivalID = 0;
    private boolean transferRequired = false;
    
    public Arrival(double timeOccurred, double timeEndured, double timeWaited, boolean transferRequired) {
        super(timeOccurred, timeEndured, timeWaited);
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