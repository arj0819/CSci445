package hw2;

public class CareArea {

    private int numOfServers = 0;
    private int availableServers = 0;

    private double probabilityOfTransfer = 0.0;
    private double meanServiceTime = 0.0;
    private double meanInterArrivalTime = 0.0;

    public CareArea(int numOfServers, double probabilityOfTransfer, double meanServiceTime) {
        this.numOfServers = numOfServers = availableServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.meanServiceTime = meanServiceTime;
    }

    public CareArea(int numOfServers, double probabilityOfTransfer, double meanServiceTime, double meanInterArrivalTime) {
        this.numOfServers = numOfServers = availableServers;
        this.probabilityOfTransfer = probabilityOfTransfer;
        this.meanServiceTime = meanServiceTime;
        this.meanInterArrivalTime = meanInterArrivalTime;
    }


    @Override
    public String toString() {
        String str = String.format (
            "\n----\\/---- "+this.getClass().getName().replace("hw2.","")+" Area ----\\/----"+
            "\n\nServers -----------------> "+numOfServers+
            "\nMean Service Time -------> "+meanServiceTime+
            "\nProbability of Transfer -> "+probabilityOfTransfer+
            "\n"
        );
        if (this instanceof Triage) {
            str=str+
            "Mean Inter-Arrival Time -> "+meanInterArrivalTime+"\n";
        }
        str=str+"\n----/\\---- "+this.getClass().getName().replace("hw2.","")+" Area ----/\\----\n";
        return str;
    }


}