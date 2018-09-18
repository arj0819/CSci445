package hw2;

public class CareArea {

    private int numOfServers = 0;
    private int availableServers = 0;

    private double probabilityOfTransfer = 0.0;
    private double expectedMeanServiceTime = 0.0;
    private double expectedMeanInterArrivalTime = 0.0;

    private double actualMeanServiceTime = 0.0;
    private double actualMeanInterArrivalTime = 0.0;

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