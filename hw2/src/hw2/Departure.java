package hw2;

public class Departure extends Event {

    private static int totalDepartures = 0;
    private int departureID = 0;

    private String departedTo = "";

    public Departure(double timeDeparted, String location) {
        super(timeDeparted, location);
        this.departureID = ++totalDepartures;
        System.out.println("DEPARTURE "+this.departureID+" CONSTRUCTED");
    }

    public static int getTotalDepartures() {
        return totalDepartures;
    }

    public int getID() {
        return departureID;
    }

    public String getEndDestination() {
        return departedTo;
    }
}