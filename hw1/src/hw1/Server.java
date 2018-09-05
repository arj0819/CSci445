package hw1;

import java.util.Random;
import java.lang.Math;

public class Server {

    private int avgServiceTimePerPatient;
    private boolean occupied = false;
    private Patient patientBeingServed;
    private int patientsServed = 0;

    private Random rand = new Random();

    public Server(int avgServiceTimePerPatient) {
        this.avgServiceTimePerPatient = avgServiceTimePerPatient;
    }

    public void service(Patient patient) {
        this.patientBeingServed = patient;

        double serviceTimeRequired = Math.log(1-rand.nextDouble())/(-avgServiceTimePerPatient);

        patient.receiveService(serviceTimeRequired);
        occupied = true;
    }

    public Patient discharge() {
        occupied = false;
        patientsServed++;
        return patientBeingServed;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Patient getPatientBeingServed() {
        return patientBeingServed;
    }

    public int getNumOfPatientsServed() {
        return patientsServed;
    }

}
        

