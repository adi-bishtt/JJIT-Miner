package com.adi.jjit.Engine;

import com.adi.jjit.Model.ExtractedJobData;
import com.adi.jjit.Model.FilterData;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JobProcessor {

    public static List<String> processEndpoints(List<String> endpoints, FilterData criteria) {
        List<String> matchingLinks = new CopyOnWriteArrayList<>();
        int threads = 8;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        System.out.println("Starting ...");

        for (String endpoint : endpoints) {
            executor.submit(() -> {
                ExtractedJobData job = JobDataExtractor.extractFields(endpoint);
                if (job != null && JobMatcher.isMatch(job, criteria)) {
                    matchingLinks.add("https://justjoin.it/job-offer/" + job.slug);
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(15, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return matchingLinks;
    }
}