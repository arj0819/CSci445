package hw1;

import java.lang.Math;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EmergencyRoomSimulation {

    private static double currentMinute = 0;
    private static double nextArrivalTime;
    private static double nextDischargeTime;

    private static double expectedAvgInterArrivalTimePerPatient;
    private static double expectedAvgServiceTimePerPatient;
    private static int maxQueueLength;

    private static boolean endSimulation = false;

    private static List<CareArea> availableCareAreas = new ArrayList<CareArea>();

    private static Random rand = new Random();

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }


        expectedAvgInterArrivalTimePerPatient = Double.parseDouble(args[0]);
        expectedAvgServiceTimePerPatient = Double.parseDouble(args[1]);
        maxQueueLength = Integer.parseInt(args[2]);

        CareArea triageRoom = new Triage(expectedAvgInterArrivalTimePerPatient, 
                                         expectedAvgServiceTimePerPatient, 
                                         maxQueueLength);

        availableCareAreas.add(triageRoom);

        while (!endSimulation) {

            if (nextArrivalTime <= nextDischargeTime) {
                nextArrivalTime = Math.log(1-rand.nextDouble())/(-1/expectedAvgServiceTimePerPatient) + currentMinute;
                System.out.println("1");
                if (!triageRoom.patientArrival(new Patient(triageRoom.getShortestWaitTime(currentMinute)))) {
                    System.out.println("Patient queue is full. Ending Simulation.");
                    endSimulation = true;
                    System.out.println("2");
                    continue;
                }
                System.out.println("3");
                currentMinute = nextArrivalTime;
                System.out.println(currentMinute);

                try {
                    nextDischargeTime = triageRoom.servicePatient(triageRoom.receivePatient(), currentMinute);
                } catch (NoSuchElementException e) {
                    System.out.println("The queue is empty at the moment.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {

                System.out.println("4");
                Server serverReadyToDischarge = triageRoom.getServerReadyToDischarge(nextDischargeTime);
                serverReadyToDischarge.clearPatientForDischarge();
        
                try {
                    triageRoom.dischargePatient();
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
    
                if (triageRoom.calculateTotalPatientsServed() >= 100) {
                    endSimulation = true;
                }
            }
        }


        System.out.println(triageRoom.report());

    }
}