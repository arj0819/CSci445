package hw2;

import java.util.Hashtable;

public class Timestamp {

    public static final String TIME_UNIT = "minutes";
    public static final String PATIENT_UNIT = "patients";
    public static final String SERVER_UNIT = "specialists";

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

    private static int totalDischargesFromTriage = 0;
    private int currentDischargesFromTriage = 0;

    private static double totalTriageServers = 0.0;
    private static double totalTraumaServers = 0.0;
    private static double totalAcuteServers = 0.0;
    private static double totalPromptServers = 0.0;

    private int availableTriageServers = 0;
    private int availableTraumaServers = 0;
    private int availableAcuteServers = 0;
    private int availablePromptServers = 0;

    private static double totalPatientsInTriageQueue = 0.0;
    private static double totalPatientsInTraumaQueue = 0.0;
    private static double totalPatientsInAcuteQueue = 0.0;
    private static double totalPatientsInPromptQueue = 0.0;

    private double currentTriageWaitTime = 0.0;
    private double currentTraumaWaitTime = 0.0;
    private double currentAcuteWaitTime = 0.0;
    private double currentPromptWaitTime = 0.0;
    private static double totalTriageWaitTime = 0.0;
    private static double totalTraumaWaitTime = 0.0;
    private static double totalAcuteWaitTime = 0.0;
    private static double totalPromptWaitTime = 0.0;

    private double currentTriageServiceTime = 0.0;
    private double currentTraumaServiceTime = 0.0;
    private double currentAcuteServiceTime = 0.0;
    private double currentPromptServiceTime = 0.0;
    private static double totalTriageServiceTime = 0.0;
    private static double totalTraumaServiceTime = 0.0;
    private static double totalAcuteServiceTime = 0.0;
    private static double totalPromptServiceTime = 0.0;

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
        currentDischargesFromTriage = totalDischargesFromTriage;
        currentTriageWaitTime = totalTriageWaitTime;
        currentTraumaWaitTime = totalTraumaWaitTime;
        currentAcuteWaitTime = totalAcuteWaitTime;
        currentPromptWaitTime = totalPromptWaitTime;
        currentTriageServiceTime = totalTriageServiceTime;
        currentTraumaServiceTime = totalTraumaServiceTime;
        currentAcuteServiceTime = totalAcuteServiceTime;
        currentPromptServiceTime = totalPromptServiceTime;

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
                currentTriageWaitTime = waitTime;
                totalTriageWaitTime += currentTriageWaitTime;
                currentTriageServiceTime = serviceTime;
                totalTriageServiceTime += currentTriageServiceTime;
            } else if (location.equals(CareArea.TRAUMA)) {
                currentTraumaArrivals = ++totalTraumaArrivals;
                currentTraumaWaitTime = waitTime;
                totalTraumaWaitTime += currentTraumaWaitTime;
                currentTraumaServiceTime = serviceTime;
                totalTraumaServiceTime += currentTraumaServiceTime;
            } else if (location.equals(CareArea.ACUTE)) {
                currentAcuteArrivals = ++totalAcuteArrivals;
                currentAcuteWaitTime = waitTime;
                totalAcuteWaitTime += currentAcuteWaitTime;
                currentAcuteServiceTime = serviceTime;
                totalAcuteServiceTime += currentAcuteServiceTime;
            } else {
                currentPromptArrivals = ++totalPromptArrivals;
                currentPromptWaitTime = waitTime;
                totalPromptWaitTime += currentPromptWaitTime;
                currentPromptServiceTime = serviceTime;
                totalPromptServiceTime += currentPromptServiceTime;
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
                if (destination.equals(Departure.OUTSIDE_WORLD)) {
                    currentDischargesFromTriage = ++totalDischargesFromTriage;
                }
            } else if (location.equals(CareArea.TRAUMA)) {
                currentTraumaDepartures = ++totalTraumaDepartures;
            } else if (location.equals(CareArea.ACUTE)) {
                currentAcuteDepartures = ++totalAcuteDepartures;
            } else {
                currentPromptDepartures = ++totalPromptDepartures;
            }
                
        }
        this.timestampID = ++totalTimestamps;

        this.availableTriageServers = emergencyDept.get(CareArea.TRIAGE).getAvailableServers();
        this.availableTraumaServers = emergencyDept.get(CareArea.TRAUMA).getAvailableServers();
        this.availableAcuteServers = emergencyDept.get(CareArea.ACUTE).getAvailableServers();
        this.availablePromptServers = emergencyDept.get(CareArea.PROMPT).getAvailableServers();
        totalTriageServers += availableTriageServers;
        totalTraumaServers += availableTraumaServers;
        totalAcuteServers += availableAcuteServers;
        totalPromptServers += availablePromptServers;
    }

    private int triageQueuePatientsRemaining() {
        int result = (currentTriageArrivals-1) - currentTriageDepartures;
        if (result >= 0) {
            totalPatientsInTriageQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int traumaQueuePatientsRemaining() {
        int result = (currentTraumaArrivals-1) - currentTraumaDepartures;
        if (result >= 0) {
            totalPatientsInTraumaQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int acuteQueuePatientsRemaining() {
        int result = (currentAcuteArrivals-1) - currentAcuteDepartures;
        if (result >= 0) {
            totalPatientsInAcuteQueue += result;
            return result;
        } else {
            return 0;
        }
    }
    private int promptQueuePatientsRemaining() {
        int result = (currentPromptArrivals-1) - currentPromptDepartures;
        if (result >= 0) {
            totalPatientsInPromptQueue += result;
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
    private int triageDeparturesSoFar() {
        return currentTriageDepartures-
               currentDischargesFromTriage;
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
        return totalPatientsInTriageQueue/totalArrivals();
    }
    public static double avgPatientsInTraumaQueue() {
        return totalPatientsInTraumaQueue/totalArrivals();
    }
    public static double avgPatientsInAcuteQueue() {
        return totalPatientsInAcuteQueue/totalArrivals();
    }
    public static double avgPatientsInPromptQueue() {
        return totalPatientsInPromptQueue/totalArrivals();
    }
    public static double avgServersAvailableInTriage() {
        return totalTriageServers/(totalArrivals()+totalDepartures());
    }
    public static double avgServersAvailableInTrauma() {
        return totalTraumaServers/(totalArrivals()+totalDepartures());
    }
    public static double avgServersAvailableInAcute() {
        return totalAcuteServers/(totalArrivals()+totalDepartures());
    }
    public static double avgServersAvailableInPrompt() {
        return totalPromptServers/(totalArrivals()+totalDepartures());
    }
    public static double avgTriageWaitTime() {
        if (totalTriageArrivals != 0) {
            return totalTriageWaitTime/totalTriageArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgTraumaWaitTime() {
        if (totalTraumaArrivals != 0) {
            return totalTraumaWaitTime/totalTraumaArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgAcuteWaitTime() {
        if (totalAcuteArrivals != 0) {
            return totalAcuteWaitTime/totalAcuteArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgPromptWaitTime() {
        if (totalPromptArrivals != 0) {
            return totalPromptWaitTime/totalPromptArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgTriageServiceTime() {
        if (totalTriageArrivals != 0) {
            return totalTriageServiceTime/totalTriageArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgTraumaServiceTime() {
        if (totalTraumaArrivals != 0) {
            return totalTraumaServiceTime/totalTraumaArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgAcuteServiceTime() {
        if (totalAcuteArrivals != 0) {
            return totalAcuteServiceTime/totalAcuteArrivals;
        } else {
            return 0.0;
        }
    }
    public static double avgPromptServiceTime() {
        if (totalPromptArrivals != 0) {
            return totalPromptServiceTime/totalPromptArrivals;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        String str = String.format(
            "Timestamp " + timestampID + "\n"+
            "                       Event: "+eventType+" "+eventID+"\n"+
            "                   PatientID: "+patientID+"\n"
        );
        if (eventType.equals(Event.ARRIVAL)) {
            str=str+
            "                  Arrived At: %s\n"+
            "               Time Occurred: %10.3f %s\n"+
            "          Inter-Arrival Time: %10.3f %s\n"+
            "                Service Time: %10.3f %s\n"+
            "                   Wait Time: %10.3f %s\n";
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
            "               Departed From: %s\n"+
            "                 Destination: %s\n"+
            "               Time Occurred: %10.3f %s\n";
            str = String.format (
                str,
                location, 
                destination,
                timeOccurred,Timestamp.TIME_UNIT
            );
        }
            str=str+
            "          Arrivals in Triage: %6d %12s\n"+
            "          Arrivals in Trauma: %6d %12s\n"+
            "           Arrivals in Acute: %6d %12s\n"+
            "          Arrivals in Prompt: %6d %12s\n"+
            "     Total Arrivals Thus Far: %6d %12s\n"+
            "      Discharges From Triage: %6d %12s\n"+
            "      Departures From Triage: %6d %12s\n"+
            "      Departures From Trauma: %6d %12s\n"+
            "       Departures From Acute: %6d %12s\n"+
            "      Departures From Prompt: %6d %12s\n"+
            "   Total Departures Thus Far: %6d %12s\n"+
            " Available Servers in Triage: %6d %15s\n"+
            " Available Servers in Trauma: %6d %15s\n"+
            "  Available Servers in Acute: %6d %15s\n"+
            " Available Servers in Prompt: %6d %15s\n"+
            "    Patients in Triage Queue: %6d %12s\n"+
            "    Patients in Trauma Queue: %6d %12s\n"+
            "    Patients in  Acute Queue: %6d %12s\n"+
            "    Patients in Prompt Queue: %6d %12s\n";
            str = String.format(
                str,
                currentTriageArrivals,Timestamp.PATIENT_UNIT,
                currentTraumaArrivals,Timestamp.PATIENT_UNIT,
                currentAcuteArrivals,Timestamp.PATIENT_UNIT,
                currentPromptArrivals,Timestamp.PATIENT_UNIT,
                totalArrivalsSoFar(),Timestamp.PATIENT_UNIT,
                currentDischargesFromTriage,Timestamp.PATIENT_UNIT,
                triageDeparturesSoFar(),Timestamp.PATIENT_UNIT,
                currentTraumaDepartures,Timestamp.PATIENT_UNIT,
                currentAcuteDepartures,Timestamp.PATIENT_UNIT,
                currentPromptDepartures,Timestamp.PATIENT_UNIT,
                totalDeparturesSoFar(),Timestamp.PATIENT_UNIT,
                availableTriageServers,Timestamp.SERVER_UNIT,
                availableTraumaServers,Timestamp.SERVER_UNIT,
                availableAcuteServers,Timestamp.SERVER_UNIT,
                availablePromptServers,Timestamp.SERVER_UNIT,
                triageQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                traumaQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                acuteQueuePatientsRemaining(),Timestamp.PATIENT_UNIT,
                promptQueuePatientsRemaining(),Timestamp.PATIENT_UNIT
            );
        return str;
    }

}