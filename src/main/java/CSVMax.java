import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {

        CSVRecord largestSoFar = null; //start with largestSoFar as nothing

        for (CSVRecord currentRow : parser) {//For each row (currentRow) in the CSV File
            if (largestSoFar == null) {//If largestSoFar is nothing
                largestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

                if (currentTemp>largestTemp) {
                    largestSoFar = currentRow;
                }

            }

        }
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }
}