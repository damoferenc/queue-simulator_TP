import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

public static void main(String args[]){

    try {
        File log = new File("log.txt");
        log.createNewFile();
        FileWriter writer = new FileWriter("log.txt");
        writer.write("");
        writer.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    SetupFrame setupFrame = new SetupFrame();
    setupFrame.setVisible(true);

    setupFrame.addStartButtonListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int timeLimit = setupFrame.getTimeLimit();
            int minProcessingTime = setupFrame.getMinProcessingTime();
            int maxProcessingTime = setupFrame.getMaxProcessingTime();
            int minArrivingTime = setupFrame.getMinArrivingTime();
            int maxArrivingTime = setupFrame.getMaxArrivingTime();
            int numberOfServers = setupFrame.getNumberOfServers();
            int numberOfClients = setupFrame.getNumberOfClients();
            setupFrame.setVisible(false);
            SimulationManager simulationManager = new SimulationManager(timeLimit, minProcessingTime, maxProcessingTime,
                    minArrivingTime, maxArrivingTime, numberOfServers, numberOfClients);
            Thread t = new Thread(simulationManager);
            t.start();
        }
    });

}

}
