package com.hilgenberg.csvprocessor.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CSVService {

    @Autowired
    private Environment env;

    private final String path = env.getProperty("csvProcessor.fileLocation");

    public void processFile() {
        CSVFormat.Builder csvBuilder = CSVFormat.Builder.create(CSVFormat.EXCEL);
        try (Reader in = new FileReader(path)){
            Iterable<CSVRecord> records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCSVBuilderOptions(CSVFormat.Builder builder) {

    }
}
