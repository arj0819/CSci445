package hw1;

import java.util.List;
import java.util.NoSuchElementException;

public class EmergencyRoomSimulation {

    public static int currentMinute = 0;
    public static int nextArrivalTime;

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }

        CareArea triageRoom = new Triage(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        System.out.println(triageRoom.getNumOfServers());

        if (!triageRoom.patientArrival(new Patient(triageRoom.getShortestWaitTime(currentMinute)))) {
            System.out.println("Patient queue is full. Ending Simulation.");
        }

        try {
            triageRoom.servicePatient(triageRoom.receivePatient(), currentMinute);
        } catch (NoSuchElementException e) {
            System.out.println("The queue is empty at the moment.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Server testServer = triageRoom.availableServers.get(0);
        testServer.clearPatientForDischarge();

        try {
            triageRoom.dischargePatient();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(triageRoom.report());

    }
}