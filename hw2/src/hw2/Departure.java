package hw2;

public class Departure extends Event {

    private static int totalDepartures = 0;

    public Departure(double timeOccurred, double timeEndured) {
        super(timeOccurred, timeEndured);
        totalDepartures++;
    }

    public static int getTotalDepartures() {
        return totalDepartures;
    }
}