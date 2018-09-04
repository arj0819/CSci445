package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public abstract class CareArea implements Hospitable {

    private Queue patientQueue = new PriorityQueue<Patient>();
    private List availableServers = new ArrayList<Server>();

    private int maxInterArrivalTime;
    private int maxServiceTime;

    private int patientsServiced;

    private int minutesInOperation;

    private int averagePatientsInQueue;


    public CareArea(int maxInterArrivalTime, int maxServiceTime, int numberOfServers) {
        this.maxInterArrivalTime = maxInterArrivalTime;
        this.maxServiceTime = maxServiceTime;

        for (int i = 0; i < numberOfServers; i++) {
            availableServers.add(new Server());
        }
    }

    public boolean recievePatient() {
        return true;
    }

    public boolean servicePatient() {
        return true;
    }

    public boolean dischargePatient() {
        return true;
    }

}