package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class CsvHelper {
    private String csvFilePath;

    public CsvHelper(String csvFile) {
        this.csvFilePath = csvFile;
    }

    public List<String[]> csvReader() throws IOException, CsvException {
        FileReader filereader = new FileReader(csvFilePath);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        return csvReader.readAll();
    }


}
