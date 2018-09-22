package hw2;

import java.util.Hashtable;

public class Timestamp {

    private static int totalTriageArrivals = 0;
    private static int totalTraumaArrivals = 0;
    private static int totalAcuteArrivals = 0;
    private static int totalPromptArrivals = 0;
    private static int totalTriageDepartures = 0;
    private static int totalTraumaDepartures = 0;
    private static int totalAcuteDepartures = 0;
    private static int totalPromptDepartures = 0;

    private int currentTriageArrivals = 0;
    private int currentTraumaArrivals = 0;
    private int currentAcuteArrivals = 0;
    private int currentPromptArrivals = 0;
    private int currentTriageDepartures = 0;
    private int currentTraumaDepartures = 0;
    private int currentAcuteDepartures = 0;
    private int currentPromptDepartures = 0;

    private static int totalTimestamps = 0;
    private int timestampID = 0;
    private String eventType = "";
    private int eventID;

    private double timeOccurred = 0.0;
    
    private String location = "";
    private String destination = "";

    private double serviceTime = 0.0;
    private double interArrivalTime = 0.0;
    private double waitTime = 0.0;

    //private static int numberOfWaits = 0;
    //private static double totalWaitTime = 0.0;


    public Timestamp(Event eventToStamp, Hashtable<String,CareArea> emergencyDept) {
        if (eventToStamp instanceof Arrival) {
            this.eventType = Event.ARRIVAL;
            this.eventID = ((Arrival) eventToStamp).getID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.serviceTime = eventToStamp.getServiceTime();
            this.interArrivalTime = eventToStamp.getInterArrivalTime();
            this.waitTime = eventToStamp.getWaitTime();
            currentTriageDepartures = totalTriageDepartures;
            currentTraumaDepartures = totalTraumaDepartures;
            currentAcuteDepartures  = totalAcuteDepartures;
            currentPromptDepartures = totalPromptDepartures;

            if (location.equals(CareArea.TRIAGE)) {
                currentTriageArrivals = ++totalTriageArrivals;
            } else if (location.equals(CareArea.TRAUMA)) {
                currentTraumaArrivals = ++totalTraumaArrivals;
            } else if (location.equals(CareArea.ACUTE)) {
                currentAcuteArrivals = ++totalAcuteArrivals;
            } else {
                currentPromptArrivals = ++totalPromptArrivals;
            }

        } else {
            this.eventType = Event.DEPARTURE;
            this.eventID = ((Departure) eventToStamp).getID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.destination = ((Departure) eventToStamp).getDestination();
            currentTriageArrivals = totalTriageArrivals;
            currentTraumaArrivals = totalTraumaArrivals;
            currentAcuteArrivals  = totalAcuteArrivals;
            currentPromptArrivals = totalPromptArrivals;

            if (location.equals(CareArea.TRIAGE)) {
                currentTriageDepartures = ++totalTriageDepartures;
            } else if (location.equals(CareArea.TRAUMA)) {
                currentTraumaDepartures = ++totalTraumaDepartures;
            } else if (location.equals(CareArea.ACUTE)) {
                currentAcuteDepartures = ++totalAcuteDepartures;
            } else {
                currentPromptDepartures = ++totalPromptDepartures;
            }
                
        }
        this.timestampID = ++totalTimestamps;
    }

    private int triageQueuePatientsRemaining() {
        int result = (currentTriageArrivals-1) - currentTriageDepartures;
        if (result >= 0) {
            return result;
        } else {
            return 0;
        }
    }
    private int traumaQueuePatientsRemaining() {
        int result = (currentTraumaArrivals-1) - currentTraumaDepartures;
        if (result >= 0) {
            return result;
        } else {
            return 0;
        }
    }
    private int acuteQueuePatientsRemaining() {
        int result = (currentAcuteArrivals-1) - currentAcuteDepartures;
        if (result >= 0) {
            return result;
        } else {
            return 0;
        }
    }
    private int promptQueuePatientsRemaining() {
        int result = (currentPromptArrivals-1) - currentPromptDepartures;
        if (result >= 0) {
            return result;
        } else {
            return 0;
        }
    }
    private int totalArrivalsSoFar() {
        return currentTriageArrivals+
               currentTraumaArrivals+
               currentAcuteArrivals+
               currentPromptArrivals;
    }
    private int totalDeparturesSoFar() {
        return currentTriageDepartures+
               currentTraumaDepartures+
               currentAcuteDepartures+
               currentPromptDepartures;
    }

    @Override
    public String toString() {
        String str = String.format(
            "Timestamp " + timestampID + "\n"+
            "                     Event: "+eventType+" "+eventID+"\n"
        );
        if (eventType.equals(Event.ARRIVAL)) {
            str=str+
            "               Arrived At: %s"    +"\n"+
            "            Time Occurred: %10.3f"+"\n"+
            "       Inter-Arrival Time: %10.3f"+"\n"+
            "             Service Time: %10.3f"+"\n"+
            "                Wait Time: %10.3f"+"\n";
            str = String.format (
                str,
                location,
                timeOccurred,
                interArrivalTime,
                serviceTime,
                waitTime
            );
        } else {
            str=str+
            "            Departed From: %s"+"\n"+
            "            Time Occurred: %.3f"+"\n"+
            "              Destination: %s"+"\n";
            str = String.format (
                str,
                location, 
                timeOccurred, 
                destination
            );
        }
            str=str+
            "       Arrivals in Triage: %6d"+"\n"+
            "       Arrivals in Trauma: %6d"+"\n"+
            "        Arrivals in Acute: %6d"+"\n"+
            "       Arrivals in Prompt: %6d"+"\n"+
            "  Total Arrivals Thus Far: %6d"+"\n"+
            "   Departures From Triage: %6d"+"\n"+
            "   Departures From Trauma: %6d"+"\n"+
            "    Departures From Acute: %6d"+"\n"+
            "   Departures From Prompt: %6d"+"\n"+
            "Total Departures Thus Far: %6d"+"\n"+
            " Patients in Triage Queue: %6d"+"\n"+
            " Patients in Trauma Queue: %6d"+"\n"+
            " Patients in  Acute Queue: %6d"+"\n"+
            " Patients in Prompt Queue: %6d"+"\n";
            str = String.format(
                str,
                currentTriageArrivals,
                currentTraumaArrivals,
                currentAcuteArrivals,
                currentPromptArrivals,
                totalArrivalsSoFar(),
                currentTriageDepartures,
                currentTraumaDepartures,
                currentAcuteDepartures,
                currentPromptDepartures,
                totalDeparturesSoFar(),
                triageQueuePatientsRemaining(),
                traumaQueuePatientsRemaining(),
                acuteQueuePatientsRemaining(),
                promptQueuePatientsRemaining()
            );
        return str;
    }

}