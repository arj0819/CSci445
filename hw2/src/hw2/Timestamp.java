package hw2;

public class Timestamp {

    private static int totalTimestamps = 0;
    private int timestampID = 0;
    private String eventType = "";
    private int eventID;

    private double timeOccurred = 0.0;
    
    private String location = "";
    private String destination = "";

    private double serviceTime = 0.0;
    private double interArrivalTime = 0.0;
    private double waitTime = 0.0;

    //private static int numberOfWaits = 0;
    //private static double totalWaitTime = 0.0;


    public Timestamp(Event eventToStamp) {
        if (eventToStamp instanceof Arrival) {
            this.eventType = Event.ARRIVAL;
            this.eventID = ((Arrival) eventToStamp).getID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.serviceTime = eventToStamp.getServiceTime();
            this.interArrivalTime = eventToStamp.getInterArrivalTime();
            this.waitTime = eventToStamp.getWaitTime();
        } else {
            this.eventType = Event.DEPARTURE;
            this.eventID = ((Departure) eventToStamp).getID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.destination = ((Departure) eventToStamp).getDestination();     
        }
        this.timestampID = ++totalTimestamps;
    }

    @Override
    public String toString() {
        String str = String.format(
            "Timestamp " + timestampID + "\n"+
            "             Event: "+eventType+" "+eventID+"\n"+
            "     Time Occurred: "+timeOccurred+"\n"
        );
        if (eventType.equals(Event.ARRIVAL)) {
            str=str+
            "        Arrived At: "+location+"\n"+
            "      Service Time: "+serviceTime+"\n"+
            "Inter-Arrival Time: "+interArrivalTime+"\n"+
            "         Wait Time: "+waitTime+"\n";
        } else {
            str=str+
            "     Departed From: "+location+"\n"+
            "       Destination: "+destination+"\n";
        }
        return str;
    }

}