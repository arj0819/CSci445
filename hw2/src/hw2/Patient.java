package hw1;

import java.util.Random;

public class Patient implements Comparable<Patient>{

    private static int ID = 0;

    private int uniqueID;
    private double waitTime;
    private double serviceTime;

    private boolean inService = false;

    public Patient(double waitTime) {
        this.waitTime = waitTime;
        this.uniqueID = ID;
        ID++;
    }

    public void receiveService(double serviceTime) {
        this.serviceTime = serviceTime;
        inService = true;
    }

    public void receiveDischarge() {
        inService = false;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public boolean isInService() {
        return inService;
    }

    @Override
    public int compareTo(Patient other){
        return 0;
    }
}
        

