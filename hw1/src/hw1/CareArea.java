package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class CareArea {

    protected Queue patientQueue = new PriorityQueue<Patient>();
    protected List availableServers = new ArrayList<Server>();

    protected int avgInterArrivalTimePerPatient;
    protected int avgServiceTimePerPatient;

    protected int patientsServiced;

    protected int minutesInOperation;

    protected int averagePatientsInQueue;


    public CareArea(int avgInterArrivalTimePerPatient, int avgServiceTimePerPatient, int numberOfServers) {
        this.avgInterArrivalTimePerPatient = avgInterArrivalTimePerPatient;
        this.avgServiceTimePerPatient = avgServiceTimePerPatient;

        for (int i = 0; i < numberOfServers; i++) {
            availableServers.add(new Server(avgServiceTimePerPatient));
        }
    }

    public boolean patientArrival(Patient newPatient) {
        if (patientQueue.offer(newPatient)) {
            return true;
        } else {
            return false;
        }
    }
}