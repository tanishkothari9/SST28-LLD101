package com.example.metrics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads default metric keys from a properties file.
 */
public class MetricsLoader {

    public MetricsRegistry loadFromFile(String filePath) throws IOException {
        Properties config = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            config.load(inputStream);
        }

        // Use singleton - not new instance
        MetricsRegistry metricsInstance = MetricsRegistry.getInstance();

        for (String metricName : config.stringPropertyNames()) {
            String rawValue = config.getProperty(metricName, "0").trim();
            long parsedCount;
            try {
                parsedCount = Long.parseLong(rawValue);
            } catch (NumberFormatException exc) {
                parsedCount = 0L;
            }
            metricsInstance.setCount(metricName, parsedCount);
        }
        return metricsInstance;
    }
}