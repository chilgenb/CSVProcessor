package com.hilgenberg.csvprocessor.service;

import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.hilgenberg.csvprocessor.model.RecordModel;
@Component
public class CSVService {

    @Value("${csvProcessor.preprocessedFile}")

    private String startingFile;
    @Value("${csvProcessor.processedFileLocation}")
    private String processedPath;
    //private  String finalFileName = processedPath + LocalDateTime.now().toString() + ".csv";

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
                        record.get("Version"),
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

        File outputFile = new File(processedPath + getDateStamp() + ".csv");
        //write out new file
        try {
            if (outputFile.createNewFile()) {
                try(FileWriter writer = new FileWriter(outputFile)){
                    writer.append("UserId,").append("Name,").append("Version,").append("InsuranceCompanyName");
                    writer.append("\n");
                    for(RecordModel record: sortedList) {
                        writer.append(record.toString());
                        writer.append("\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDateStamp() {
        return LocalDateTime.now().toString().replace(" ", "").replace(".", "").replace(":", "");
    }
}
