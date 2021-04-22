import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {

    public CSVRecord getMinimumOfTwo(CSVRecord currentRow, CSVRecord minimumRow) {
        if (minimumRow == null) {
            minimumRow = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double minimumTemp = Double.parseDouble(minimumRow.get("TemperatureF"));

            if (currentTemp != -9999 && currentTemp<minimumTemp) { minimumRow = currentRow; }
        }
        return minimumRow;
    }

    public CSVRecord minimumHourInFile(CSVParser parser) {

        CSVRecord minimumRow = null;

        for (CSVRecord currentRow : parser) {
            minimumRow = getMinimumOfTwo(currentRow, minimumRow);
        }
        return minimumRow;
    }

    public void testMinimumInDay () {
        FileResource fr = new FileResource();
        CSVRecord minimum = minimumHourInFile(fr.getCSVParser());
        System.out.println("Minimum temperature was " + minimum.get("TemperatureF") +
                " at " + minimum.get("DateUTC"));
    }

    public File fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord minimumRow = null;
        File coldestFile = null;

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = minimumHourInFile(fr.getCSVParser());
            minimumRow = getMinimumOfTwo(currentRow, minimumRow);
            coldestFile = file;
        }
        return coldestFile;
    }

    public void testFileWithColdestTemperature() {
        File coldestFile = fileWithColdestTemperature();
        String coldestName = coldestFile.getName();
        System.out.println("Coldest day was in file " + coldestName);

        FileResource fr = new FileResource(coldestFile);
        CSVRecord minimum = minimumHourInFile(fr.getCSVParser());
        System.out.println("Minimum temperature was " + minimum.get("TemperatureF") +
                " at " + minimum.get("TimeEST"));
    }

    public static void main(String[] args) {
        CSVMin o = new CSVMin();
        //o.testMinimumInDay();
        o.testFileWithColdestTemperature();
    }

}
