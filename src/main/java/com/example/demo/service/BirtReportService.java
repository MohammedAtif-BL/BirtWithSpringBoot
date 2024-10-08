package com.example.demo.service;

import java.io.ByteArrayOutputStream;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.springframework.stereotype.Service;

@Service
public class BirtReportService {

    private final IReportEngine reportEngine;

    public BirtReportService(IReportEngine reportEngine) {
        this.reportEngine = reportEngine;
    }

    public byte[] generateReport(String reportFile) throws EngineException {
        IReportRunnable reportRunnable = reportEngine.openReportDesign("src/main/resources/reports/" + reportFile);
        IRunAndRenderTask task = reportEngine.createRunAndRenderTask(reportRunnable);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        RenderOption renderOption = new HTMLRenderOption();
        renderOption.setOutputStream(out);
        renderOption.setOutputFormat("html");

        task.setRenderOption(renderOption);
        task.run();
        task.close();

        return out.toByteArray();
    }
}

