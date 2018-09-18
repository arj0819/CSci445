package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EmergencyRoomSimulation {

    static double currentTime = 0.0;
    static int totalSimTime = 0;

    static List<CareArea> careAreas = new ArrayList<CareArea>();

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
            careAreas.add(triage);

            tokens = rdr.readLine().split(" ");
            CareArea trauma = new Trauma (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            careAreas.add(trauma);

            tokens = rdr.readLine().split(" ");
            CareArea acute = new Acute (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            careAreas.add(acute);

            tokens = rdr.readLine().split(" ");
            CareArea prompt = new Prompt (
                Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[1])
            );
            careAreas.add(prompt);

            rdr.close();

        } catch (Exception e) {
            System.err.println("Invalid file format provided.");
            System.err.println(e.getMessage());
        }

        for (CareArea area : careAreas) {
            System.out.println(area);
        }

        //EventScheduler es = new EventScheduler(simTime);

        //currentTime = es.getNextEvent();


    }

}