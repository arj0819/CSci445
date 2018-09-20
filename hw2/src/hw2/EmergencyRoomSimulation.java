package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.Random;

public class EmergencyRoomSimulation {

    static double currentTime = 0.0;
    static int totalSimTime = 0;

    private static Hashtable<String,CareArea> emergencyDept = new Hashtable<String,CareArea>();
    private static Hashtable<String,Double> transferProbabilities = new Hashtable<String,Double>();
    private static Queue<Event> events = new PriorityQueue<Event>();
    private static List<Event> arrivals = new ArrayList<Event>();
    private static List<Event> departures = new ArrayList<Event>();

    private static Random rand = new Random();

    public static void main(String[] args) {

        // Set up the Care Areas!
        try {
            File simInput = new File("src/hw2/simInput.txt");
            BufferedReader rdr = new BufferedReader(new FileReader(simInput));

            String currentLine = rdr.readLine();
            totalSimTime = Integer.parseInt(currentLine);

            String[] tokens = rdr.readLine().split(" ");
            int numServers = Integer.parseInt(tokens[0]);
            double probOfTransfer = Double.parseDouble(tokens[3]);
            double serviceMean = Double.parseDouble(tokens[2]);
            double interArrivalMean = Double.parseDouble(tokens[1]);
            CareArea triage = new Triage (numServers,probOfTransfer,serviceMean,interArrivalMean);

            emergencyDept.put(CareArea.TRIAGE, triage);
            transferProbabilities.put(CareArea.TRIAGE, probOfTransfer);

            tokens = rdr.readLine().split(" ");
            numServers = Integer.parseInt(tokens[0]);
            probOfTransfer = Double.parseDouble(tokens[2]);
            serviceMean = Double.parseDouble(tokens[1]);
            CareArea trauma = new Trauma (numServers,probOfTransfer,serviceMean);

            emergencyDept.put(CareArea.TRAUMA, trauma);
            transferProbabilities.put(CareArea.TRAUMA, probOfTransfer);

            tokens = rdr.readLine().split(" ");
            numServers = Integer.parseInt(tokens[0]);
            probOfTransfer = Double.parseDouble(tokens[2]);
            serviceMean = Double.parseDouble(tokens[1]);
            CareArea acute = new Acute (numServers,probOfTransfer,serviceMean);

            emergencyDept.put(CareArea.ACUTE, acute);
            transferProbabilities.put(CareArea.ACUTE, probOfTransfer);

            tokens = rdr.readLine().split(" ");
            numServers = Integer.parseInt(tokens[0]);
            probOfTransfer = Double.parseDouble(tokens[2]);
            serviceMean = Double.parseDouble(tokens[1]);
            CareArea prompt = new Prompt (numServers,probOfTransfer,serviceMean);

            emergencyDept.put(CareArea.PROMPT, prompt);
            transferProbabilities.put(CareArea.PROMPT, probOfTransfer);

            rdr.close();

        } catch (Exception e) {
            System.err.println("Invalid file format provided.");
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // Iterate through the Care Areas to test if things worked!
        for (String area : emergencyDept.keySet()) {
            System.out.println(emergencyDept.get(area));
        }

        // Schedule and run through all of the Events!
        double nextInterArrivalTime = 0.0;
        double nextServiceTime = 0.0;
        double nextArrivalTime = 0.0;
        double prevDepartureTime = 0.0;
        double nextDepartureTime = 0.0;
        double nextWaitTime = 0.0;
        double actualAvgIntArrTime = 0.0;
        double actualAvgSrvcTime = 0.0;

        Event currentEvent = null;

        int iterations=0;

        while (currentTime <= totalSimTime) {

            if (currentEvent instanceof Arrival || currentTime == 0.0) {
                emergencyDept.get(CareArea.TRIAGE).servicePatient();

                for (int i = 0; i < departures.size(); i++) {
                    if (i == departures.size() - 1) {
                        prevDepartureTime = departures.get(i).getTimeDeparted();
                    }
                }
                
                nextInterArrivalTime = scheduleInterArrivalTime(emergencyDept.get(CareArea.TRIAGE));
                nextServiceTime = scheduleServiceTime(emergencyDept.get(CareArea.TRIAGE));
                nextArrivalTime = currentTime + nextInterArrivalTime;
                nextWaitTime = prevDepartureTime <= nextArrivalTime || emergencyDept.get(CareArea.TRIAGE).isServiceAvailable() ?
                            0.0 : prevDepartureTime - nextArrivalTime;
                nextDepartureTime = nextArrivalTime + nextServiceTime + nextWaitTime;
                
                //events should be given a CareArea name as occurrance location
                //so we can tell where each event should take place in
                
                Event nextArrival = new Arrival(nextArrivalTime, nextInterArrivalTime, nextServiceTime, nextWaitTime, false, CareArea.TRIAGE);
                Event nextDeparture = new Departure(nextDepartureTime, CareArea.TRIAGE);

                System.out.println("       Current Time: "+currentTime);
                System.out.println("  Next Int-Arr Time: "+nextInterArrivalTime);
                System.out.println("  Next Arrival Time: "+nextArrivalTime);
                System.out.println("  Next Service Time: "+nextServiceTime);
                System.out.println("Prev Departure Time: "+prevDepartureTime);
                System.out.println("     Next Wait Time: "+nextWaitTime);
                System.out.println("Next Departure Time: "+nextDepartureTime);

                events.add(nextArrival);
                events.add(nextDeparture);

                arrivals.add(nextArrival);
                departures.add(nextDeparture);

                currentEvent = events.remove();
                currentTime = currentEvent.getTimeOccurred();

                //System.out.println("       Current Time: "+currentTime+"\n\n");

                actualAvgIntArrTime+=nextInterArrivalTime;
                actualAvgSrvcTime+=nextServiceTime;
            } else {
                emergencyDept.get(CareArea.TRIAGE).dischargePatient();
                //System.out.println("Current Time: "+currentTime+"\n\n");
                currentEvent = events.remove();
                currentTime = currentEvent.getTimeOccurred();
                
            }
            if (currentTime >= totalSimTime) {
                System.out.println("\n");
                break;
            }
            if (currentEvent instanceof Arrival) {
                System.out.println("\nARRIVAL "+((Arrival)currentEvent).getID()+" occurred in "+currentEvent.getLocation());
                System.out.println("Current Time: "+currentTime+"\n");
            } else {
                System.out.println("\nDEPARTURE "+((Departure)currentEvent).getID()+" occurred in "+currentEvent.getLocation());
                System.out.println("Current Time: "+currentTime+"\n");
            }
            //break;
            //iterations++;
        }

        System.out.println("           Simulation ended at: "+currentTime);
        System.out.println("       Actual Avg Int-Arr Time: "+ actualAvgIntArrTime/Arrival.getTotalArrivals());
        System.out.println("       Actual Avg service Time: "+ actualAvgSrvcTime/Departure.getTotalDepartures());
        System.out.println("             Arrivals Occurred: "+ arrivals.size());
        System.out.println("           Departures Occurred: "+ departures.size());
        System.out.println("Patients Still In Triage Queue: "+ 0);
        System.out.println("      Number of Waits Occurred: "+ Event.getNumOfWaits());
        System.out.println("             Average Wait Time: "+ Event.getAverageWaitTime());

    }

    public static double scheduleInterArrivalTime(CareArea triage) {
        return Math.log(1-rand.nextDouble())/(-1/triage.getExpMeanInterArrivalTime());
    }

    public static double scheduleServiceTime(CareArea area) {
        return Math.log(1-rand.nextDouble())/(-1/area.getExpMeanServiceTime());
    }

    public static String schedulePatientServiceArea(Hashtable<String,Double> transferProbabilities) {
        double transferValue = rand.nextDouble();

        double probLeaveTriage = transferProbabilities.get(CareArea.TRIAGE);
        double probTransTrauma = transferProbabilities.get(CareArea.TRAUMA);
        double probTransAcute  = transferProbabilities.get(CareArea.ACUTE);
        double probTransPrompt = transferProbabilities.get(CareArea.PROMPT);

        if (transferValue < probLeaveTriage) {
            return CareArea.TRIAGE;
        } else if (transferValue < probLeaveTriage + probTransTrauma) {
            return CareArea.TRAUMA;
        } else if (transferValue < probLeaveTriage + probTransTrauma + probTransAcute) {
            return CareArea.ACUTE;
        } else {
            return CareArea.PROMPT;
        }

    }

}