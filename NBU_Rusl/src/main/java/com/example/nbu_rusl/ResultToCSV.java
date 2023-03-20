package com.example.nbu_rusl;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultToCSV {
    public void writeData(String filePath, List<Result> results){
        File file = new File(filePath);
        try {
            FileWriter outPutFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outPutFile,';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.NO_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] head = {"Month","Value","Difference"};

            writer.writeNext(head);

            List<String[]> data = new ArrayList<>();
            for (Result r : results) {
                data.add(r.toArray());
            }
            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
