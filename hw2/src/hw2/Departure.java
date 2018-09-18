package hw2;

public class Departure extends Event {

    private static int totalDepartures = 0;
    private int departureID = 0;

    public Departure(double timeOccurred, double timeEndured) {
        super(timeOccurred, timeEndured);
        this.departureID = ++totalDepartures;
    }

    public static int getTotalDepartures() {
        return totalDepartures;
    }

    public int getID() {
        return departureID;
    }
}