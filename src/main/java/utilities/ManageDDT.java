package utilities;

import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ManageDDT extends CommonOps{

    @DataProvider(name = "data-provider-indexesAndSearchTextS")
    public Object[][] getDataObjectIndexesTexts() {
        return getDataFromCSV64(getData("DDTFile_IndexesTexts"));
    }

    public static List<String> readCSV(String csvFile) {
        List<String> lines = null;
        File file = new File(csvFile);
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static Object[][] getDataFromCSV64(String filePath) {
        Object[][] data = new Object[6][4];
        List<String> csvData = readCSV(filePath);
        for (int i=0; i < csvData.size(); i++) {
            for (int j=0; j < 4; j++)
                data[i][j] = csvData.get(i).split(",")[j];
        }
        return data;
    }

}