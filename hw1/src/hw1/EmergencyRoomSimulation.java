package hw1;

import java.util.List;

public class EmergencyRoomSimulation {

    public static int currentMinute = 0;
    public static int nextArrivalTime;

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }

        CareArea triageRoom = new Triage(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(triageRoom.getNumOfServers());

        if (!triageRoom.patientArrival(new Patient(20))) {
            System.out.println("Patient queue is full. Ending Simulation.");
        }

        Patient nextInLine = ((Triage) triageRoom).receivePatient();

        ((Triage) triageRoom).servicePatient((Server)triageRoom.availableServers.get(0), nextInLine);
        ((Triage) triageRoom).dischargePatient((Server)triageRoom.availableServers.get(0));
        System.out.println(triageRoom.report());

    }
}