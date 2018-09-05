package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class CareArea {

    protected Queue patientQueue = new PriorityQueue<Patient>();
    protected List availableServers = new ArrayList<Server>();
    protected List serviceTimeLog = new ArrayList<Double>();
    protected List waitTimeLog = new ArrayList<Double>();

    protected int avgInterArrivalTimePerPatient;
    protected int avgServiceTimePerPatient;

    private double actualAvgServiceTime;
    private double actualAvgWaitTime;

    protected int totalPatientsServed;
    protected int minutesInOperation;
    protected int averagePatientsInQueue;
    protected int maxQueueLength;
    protected int numOfServers;


    public CareArea(int avgInterArrivalTimePerPatient, int avgServiceTimePerPatient, int maxQueueLength, int numOfServers) {
        this.avgInterArrivalTimePerPatient = avgInterArrivalTimePerPatient;
        this.avgServiceTimePerPatient = avgServiceTimePerPatient;
        this.maxQueueLength = maxQueueLength;
        this.numOfServers = numOfServers;

        for (int i = 0; i < numOfServers; i++) {
            availableServers.add(new Server(avgServiceTimePerPatient));
        }
    }

    public boolean patientArrival(Patient newPatient) {
        if (patientQueue.offer(newPatient) || patientQueue.size() <= maxQueueLength) {
            return true;
        } else {
            return false;
        }
    }

    public Patient receivePatient() throws NoSuchElementException {
        Patient nextPatient = (Patient)patientQueue.element();
        return nextPatient;
    }

    public boolean servicePatient(Patient nextPatient) {
        
        for (Server possibleServer : availableServers) {
            if (!possibleServer.isOccupied()) {
                possibleServer.service(nextPatient);
                return true;
            } else {
                return false;
            }
        }
        
        // for (int i = 0; i < availableServers.size(); i++) {
        //     Server possibleServer = availableServers.get(i);
        //     if (!possibleServer.isOccupied()) {
        //         possibleServer.service(nextPatient);
        //         return true;
        //     } else {
        //         return false;
        //     }
        // }
    }

    public Patient dischargePatient() throws NoSuchElementException {
        Patient happyPatient;
        for (int i = 0; i < availableServers.size(); i++) {
            Server currentServer = availableServers.get(i);
            if (!currentServer.getPatientBeingServed().isInService()) {
                happyPatient = currentServer.discharge();

                serviceTimeLog.add(happyPatient.getServiceTime());
                waitTimeLog.add(happyPatient.getWaitTime());

                return happyPatient;
            } else {
                throw new NoSuchElementException("There are no patients to discharge at the moment.");
            }
        }

    }

    public void calculateLogStats() {
        int numOfServiceTimeLogs = serviceTimeLog.size();
        double serviceTimeRunningTotal = 0;

        int numOfWaitTimeLogs = waitTimeLog.size();
        double waitTimeRunningTotal = 0; 

        for (int i = 0; i < numOfServiceTimeLogs; i++) {
            serviceTimeRunningTotal += (Double)serviceTimeLog.get(i);
        }
        for (int i = 0; i < numOfWaitTimeLogs; i++) {
            waitTimeRunningTotal += (Double)waitTimeLog.get(i);
        }

        actualAvgServiceTime = serviceTimeRunningTotal / numOfServiceTimeLogs;
        actualAvgWaitTime = waitTimeRunningTotal / numOfWaitTimeLogs;
    }

    public void calculateTotalPatientsServed() {
        int numOfServers = availableServers.size();

        for (int i = 0; i < numOfServers; i++) {
            Server currentServer = (Server)availableServers.get(i);
            totalPatientsServed += currentServer.getNumOfPatientsServed();
        }
    }

    public int getNumOfServers() {
        return numOfServers;
    }

    public String report() {
        calculateLogStats();
        calculateTotalPatientsServed();
        return "Total Patients Served: " + totalPatientsServed + "\nAverage Wait Time: " + actualAvgWaitTime + "\nAverage Service Time: " + actualAvgServiceTime;
    }

}