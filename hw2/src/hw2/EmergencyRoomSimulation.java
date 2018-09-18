package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Hashtable;

public class EmergencyRoomSimulation {

    static double currentTime = 0.0;
    static int totalSimTime = 0;

    static Hashtable<String,CareArea> emergencyDept = new Hashtable<String,CareArea>();
    static Queue<Event> events = new PriorityQueue<Event>();

    public static void main(String[] args) {

        try {
            File simInput = new File("src/hw2/simInput.txt");
            BufferedReader rdr = new BufferedReader(new FileReader(simInput));

            String currentLine = rdr.readLine();
            totalSimTime = Integer.parseInt(currentLine);

            String[] tokens = rdr.readLine().split(" ");
            CareArea triage = new Triage (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[3]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            emergencyDept.put("Triage", triage);

            tokens = rdr.readLine().split(" ");
            CareArea trauma = new Trauma (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            emergencyDept.put("Trauma", trauma);

            tokens = rdr.readLine().split(" ");
            CareArea acute = new Acute (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            emergencyDept.put("Acute", acute);

            tokens = rdr.readLine().split(" ");
            CareArea prompt = new Prompt (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            emergencyDept.put("Prompt", prompt);

            rdr.close();

        } catch (Exception e) {
            System.err.println("Invalid file format provided.");
            System.err.println(e.getMessage());
            System.exit(1);
        }

        for (String area : emergencyDept.keySet()) {
            System.out.println(emergencyDept.get(area));
        }

        //EventScheduler es = new EventScheduler(simTime);

        //currentTime = es.getNextEvent();


    }

}