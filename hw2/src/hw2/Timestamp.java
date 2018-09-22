package hw2;

import java.util.Hashtable;

public class Timestamp {

    public static final String TIME_UNIT = "(minutes)";
    public static final String PATIENT_UNIT = "(patients)";

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

    private static double avgPatientsInTriageQueue = 0.0;
    private static double avgPatientsInTraumaQueue = 0.0;
    private static double avgPatientsInAcuteQueue = 0.0;
    private static double avgPatientsInPromptQueue = 0.0;

    private static int totalTimestamps = 0;
    private int timestampID = 0;
    private String eventType = "";
    private int eventID = 0;
    private int patientID = 0;

    private double timeOccurred = 0.0;
    
    private String location = "";
    private String destination = "";

    private double serviceTime = 0.0;
    private double interArrivalTime = 0.0;
    private double waitTime = 0.0;

    //private static int numberOfWaits = 0;
    //private static double totalWaitTime = 0.0;


    public Timestamp(Event eventToStamp, Hashtable<String,CareArea> emergencyDept) {
        
        currentTriageArrivals = totalTriageArrivals;
        currentTraumaArrivals = totalTraumaArrivals;
        currentAcuteArrivals  = totalAcuteArrivals;
        currentPromptArrivals = totalPromptArrivals;
        currentTriageDepartures = totalTriageDepartures;
        currentTraumaDepartures = totalTraumaDepartures;
        currentAcuteDepartures  = totalAcuteDepartures;
        currentPromptDepartures = totalPromptDepartures;

        if (eventToStamp instanceof Arrival) {
            this.eventType = Event.ARRIVAL;
            this.eventID = ((Arrival) eventToStamp).getID();
            this.patientID = ((Arrival) eventToStamp).getPatientID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.serviceTime = eventToStamp.getServiceTime();
            this.interArrivalTime = eventToStamp.getInterArrivalTime();
            this.waitTime = eventToStamp.getWaitTime();

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
            this.patientID = ((Departure) eventToStamp).getPatientID();
            this.timeOccurred = eventToStamp.getTimeOccurred();
            this.location = eventToStamp.getLocation();
            this.destination = ((Departure) eventToStamp).getDestination();

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
            avgPatientsInTriageQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int traumaQueuePatientsRemaining() {
        int result = (currentTraumaArrivals-1) - currentTraumaDepartures;
        if (result >= 0) {
            avgPatientsInTraumaQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int acuteQueuePatientsRemaining() {
        int result = (currentAcuteArrivals-1) - currentAcuteDepartures;
        if (result >= 0) {
            avgPatientsInAcuteQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int promptQueuePatientsRemaining() {
        int result = (currentPromptArrivals-1) - currentPromptDepartures;
        if (result >= 0) {
            avgPatientsInPromptQueue += result;
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
    private static int totalArrivals() {
        return totalTriageArrivals+
               totalTraumaArrivals+
               totalAcuteArrivals+
               totalPromptArrivals;
    }
    private static int totalDepartures() {
        return totalTriageDepartures+
               totalTraumaDepartures+
               totalAcuteDepartures+
               totalPromptDepartures;
    }

    public static double avgPatientsInTriageQueue() {
        return avgPatientsInTriageQueue/totalArrivals();
    }
    public static double avgPatientsInTraumaQueue() {
        return avgPatientsInTraumaQueue/totalArrivals();
    }
    public static double avgPatientsInAcuteQueue() {
        return avgPatientsInAcuteQueue/totalArrivals();
    }
    public static double avgPatientsInPromptQueue() {
        return avgPatientsInPromptQueue/totalArrivals();
    }

    @Override
    public String toString() {
        String str = String.format(
            "Timestamp " + timestampID + "\n"+
            "                    Event: "+eventType+" "+eventID+"\n"+
            "                PatientID: "+patientID+"\n"
        );
        if (eventType.equals(Event.ARRIVAL)) {
            str=str+
            "               Arrived At: %s\n"+
            "            Time Occurred: %10.3f %s\n"+
            "       Inter-Arrival Time: %10.3f %s\n"+
            "             Service Time: %10.3f %s\n"+
            "                Wait Time: %10.3f %s\n";
            str = String.format (
                str,
                location,
                timeOccurred,Timestamp.TIME_UNIT,
                interArrivalTime,Timestamp.TIME_UNIT,
                serviceTime,Timestamp.TIME_UNIT,
                waitTime,Timestamp.TIME_UNIT
            );
        } else {
            str=str+
            "            Departed From: %s\n"+
            "              Destination: %s\n"+
            "            Time Occurred: %10.3f %s\n";
            str = String.format (
                str,
                location, 
                destination,
                timeOccurred,Timestamp.TIME_UNIT
            );
        }
            str=str+
            "       Arrivals in Triage: %6d %14s\n"+
            "       Arrivals in Trauma: %6d %14s\n"+
            "        Arrivals in Acute: %6d %14s\n"+
            "       Arrivals in Prompt: %6d %14s\n"+
            "  Total Arrivals Thus Far: %6d %14s\n"+
            "   Departures From Triage: %6d %14s\n"+
            "   Departures From Trauma: %6d %14s\n"+
            "    Departures From Acute: %6d %14s\n"+
            "   Departures From Prompt: %6d %14s\n"+
            "Total Departures Thus Far: %6d %14s\n"+
            " Patients in Triage Queue: %6d %14s\n"+
            " Patients in Trauma Queue: %6d %14s\n"+
            " Patients in  Acute Queue: %6d %14s\n"+
            " Patients in Prompt Queue: %6d %14s\n";
            str = String.format(
                str,
                currentTriageArrivals,Timestamp.PATIENT_UNIT,
                currentTraumaArrivals,Timestamp.PATIENT_UNIT,
                currentAcuteArrivals,Timestamp.PATIENT_UNIT,
                currentPromptArrivals,Timestamp.PATIENT_UNIT,
                totalArrivalsSoFar(),Timestamp.PATIENT_UNIT,
                currentTriageDepartures,Timestamp.PATIENT_UNIT,
                currentTraumaDepartures,Timestamp.PATIENT_UNIT,
                currentAcuteDepartures,Timestamp.PATIENT_UNIT,
                currentPromptDepartures,Timestamp.PATIENT_UNIT,
                totalDeparturesSoFar(),Timestamp.PATIENT_UNIT,
                triageQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                traumaQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                acuteQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                promptQueuePatientsRemaining(),Timestamp.PATIENT_UNIT
            );
        return str;
    }

}