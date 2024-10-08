package com.example.demo.config;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.core.framework.Platform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BirtConfig {

    @Bean
    public IReportEngine reportEngine() {
        EngineConfig config = new EngineConfig();
        IReportEngine reportEngine = null;

        try {
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            reportEngine = factory.createReportEngine(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportEngine;
    }
}

