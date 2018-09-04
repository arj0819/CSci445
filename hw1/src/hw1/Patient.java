package hw1;

import java.util.Random;

public class Patient {

    private int waitTime;
    private int serviceTime;

    public Patient(int waitTime, int serviceTime) {
        this.waitTime = waitTime;
        this.serviceTime = serviceTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

}
        

