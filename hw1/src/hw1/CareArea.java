package hw1;

import java.util.Queue;
import java.util.PriorityQueue;

public abstract class CareArea implements Hospitable {

    private Queue patientQueue = new PriorityQueue<Patient>();

    private int maxInterArrivalTime;
    private int maxServiceTime;

    private int patientsServiced;

    private int minutesInOperation;

    private int averagePatientsInQueue;


    public CareArea(int maxInterArrivalTime, int maxServiceTime) {
        
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