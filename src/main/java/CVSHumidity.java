import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CVSHumidity {

    public CSVRecord getMinimumOfTwo(CSVRecord currentRow, CSVRecord minimumRow) {
        if (minimumRow == null) {
            minimumRow = currentRow;
        } else if (!currentRow.get("Humidity").equals("N/A")){
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double minimumHumidity = Double.parseDouble(minimumRow.get("Humidity"));

            if (currentHumidity<minimumHumidity) { minimumRow = currentRow; }
        }
        return minimumRow;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord minimumRow = null;

        for (CSVRecord currentRow : parser) {
            minimumRow = getMinimumOfTwo(currentRow, minimumRow);
        }
        return minimumRow;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord minimum = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + minimum.get("Humidity") +
                " at " + minimum.get("DateUTC"));
    }

    public static void main(String[] args) {
        CVSHumidity o = new CVSHumidity();
        o.testLowestHumidityInFile();
    }

}
