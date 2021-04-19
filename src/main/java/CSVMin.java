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
                " at " + minimum.get("TimeEST"));
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord minimumRow = null;
        String coldestFileName = null;

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = minimumHourInFile(fr.getCSVParser());
            minimumRow = getMinimumOfTwo(currentRow, minimumRow);
            coldestFileName = file.getName();
        }
        return coldestFileName;
    }

    public void testFileWithColdestTemperature() {
        String coldestname = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestname);

        FileResource fr = new FileResource();
        CSVRecord minimum = minimumHourInFile(fr.getCSVParser());
        System.out.println("Minimum temperature was " + minimum.get("TemperatureF") +
                " at " + minimum.get("TimeEST"));
    }

    public static void main(String[] args) {
        CSVMin o = new CSVMin();
        //o.testMinimumInDay();
        System.out.println(o.fileWithColdestTemperature());
    }

}
