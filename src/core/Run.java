/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Libor
 */
import entity.Records;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {

    public static void main(String[] args) throws InterruptedException, IOException {
        Thread summaryDaemon = new Thread((Runnable) new SummaryDaemon());
        summaryDaemon.setDaemon(true);
        summaryDaemon.start();

        Records records = Records.getInstance();
        records.readFromFile();
        
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (!(input = br.readLine()).toLowerCase().equals("quit")) {
            records.addPayment(input);
        }
        
        records.writeToFile();
    }
}
