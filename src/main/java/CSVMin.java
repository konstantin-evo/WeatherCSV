import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {

    public CSVRecord getMinimumOfTwo(CSVRecord currentRow, CSVRecord minimumRow) {
        if (minimumRow == null) {//If largestSoFar is nothing
            minimumRow = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double minimumTemp = Double.parseDouble(minimumRow.get("TemperatureF"));

            if (currentTemp<minimumTemp) {
                minimumRow = currentRow;
            }
        }
        return minimumRow;
    }

}
