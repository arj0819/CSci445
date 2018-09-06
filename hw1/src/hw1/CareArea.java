package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class CareArea {

    protected Queue<Patient> patientQueue = new PriorityQueue<Patient>();
    protected List<Server> availableServers = new ArrayList<Server>();

    protected List<Double> interArrivalTimeLog = new ArrayList<Double>();
    protected List<Double> serviceTimeLog = new ArrayList<Double>();
    protected List<Double> waitTimeLog = new ArrayList<Double>();

    protected double expectedAvgInterArrivalTimePerPatient;
    protected double expectedAvgServiceTimePerPatient;

    private double actualAvgInterArrivalTime;
    private double actualAvgServiceTime;
    private double actualAvgWaitTime;

    protected int totalPatientsServed;
    protected int minutesInOperation;
    protected int averagePatientsInQueue;
    protected int maxQueueLength;
    protected int numOfServers;


    public CareArea(double expectedAvgInterArrivalTimePerPatient, double expectedAvgServiceTimePerPatient, int maxQueueLength, int numOfServers) {
        this.expectedAvgInterArrivalTimePerPatient = expectedAvgInterArrivalTimePerPatient;
        this.expectedAvgServiceTimePerPatient = expectedAvgServiceTimePerPatient;
        this.maxQueueLength = maxQueueLength;
        this.numOfServers = numOfServers;

        for (int i = 0; i < numOfServers; i++) {
            availableServers.add(new Server(expectedAvgServiceTimePerPatient));
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
        Patient nextPatient = patientQueue.element();
        return nextPatient;
    }

    public double servicePatient(Patient nextPatient, double currentMinute) {
        
        double nextDischargeTime = 0.0;

        for (Server possibleServer : availableServers) {
            if (!possibleServer.isOccupied()) {
                nextDischargeTime = possibleServer.service(nextPatient, currentMinute);
                break;
            }
        }
        return nextDischargeTime;
        
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
        Patient happyPatient = null;
        for (int i = 0; i < availableServers.size(); i++) {
            Server currentServer = availableServers.get(i);
            if (!currentServer.getPatientBeingServed().isInService()) {
                happyPatient = currentServer.discharge();

                serviceTimeLog.add(happyPatient.getServiceTime());
                waitTimeLog.add(happyPatient.getWaitTime());

            } else {
                throw new NoSuchElementException("There are no patients to discharge at the moment.");
            }
        }
        return happyPatient;
    }

    public void calculateLogStats() {
        int numOfServiceTimeLogs = serviceTimeLog.size();
        double serviceTimeRunningTotal = 0;

        int numOfWaitTimeLogs = waitTimeLog.size();
        double waitTimeRunningTotal = 0; 

        for (int i = 0; i < numOfServiceTimeLogs; i++) {
            serviceTimeRunningTotal += serviceTimeLog.get(i);
        }
        for (int i = 0; i < numOfWaitTimeLogs; i++) {
            waitTimeRunningTotal += waitTimeLog.get(i);
        }

        actualAvgServiceTime = serviceTimeRunningTotal / numOfServiceTimeLogs;
        actualAvgWaitTime = waitTimeRunningTotal / numOfWaitTimeLogs;
    }

    public int calculateTotalPatientsServed() {
        int numOfServers = availableServers.size();

        for (int i = 0; i < numOfServers; i++) {
            Server currentServer = availableServers.get(i);
            totalPatientsServed += currentServer.getNumOfPatientsServed();
        }
        return totalPatientsServed;
    }

    public int getNumOfServers() {
        return numOfServers;
    }

    public double getShortestWaitTime(double currentMinute) {
        if (currentMinute == 0) {
            return 0.0;
        }
        
        double shortestWaitTime = -1;

        for (Server server : availableServers) {
            if (shortestWaitTime == -1) {
                shortestWaitTime = server.getServiceCompletionTime() - currentMinute;
            } else if (shortestWaitTime >= server.getServiceCompletionTime() - currentMinute) {
                shortestWaitTime = server.getServiceCompletionTime() - currentMinute;
            }
        }
        return shortestWaitTime;
    }

    public Server getServerReadyToDischarge(double nextDischargeTime) {
        Server serverReadyToDischarge = null;
        for (Server server : availableServers) {
            if (server.getServiceCompletionTime() == nextDischargeTime) {
                serverReadyToDischarge = server;
            }
        }
        return serverReadyToDischarge;
    }

    public String report() {
        calculateLogStats();
        calculateTotalPatientsServed();
        return "Total Patients Served: " + totalPatientsServed + "\nAverage Wait Time: " + actualAvgWaitTime + "\nAverage Service Time: " + actualAvgServiceTime;
    }

}