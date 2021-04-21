import edu.duke.*;
import org.apache.commons.csv.*;

public class CVSAverage {

    public double  averageTemperatureInFile(CSVParser parser) {
        double sumTemperature = 0;
        double count = 0;

        for (CSVRecord currentRow : parser) {//For each row (currentRow) in the CSV File
            sumTemperature = sumTemperature + Double.parseDouble(currentRow.get("TemperatureF"));
            count++;
        }
        return sumTemperature/count;
    }

    public  void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature was " + averageTemperature);
    }

    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sumTemperature = 0;
        double count = 0;

        for (CSVRecord currentRow : parser) {
            int humidity = Integer.parseInt(currentRow.get("Humidity"));
            if (humidity >= value) {
                sumTemperature = sumTemperature + Double.parseDouble(currentRow.get("TemperatureF"));
                count++;
            }
        }
        if (count != 0) { return sumTemperature/count; }
        else { return null; }
    }

    public  void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        int value = 80;
        if (averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), value)!= null) {
            double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), value);
            System.out.println("Average Temperature Humidity was " + averageTemperature + " with Humidity greater than " + value);
        } else {
            System.out.println("No temperatures with that humidity");
        }

    }

    public static void main(String[] args) {
        CVSAverage o = new CVSAverage();
        //o.testAverageTemperatureInFile();
        o.testAverageTemperatureWithHighHumidityInFile();
    }

}
