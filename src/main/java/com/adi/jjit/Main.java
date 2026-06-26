package com.adi.jjit;

import com.adi.jjit.Engine.JobProcessor;
import com.adi.jjit.Engine.SiteDataFetcher;
import com.adi.jjit.Model.FilterData;
import com.adi.jjit.UI.UserInput;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");

        FilterData criteria = UserInput.userInput();

        List<String> apiEndpoints = SiteDataFetcher.fetchJobUrls(criteria.getMainSkill());
        System.out.println("Sitemap evaluation complete. Items to fetch: " + apiEndpoints.size());

        if (apiEndpoints.isEmpty()) {
            System.out.println("No matching endpoints found.");
            return;
        }

        List<String> matchingJobUrls = JobProcessor.processEndpoints(apiEndpoints, criteria);

        System.out.println("\nMatching Job Listings:");
        for (String url : matchingJobUrls) {
            System.out.println(url);
        }

        System.out.println("\nDone. Found " + matchingJobUrls.size() + " matches.");
    }
}