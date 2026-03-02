package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, lazy-initialized Singleton using static holder pattern.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> metricStore = new HashMap<>();

    // Flag to block reflection attacks
    private static boolean alreadyInitialized = false;

    // Private constructor - blocks reflection attack
    private MetricsRegistry() {
        synchronized (MetricsRegistry.class) {
            if (alreadyInitialized) {
                throw new RuntimeException("Use getInstance() - reflection not allowed!");
            }
            alreadyInitialized = true;
        }
    }

    // Static holder - lazy & thread-safe (JVM guarantees)
    private static class RegistryHolder {
        private static final MetricsRegistry SOLE_INSTANCE = new MetricsRegistry();
    }

    // Thread-safe lazy initialization
    public static MetricsRegistry getInstance() {
        return RegistryHolder.SOLE_INSTANCE;
    }

    // Preserve singleton on deserialization
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String metricKey, long metricValue) {
        metricStore.put(metricKey, metricValue);
    }

    public synchronized void increment(String metricKey) {
        metricStore.put(metricKey, getCount(metricKey) + 1);
    }

    public synchronized long getCount(String metricKey) {
        return metricStore.getOrDefault(metricKey, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(metricStore));
    }
}