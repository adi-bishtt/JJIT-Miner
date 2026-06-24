package com.adi.jjit;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");

        FilterData criteria = UserInput.userInput();

        List<String> apiEndpoints = SiteDataFetcher.fetchJobUrls(criteria.getMainSkill());

        System.out.println("\nCollected Endpoints:");
        for (String endpoint : apiEndpoints) {
            System.out.println(endpoint);
        }

        System.out.println("\nDone. Total links found: " + apiEndpoints.size());
    }
}