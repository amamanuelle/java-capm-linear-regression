package org.example.capm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVPriceReader {

    public double[] readClosingPrices(String filePath) {

        List<Double> prices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String header = reader.readLine();
            if (header == null) throw new IllegalArgumentException("CSV file is empty");
            //Date,Open,High,Low,Close,Volume
            String[] cols = header.split(",");

            int closeIdx = -1;
            for (int i = 0; i < cols.length; i++) {
                if (cols[i].trim().equalsIgnoreCase("Close")) {
                    closeIdx = i;
                    break;
                }
            }

            if (closeIdx == -1) throw new IllegalArgumentException("Close column not found");
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                double closePrice = Double.parseDouble(values[closeIdx].trim());
                prices.add(closePrice);
            }
        } catch (IOException io) {
            throw new IllegalArgumentException("Failed to read CSV file", io);
        }

        return prices.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }
}
