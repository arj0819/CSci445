package hw2;

public class Arrival extends Event {

    private static int totalArrivals = 0;
    private int arrivalID = 0;

    private static int totalPatients = 0;
    private int patientID = 0;


    private boolean transferRequired = false;
    
    public Arrival(double timeArrived, double interArrivalTime, double serviceTime, double waitTime, boolean transferRequired, String location) {
        super(timeArrived, interArrivalTime, serviceTime, waitTime, location);
        this.transferRequired = transferRequired;
        this.arrivalID = ++totalArrivals;
        this.patientID = ++totalPatients;
        System.out.println("ARRIVAL "+this.arrivalID+" CONSTRUCTED");
    }

    public Arrival(int patientID, double timeArrived, double interArrivalTime, double serviceTime, double waitTime, boolean transferRequired, String location) {
        super(timeArrived,interArrivalTime,serviceTime,waitTime,location);
        this.arrivalID = ++totalArrivals;
        this.patientID = patientID;
    }

    public static int getTotalArrivals() {
        return totalArrivals;
    }

    public int getID() {
        return arrivalID;
    }

    public int getPatientID() {
        return patientID;
    }

    public boolean mustTransfer() {
        return transferRequired;
    }
}