package com.example.metrics;

import java.io.*;

/**
 * Serializes and deserializes the registry.
 * Starter will typically produce a NEW instance. After fix, it must return the same singleton.
 */
public class SerializationCheck {

    public static void main(String[] args) throws Exception {
        MetricsRegistry originalInstance = MetricsRegistry.getInstance();
        originalInstance.setCount("REQUESTS_TOTAL", 42);

        byte[] serializedData = serialize(originalInstance);
        MetricsRegistry restoredInstance = deserialize(serializedData);

        System.out.println("A identity: " + System.identityHashCode(originalInstance));
        System.out.println("B identity: " + System.identityHashCode(restoredInstance));
        System.out.println("Same object? " + (originalInstance == restoredInstance));
        System.out.println("B REQUESTS_TOTAL = " + restoredInstance.getCount("REQUESTS_TOTAL"));
    }

    private static byte[] serialize(MetricsRegistry registryRef) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectWriter = new ObjectOutputStream(byteStream)) {
            objectWriter.writeObject(registryRef);
        }
        return byteStream.toByteArray();
    }

    private static MetricsRegistry deserialize(byte[] serializedData) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectReader = new ObjectInputStream(new ByteArrayInputStream(serializedData))) {
            return (MetricsRegistry) objectReader.readObject();
        }
    }
}