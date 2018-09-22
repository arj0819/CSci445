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

    @Override
    public String toString() {
        String str = String.format(
            "Timestamp " + timestampID + "\n"+
            "                     Event: "+eventType+" "+eventID+"\n"
        );
        if (eventType.equals(Event.ARRIVAL)) {
            str=str+
            "                Arrived At: "+location+"\n"+
            "             Time Occurred: "+timeOccurred+"\n"+
            "              Service Time: "+serviceTime+"\n"+
            "        Inter-Arrival Time: "+interArrivalTime+"\n"+
            "                 Wait Time: "+waitTime+"\n";
        } else {
            str=str+
            "             Time Occurred: "+timeOccurred+"\n"+
            "             Departed From: "+location+"\n"+
            "               Destination: "+destination+"\n";
        }
            str=str+
            "  Triage Arrivals Thus Far: "+currentTriageArrivals+"\n"+
            "  Trauma Arrivals Thus Far: "+currentTraumaArrivals+"\n"+
            "   Acute Arrivals Thus Far: "+currentAcuteArrivals+"\n"+
            "  Prompt Arrivals Thus Far: "+currentPromptArrivals+"\n"+
            "Triage Departures Thus Far: "+currentTriageDepartures+"\n"+
            "Trauma Departures Thus Far: "+currentTraumaDepartures+"\n"+
            " Acute Departures Thus Far: "+currentAcuteDepartures+"\n"+
            "Prompt Departures Thus Far: "+currentPromptDepartures+"\n"+
            "  Patients in Triage Queue: "+triageQueuePatientsRemaining()+"\n"+
            "  Patients in Trauma Queue: "+traumaQueuePatientsRemaining()+"\n"+
            "  Patients in  Acute Queue: "+acuteQueuePatientsRemaining()+"\n"+
            "  Patients in Prompt Queue: "+promptQueuePatientsRemaining()+"\n";
        return str;
    }

}