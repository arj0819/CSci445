package hw2;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Queue;
// import java.util.PriorityQueue;

public class CareArea {

    public static final String TRIAGE = "Triage";
    public static final String TRAUMA = "Trauma";
    public static final String ACUTE = "Acute";
    public static final String PROMPT = "Prompt";

    private int numOfServers = 0;
    private int availableServers = 0;
    private int patientsServed = 0;

    //array of doubles to keep track of each server's busy time
    private double probabilityOfTransfer = 0.0;
    private double expectedMeanServiceTime = 0.0;
    private double expectedMeanInterArrivalTime = 0.0;

    private double actualMeanServiceTime = 0.0;
    private double actualMeanInterArrivalTime = 0.0;

    // private Queue<Event> arrivals = new PriorityQueue<Event>();
    // private List<Event> departures = new ArrayList<Departure>();

    public CareArea(int numOfServers, double probabilityOfTransfer, double expectedMeanServiceTime) {
        this.numOfServers = this.availableServers = numOfServers;
        this.availableServers = numOfServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.expectedMeanServiceTime = expectedMeanServiceTime;
    }

    public CareArea(int numOfServers, double probabilityOfTransfer, double expectedMeanServiceTime, double expectedMeanInterArrivalTime) {
        this.numOfServers = this.availableServers = numOfServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.expectedMeanServiceTime = expectedMeanServiceTime;
        this.expectedMeanInterArrivalTime = expectedMeanInterArrivalTime;
    }

    public boolean isServiceAvailable() {
        if (availableServers > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void servicePatient() {
        System.out.println("Servers Available: "+this.availableServers);
        if (availableServers > 0) {
            availableServers--;
            patientsServed++;
        }
    }

    public int getAvailableServers() {
        return availableServers;
    }

    public void dischargePatient() {
        if(availableServers < numOfServers) {
            availableServers++;
        }
    }

    public double getExpMeanInterArrivalTime() {
        return expectedMeanInterArrivalTime;
    }

    public double getExpMeanServiceTime() {
        return expectedMeanServiceTime;
    }


    @Override
    public String toString() {

        String areaType = "";
        boolean isTriage = false;
        if (this instanceof Triage) {
            areaType = CareArea.TRIAGE;
            isTriage = true;
        } else if (this instanceof Trauma) {
            areaType = CareArea.TRAUMA;
        } else if (this instanceof Acute) {
            areaType = CareArea.ACUTE;
        } else {
            areaType = CareArea.PROMPT;
        }

        String str = String.format (
            "\n--------\\/-------- "+areaType+" Area --------\\/--------"+
            "\n\nServers ---------------------------------> "+numOfServers+
            "\nPatients Served -------------------------> "+patientsServed+
            "\nExpected Mean Service Time --------------> "+expectedMeanServiceTime+
            "\nActual Mean Service Time ----------------> "+actualMeanServiceTime+
            "\n"
        );
        if (isTriage) {
            str=str+
            "Expected Mean Inter-Arrival Time --------> "+expectedMeanInterArrivalTime+
            "\nActual Mean Inter-Arrival Time ----------> "+actualMeanInterArrivalTime+
            "\nProb. of Discharge from Emergency Dept. -> "+probabilityOfTransfer+"\n";
        } else {
            str=str+
            "Prob. of Transfer from Triage Area ------> "+probabilityOfTransfer+"\n";
        }
        str=str+"\n--------/\\-------- "+areaType+" Area --------/\\--------\n";
        return str;
    }

}
