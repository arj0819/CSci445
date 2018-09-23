package hw2;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Hashtable;

public class CareArea {

    public static final String TRIAGE = "Triage";
    public static final String TRAUMA = "Trauma";
    public static final String ACUTE = "Acute";
    public static final String PROMPT = "Prompt";

    private Queue<Server> serverQueue = new PriorityQueue<Server>();
    private static Hashtable<Integer,Server> serverTable = new Hashtable<Integer,Server>();

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
        createServers(numOfServers);
    }

    public CareArea(int numOfServers, double probabilityOfTransfer, double expectedMeanServiceTime, double expectedMeanInterArrivalTime) {
        this.numOfServers = this.availableServers = numOfServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.expectedMeanServiceTime = expectedMeanServiceTime;
        this.expectedMeanInterArrivalTime = expectedMeanInterArrivalTime;
        createServers(numOfServers);
    }

    private void createServers(int numOfServers) {
        for (int i = 0; i < numOfServers; i++) {
            Server s = new Server(this.getClass().getName().replace("hw2.",""));
            serverTable.put(s.getID(),s);
            serverQueue.add(s);
        }
    }

    public static Hashtable<Integer,Server> getServerTable() {
        return serverTable;
    }

    public Server getNextAvailableServer() {
        return serverQueue.remove();
    }

    public void establishService(Server server, double serviceTime) {
        server.serve(serviceTime);
        serverTable.replace(server.getID(),server);
        serverQueue.add(server);
    }

    public boolean isServiceAvailable() {
        if (availableServers > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void servicePatient() {
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

    public static String avgServerUtilization(double simEndTime) {
        String str = "";
        for (Integer serverID : serverTable.keySet()) {
            double avgUtilization = 
            serverTable.get(serverID).getTotalServiceTime()/simEndTime;
            String formatter = "";
            if (serverTable.get(serverID).getServiceArea().equals(CareArea.ACUTE)) {
                formatter = "%16s";
            } else {
                formatter = "%17s";
            }
            str=str+
            "      Server %2d Serving Area: "+formatter+"\n"+
            "      Server %2d Service Time: %10.3f %6s\n"+
            "      Server %2d  Utilization: %9.2f %8s\n";
            str = String.format(
                str, 
                serverID, 
                serverTable.get(serverID).getServiceArea(),
                serverID, 
                serverTable.get(serverID).getTotalServiceTime(),
                Timestamp.TIME_UNIT,
                serverID, 
                avgUtilization,
                "percent"
            );
        }
        return str;
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
