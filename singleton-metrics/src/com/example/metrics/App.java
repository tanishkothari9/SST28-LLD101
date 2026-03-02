package com.example.metrics;

import java.util.Map;

/**
 * PulseMeter CLI entry point.
 *
 * Starter behavior prints instance identity and increments a counter.
 * After you fix the Singleton, the identity should be stable across the app.
 */
public class App {

    public static void main(String[] args) throws Exception {
        String configFile = "metrics.properties";

        MetricsLoader metricsImporter = new MetricsLoader();
        MetricsRegistry populatedRegistry = metricsImporter.loadFromFile(configFile);

        // In a correct design, loader should populate the SAME singleton instance.
        MetricsRegistry sharedRegistry = MetricsRegistry.getInstance();

        System.out.println("Loaded registry instance  : " + System.identityHashCode(populatedRegistry));
        System.out.println("Global registry instance  : " + System.identityHashCode(sharedRegistry));

        sharedRegistry.increment("REQUESTS_TOTAL");
        System.out.println("\nREQUESTS_TOTAL = " + sharedRegistry.getCount("REQUESTS_TOTAL"));

        System.out.println("\nAll counters:");
        for (Map.Entry<String, Long> entry : sharedRegistry.getAll().entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("\nTIP: Run ConcurrencyCheck / ReflectionAttack / SerializationCheck for validations.");
    }
}