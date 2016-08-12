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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SummaryDaemon
        implements Runnable {

    private static final int SLEEP_TIME = 60000;

    @Override
    public void run() {
        Records records = Records.getInstance();
        do {
            try {
                do {
                    Thread.sleep(SLEEP_TIME);
                    records.displayPayments();
                } while (true);
            } catch (InterruptedException ex) {
                Logger.getLogger(SummaryDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }
}
