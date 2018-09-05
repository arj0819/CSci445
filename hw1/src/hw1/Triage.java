package hw1;

import java.util.List;
import java.util.ArrayList;

public class Triage extends CareArea implements Hospitable {

    public static final int NUM_OF_SERVERS = 1;

    public Triage(int avgInterArrivalTimePerPatient, int avgServiceTimePerPatient, int numberOfServers) {
        super(avgInterArrivalTimePerPatient, avgServiceTimePerPatient, NUM_OF_SERVERS);
    }

    public Patient recievePatient() {
        Patient nextPatient = (Patient)patientQueue.element();
        return nextPatient;
    }

    public boolean servicePatient(Server server, Patient nextPatient) {
        if (!server.isOccupied()) {
            server.service(nextPatient);
            return true;
        } else {
            return false;
        }
    }

    public boolean dischargePatient(Server server) {
        Patient happyPatient = server.discharge();

        return true;
    }

}