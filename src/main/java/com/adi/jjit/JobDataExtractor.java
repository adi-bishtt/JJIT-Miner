package com.adi.jjit;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import java.io.IOException;

public class JobDataExtractor {

    public static ExtractedJobData extractFields(String jsonApiUrl) {
        try {
            String jsonResponse = Jsoup.connect(jsonApiUrl)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .execute()
                    .body();

            return new Gson().fromJson(jsonResponse, ExtractedJobData.class);

        } catch (IOException e) {
            System.out.println("Connection Error: Fetch failed.");
            return null;
        }
    }
}
