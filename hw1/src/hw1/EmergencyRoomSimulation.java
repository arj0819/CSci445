package hw1;

import java.lang.Math;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EmergencyRoomSimulation {

    private static double nextArrivalTime;
    private static double nextDischargeTime;

    private static double expectedAvgInterArrivalTimePerPatient;
    private static double expectedAvgServiceTimePerPatient;
    private static int maxQueueLength;
    private static int maxDischarges;

    private static boolean endSimulation = false;

    private static List<CareArea> availableCareAreas = new ArrayList<CareArea>();

    private static List<Integer> patientsInQueueList = new ArrayList<Integer>();

    private static Random rand = new Random();

    public static void main(String[] args) {

        expectedAvgInterArrivalTimePerPatient = Double.parseDouble(args[0]);
        expectedAvgServiceTimePerPatient = Double.parseDouble(args[1]);
        maxQueueLength = Integer.parseInt(args[2]);
        maxDischarges = Integer.parseInt(args[3]);

        CareArea triageRoom = new Triage(expectedAvgInterArrivalTimePerPatient, 
                                         expectedAvgServiceTimePerPatient, 
                                         maxQueueLength);

        availableCareAreas.add(triageRoom);

        EventScheduler eventScheduler = new EventScheduler(expectedAvgInterArrivalTimePerPatient, 
                                                           expectedAvgServiceTimePerPatient,
                                                           maxDischarges);

        // for (int i = 0; i < 50; i++) {
        //     eventScheduler.generateNextEventSequence();
        //     //currentMinute += eventScheduler.getNextEvent().getEventTime();
        // }

        while (!eventScheduler.generateNextEventSequence()){}

        //eventScheduler.printEventLists();

        double simulationLength = 0.0;

        int patientCounter = 0;
        double totalPatientsInQueue = 0;
        double avgPatientsInQueue = 0.0;

        while (eventScheduler.getEventQueue().size() != 0) {
            Event currentEvent = eventScheduler.getEventQueue().remove();
            //System.out.println("-------------\nEVENT TYPE: " + currentEvent.getEventType() + "\nEVENT TIME: "+currentEvent.getEventTime()+ "\nEVENT DURATION: "+currentEvent.getEventDuration());
            if (eventScheduler.getEventQueue().size() == 0) {
                simulationLength = currentEvent.getEventTime();
            }

            if (currentEvent.getEventType().equals("Arrival")) {
                patientCounter++;
            } else {
                patientCounter--;
            }
            patientsInQueueList.add(patientCounter);
        }

        System.out.println("\n(All units are in minutes)");
        eventScheduler.printAvgWaitTime();
        eventScheduler.printActualAvgInterArrivalTime();
        eventScheduler.printActualAvgServiceTime();
        System.out.printf("Simulation Length: %.3f\n", simulationLength);

        for (int i = 0; i < patientsInQueueList.size(); i++) {
            totalPatientsInQueue += patientsInQueueList.get(i);
        }
        avgPatientsInQueue = totalPatientsInQueue / patientsInQueueList.size();
        System.out.printf("Average Patients In Queue: %.3f\n", avgPatientsInQueue);
        System.out.printf("Patients Still In Queue: %d", patientCounter);
    // public void printAvgPatientsWaitingInQueue() {

    // }

    // public void printNumOfPatientsLeftInQueue() {

    // }


        // while (!endSimulation) {

        //     if (nextArrivalTime <= nextDischargeTime) {
        //         nextArrivalTime = Math.log(1-rand.nextDouble())/(-1/expectedAvgServiceTimePerPatient) + currentMinute;
        //         System.out.println("1");
        //         if (!triageRoom.patientArrival(new Patient(triageRoom.getShortestWaitTime(currentMinute)))) {
        //             System.out.println("Patient queue is full. Ending Simulation.");
        //             endSimulation = true;
        //             System.out.println("2");
        //             continue;
        //         }
        //         System.out.println("3");
        //         currentMinute = nextArrivalTime;
        //         System.out.println(currentMinute);

        //         try {
        //             nextDischargeTime = triageRoom.servicePatient(triageRoom.receivePatient(), currentMinute);
        //         } catch (NoSuchElementException e) {
        //             System.out.println("The queue is empty at the moment.");
        //         } catch (Exception e) {
        //             System.out.println(e.getMessage());
        //         }
        //     } else {

        //         System.out.println("4");
        //         try {
        //             Server serverReadyToDischarge = triageRoom.getServerReadyToDischarge(nextDischargeTime);
        //             serverReadyToDischarge.clearPatientForDischarge();
        //         } catch (Exception e) {
        //             System.out.println(e.getMessage());
        //             e.printStackTrace();
        //         }
        
        //         try {
        //             triageRoom.dischargePatient();
        //         } catch (NoSuchElementException e) {
        //             System.out.println(e.getMessage());
        //         } catch (Exception e) {
        //             System.out.println(e.getMessage());
        //             e.printStackTrace();
        //         }
        //         System.out.println("5");
    
        //         if (triageRoom.calculateTotalPatientsServed() >= 100) {
        //             endSimulation = true;
        //         }
        //     }
        // }


        //System.out.println(triageRoom.report());

    }
}