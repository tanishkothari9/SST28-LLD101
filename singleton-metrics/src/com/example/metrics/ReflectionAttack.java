package com.example.metrics;

import java.lang.reflect.Constructor;

/**
 * Attempts to create multiple instances via reflection.
 * Starter allows this. After fix, it must fail.
 */
public class ReflectionAttack {

    public static void main(String[] args) throws Exception {
        MetricsRegistry legitimateInstance = MetricsRegistry.getInstance();

        Constructor<MetricsRegistry> constructor = MetricsRegistry.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        MetricsRegistry forgedInstance = constructor.newInstance();

        System.out.println("Singleton identity: " + System.identityHashCode(legitimateInstance));
        System.out.println("Evil identity     : " + System.identityHashCode(forgedInstance));
        System.out.println("Same object?      : " + (legitimateInstance == forgedInstance));
    }
}