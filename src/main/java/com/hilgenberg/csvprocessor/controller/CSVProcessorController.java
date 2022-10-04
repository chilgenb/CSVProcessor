package com.hilgenberg.csvprocessor.controller;

import com.hilgenberg.csvprocessor.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CSVProcessorController {
    @Autowired
    private CSVService service;

    @GetMapping(path="/csv/conversion")
    public ResponseEntity runCSVProcessor() {
        service.processFile();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
