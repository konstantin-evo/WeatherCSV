import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {//If largestSoFar is nothing
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

            if (currentTemp>largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord hottestHourInFile(CSVParser parser) {

        CSVRecord largestSoFar = null; //start with largestSoFar as nothing

        for (CSVRecord currentRow : parser) {//For each row (currentRow) in the CSV File
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestHourManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null; //start with largestSoFar as nothing

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());

            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public void testHottestHourManyDays() {
        CSVRecord largest = hottestHourManyDays();
        System.out.println("The hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("DateUTC"));
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("C:/Users/kpriluch/IdeaProjects/WeatherCSV/src/main/resources/data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }

    public static void main(String[] args) {
        CSVMax o = new CSVMax();
        //o.testHottestInDay();
        o.testHottestHourManyDays();
    }
}