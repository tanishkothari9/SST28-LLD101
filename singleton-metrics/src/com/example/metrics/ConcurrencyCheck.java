package com.example.metrics;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Spawns many threads racing on getInstance().
 * Starter is expected to sometimes create >1 instance. After fix, must always be 1.
 */
public class ConcurrencyCheck {

    public static void main(String[] args) throws Exception {
        int workerCount = 80;
        ExecutorService executor = Executors.newFixedThreadPool(workerCount);

        CountDownLatch readyLatch = new CountDownLatch(workerCount);
        CountDownLatch goSignal = new CountDownLatch(1);
        CountDownLatch completionLatch = new CountDownLatch(workerCount);

        Set<Integer> uniqueHashes = ConcurrentHashMap.newKeySet();

        for (int idx = 0; idx < workerCount; idx++) {
            executor.submit(() -> {
                readyLatch.countDown();
                try {
                    goSignal.await();
                    MetricsRegistry registryRef = MetricsRegistry.getInstance();
                    uniqueHashes.add(System.identityHashCode(registryRef));
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                } finally {
                    completionLatch.countDown();
                }
            });
        }

        readyLatch.await(2, TimeUnit.SECONDS);
        goSignal.countDown();
        completionLatch.await(3, TimeUnit.SECONDS);
        executor.shutdownNow();

        System.out.println("Unique instances seen: " + uniqueHashes.size());
        System.out.println("Identities: " + uniqueHashes);
    }
}