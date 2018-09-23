package hw2;

public class Server implements Comparable<Server>{

    private static int totalServers = 0;
    private int serverID = 0;
    private static int patientsServed = 0;

    private String servingIn = "";

    private double totalServiceTime = 0.0;
    private double currentServiceTime = 0.0;

    public Server(String careAreaName) {
        this.serverID = ++totalServers;
        this.servingIn = careAreaName;
    }

    public void serve(double serviceTime) {
        this.currentServiceTime = serviceTime;
        totalServiceTime += serviceTime;
        patientsServed++;
    }

    public double getTotalServiceTime() {
        return totalServiceTime;
    }

    public double getCurrentServiceTime() {
        return currentServiceTime;
    }

    public int getID() {
        return serverID;
    }

    public String getServiceArea() {
        return servingIn;
    }
    
    public static int getPatientsServed() {
        return patientsServed;
    }

    @Override
    public int compareTo(Server otherServer){
        int result = 0;
        
        if (this.getTotalServiceTime() < otherServer.getTotalServiceTime()) {
            result = -1;
        } else if (this.getTotalServiceTime() == otherServer.getTotalServiceTime()) {
            result = 1;
        } else {
            result = 1;
        }
        return result;
    }
}