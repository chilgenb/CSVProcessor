package com.hilgenberg.csvprocessor.service;

import com.hilgenberg.csvprocessor.model.RecordModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVService {

    @Autowired
    private Environment env;

    private final String startingFile = env.getProperty("csvProcessor.preprocessedFile");
    private final String processedPath = env.getProperty("csvProcessor.processedFileLocation");

    public void processFile() {
        List<RecordModel> recordsToSort = new ArrayList<>();
        RecordModel recordModel;
        try (Reader fileReader = new FileReader(startingFile)){
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("UserId", "Name", "Version", "InsuranceCompany").parse(fileReader);
            for(CSVRecord record: records) {
                recordModel = new RecordModel(record.get("UserId"),
                        record.get("Name"),
                        Integer.parseInt(record.get("Version")),
                        record.get("InsuranceCompany"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*private void setCSVBuilderOptions(CSVFormat.Builder builder) {

    }*/
}
