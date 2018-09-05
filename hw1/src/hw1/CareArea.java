package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class CareArea {

    protected Queue patientQueue = new PriorityQueue<Patient>();
    protected List availableServers = new ArrayList<Server>();
    protected List serviceTimeLog = new ArrayList<Integer>();
    protected List waitTimeLog = new ArrayList<Integer>();

    protected int avgInterArrivalTimePerPatient;
    protected int avgServiceTimePerPatient;

    private int actualAvgServiceTime;
    private int actualAvgWaitTime;

    protected int totalPatientsServiced;

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

    public void calculateLogStats() {

        int numOfServiceTimeLogs = (Integer)serviceTimeLog.size();
        int serviceTimeRunningTotal = 0;

        int numOfWaitTimeLogs = (Integer)waitTimeLog.size();
        int waitTimeRunningTotal = 0; 

        for (int i = 0; i < numOfServiceTimeLogs; i++) {
            serviceTimeRunningTotal += (Integer)serviceTimeLog.get(i);
        }
        for (int i = 0; i < numOfWaitTimeLogs; i++) {
            waitTimeRunningTotal += (Integer)waitTimeLog.get(i);
        }

        actualAvgServiceTime = serviceTimeRunningTotal / numOfServiceTimeLogs;
        actualAvgWaitTime = waitTimeRunningTotal / numOfWaitTimeLogs;

    }
}