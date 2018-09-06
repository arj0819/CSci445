package hw1;

import java.util.Random;
import java.lang.Math;

public class Server {

    private boolean occupied = false;
    private Patient patientBeingServed;

    private int patientsServed = 0;

    private double expectedAvgServiceTimePerPatient;
    private double actualAvgServiceTimePerPatient;
    private double serviceCompletionTime;

    private Random rand = new Random();

    public Server(double expectedAvgServiceTimePerPatient) {
        this.expectedAvgServiceTimePerPatient = expectedAvgServiceTimePerPatient;
    }

    public void service(Patient patient, int currentMinute) {
        this.patientBeingServed = patient;

        double serviceTimeRequired = Math.log(1-rand.nextDouble())/(-1/expectedAvgServiceTimePerPatient);

        patient.receiveService(serviceTimeRequired);
        this.serviceCompletionTime = currentMinute + serviceTimeRequired;

        occupied = true;
    }

    public void clearPatientForDischarge() {
        patientBeingServed.receiveDischarge();
    }

    public Patient discharge() {
        occupied = false;
        patientsServed++;
        return patientBeingServed;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public double getActualAvgServiceTime() {
        return actualAvgServiceTimePerPatient;
    }

    public Patient getPatientBeingServed() {
        return patientBeingServed;
    }

    public int getNumOfPatientsServed() {
        return patientsServed;
    }

    public double getServiceCompletionTime() {
        return serviceCompletionTime;
    }

}
        

