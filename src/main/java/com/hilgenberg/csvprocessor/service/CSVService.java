package com.hilgenberg.csvprocessor.service;

import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.hilgenberg.csvprocessor.model.RecordModel;
@Component
public class CSVService {

    @Autowired
    private Environment env;

    private final String startingFile = env.getProperty("csvProcessor.preprocessedFile");
    private final String processedPath = env.getProperty("csvProcessor.processedFileLocation");
    private final String finalFileName = processedPath + "\\" + LocalDateTime.now().toString() + ".csv";

    public void processFile() {
        List<RecordModel> records = readFile();
        if (!records.isEmpty()){
            records = sortFileRecords(records);
            if (!records.isEmpty()) {
                writeSortedFile(records);
            }
        }
    }

    private List<RecordModel> readFile() {
        List<RecordModel> recordsToSort = new ArrayList<>();
        RecordModel recordModel;
        try (Reader fileReader = new FileReader(startingFile)){
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("UserId", "Name", "Version", "InsuranceCompany").parse(fileReader);
            for(CSVRecord record: records) {
                recordModel = new RecordModel(record.get("UserId"),
                        record.get("Name"),
                        Integer.parseInt(record.get("Version")),
                        record.get("InsuranceCompany"));
                recordsToSort.add(recordModel);
            }
            return recordsToSort;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RecordModel> sortFileRecords(List<RecordModel> listOfRecordsToSort) {
        List<RecordModel> sortedList;
        return listOfRecordsToSort.stream()
                .sorted(Comparator.comparing(RecordModel::getInsuranceCompanyName)
                        .thenComparing(RecordModel::getName))
                .collect(Collectors.toList());
    }

    private void writeSortedFile(List<RecordModel> sortedList) {
        //write out new file
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(finalFileName), CSVFormat.EXCEL)){
            printer.printRecord("UserId", "Name", "Version", "InsuranceCompanyName");
            for(RecordModel record: sortedList) {
                printer.printRecord(record);
                //TODO - based on code, this should do a printer.println internally, need to test to see
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
