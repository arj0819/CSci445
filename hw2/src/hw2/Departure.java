package hw2;

public class Departure extends Event {

    public static final String OUTSIDE_WORLD = "Outside World";

    private static int totalDepartures = 0;
    private int departureID = 0;

    private int patientID = 0;

    private String destination = "";

    public Departure(int patientID, double timeDeparted, String location, String destination) {
        super(timeDeparted, location);
        this.departureID = ++totalDepartures;
        this.patientID = patientID;
        this.destination = destination;
        System.out.println("DEPARTURE "+this.departureID+" CONSTRUCTED");
    }

    public static int getTotalDepartures() {
        return totalDepartures;
    }

    public int getID() {
        return departureID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getDestination() {
        return destination;
    }
}