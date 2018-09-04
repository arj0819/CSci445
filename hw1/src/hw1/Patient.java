package hw1;

import java.util.Random;

public class Patient {

    private double waitTime;
    private double serviceTime;

    public Patient(double waitTime) {
        this.waitTime = waitTime;
    }

    public void receiveService(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

}
        

