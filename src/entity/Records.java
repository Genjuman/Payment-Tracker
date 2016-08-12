/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Libor
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Records {

    private static Records instance;
    private static ConcurrentHashMap<Currencies, BigDecimal> map;
    private static final String FILE_PATH = "Records.txt";

    protected Records() {
        map = new ConcurrentHashMap();
    }

    public static Records getInstance() {
        if (instance == null) {
            instance = new Records();
        }
        return instance;
    }

    public void addPayment(String record) {
        BigDecimal amount;
        Currencies currency;
        String[] parts = record.split(" ");
        try {
            currency = Currencies.valueOf(parts[0].toUpperCase());
            amount = new BigDecimal(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            return;
        } catch (Exception e) {
            System.out.println("Invalid currency!");
            return;
        }
        if (map.containsKey((currency))) {
            map.replace(currency, map.get((currency)).add(amount));
            System.out.println("Currency amount sucesfully updated.");
        } else {
            map.put(currency, amount);
            System.out.println("New currency amount sucesfully added.");
        }
    }

    public void displayPayments() {
        Iterator it = map.keySet().iterator();
        System.out.println("Displaing list of payments:");
        while (it.hasNext()) {
            Currencies currency = (Currencies) it.next();
            BigDecimal amount = map.get(currency);
            if (amount.equals(new BigDecimal(0))) {
                continue;
            }
            if (currency != Currencies.USD) {
                System.out.println(currency + " " + amount + "(USD " + amount.multiply(currency.getUsdRatting()).setScale(2, RoundingMode.HALF_UP) + ")");
                continue;
            }
            System.out.println(currency + " " + amount);
        }
        System.out.println("-----------------");
    }

    public void readFromFile() {
        Records records = Records.getInstance();
        BufferedReader br = null;
        System.out.println("Reading records from file:");
        try {
            String line;
            br = new BufferedReader(new FileReader("FILE_PATH"));
            while ((line = br.readLine()) != null) {
                records.addPayment(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
        } catch (IOException e) {
            System.out.println("Error reading file!");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file!");
            }
        }
        System.out.println("Reading from file completed.");
    }

    public void writeToFile() {
        Records records = Records.getInstance();
        ArrayList<String> lines = records.getListOfPayments();
        Path file = Paths.get("FILE_PATH", new String[0]);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"), new OpenOption[0]);
        } catch (IOException ex) {
            System.out.println("Error writing to file.");
        }
    }

    private ArrayList<String> getListOfPayments() {
        ArrayList<String> paymentList = new ArrayList<>();
        map.keySet().stream().forEach((currency) -> {
            BigDecimal amount = map.get(currency);
            if (!(amount.equals(new BigDecimal(0)))) {
                paymentList.add(currency + " " + amount);
            }
        });
        return paymentList;
    }
}
