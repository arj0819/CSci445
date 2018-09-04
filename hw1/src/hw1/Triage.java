package hw1;

import java.util.List;
import java.util.ArrayList;

public class Triage extends CareArea implements Hospitable {

    public static final int NUM_OF_SERVERS = 1;

    public Triage(int maxInterArrivalTime, int maxServiceTime, int numberOfServers) {
        super(maxInterArrivalTime, maxServiceTime, NUM_OF_SERVERS);
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