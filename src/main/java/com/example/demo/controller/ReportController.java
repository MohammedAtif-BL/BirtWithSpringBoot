package com.example.demo.controller;

import org.eclipse.birt.report.engine.api.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BirtReportService;

@RestController
public class ReportController {

	@Autowired
    private BirtReportService birtReportService;


    @GetMapping("/report/{reportName}")
    public ResponseEntity<byte[]> generateReport(@PathVariable("reportName") String reportName) throws EngineException {
        byte[] reportContent = birtReportService.generateReport(reportName + ".rptdesign");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/html");

        return new ResponseEntity<>(reportContent, headers, HttpStatus.OK);
    }
}

