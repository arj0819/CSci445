package hw2;

public class Arrival extends Event {

    private static int totalArrivals = 0;

    public Arrival(double timeOccurred, double timeEndured, double timeWaited) {
        super(timeOccurred, timeEndured, timeWaited);
        totalArrivals++;
    }

    public static int getTotalArrivals() {
        return totalArrivals;
    }
}