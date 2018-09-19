package hw2;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Queue;
// import java.util.PriorityQueue;

public class CareArea {

    private int numOfServers = 0;
    private int availableServers = 0;
    private int patientsServiced = 0;

    private double probabilityOfTransfer = 0.0;
    private double expectedMeanServiceTime = 0.0;
    private double expectedMeanInterArrivalTime = 0.0;

    private double actualMeanServiceTime = 0.0;
    private double actualMeanInterArrivalTime = 0.0;

    // private Queue<Event> arrivals = new PriorityQueue<Event>();
    // private List<Event> departures = new ArrayList<Departure>();

    public CareArea(int numOfServers, double probabilityOfTransfer, double expectedMeanServiceTime) {
        this.numOfServers = numOfServers = availableServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.expectedMeanServiceTime = expectedMeanServiceTime;
    }

    public CareArea(int numOfServers, double probabilityOfTransfer, double expectedMeanServiceTime, double expectedMeanInterArrivalTime) {
        this.numOfServers = numOfServers = availableServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.expectedMeanServiceTime = expectedMeanServiceTime;
        this.expectedMeanInterArrivalTime = expectedMeanInterArrivalTime;
    }

    public boolean servicePatient() {
        if (availableServers > 0) {
            availableServers--;
            patientsServiced++;
            return true;
        } else {
            return false;
        }
    }

    public void releasePatient() {
        if(availableServers < numOfServers) {
            availableServers++;
        }
    }


    @Override
    public String toString() {
        String str = String.format (
            "\n--------\\/-------- "+this.getClass().getName().replace("hw2.","")+" Area --------\\/--------"+
            "\n\nServers ------------------------> "+numOfServers+
            "\nExpected Mean Service Time -----> "+expectedMeanServiceTime+
            "\nProbability of Transfer --------> "+probabilityOfTransfer+
            "\n"
        );
        if (this instanceof Triage) {
            str=str+
            "Expected Mean Inter-Arrival Time -> "+expectedMeanInterArrivalTime+"\n";
        }
        str=str+"\n--------/\\-------- "+this.getClass().getName().replace("hw2.","")+" Area --------/\\--------\n";
        return str;
    }

}