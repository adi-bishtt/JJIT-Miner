package com.adi.jjit.Engine;

import com.adi.jjit.Model.ExtractedJobData;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class JobDataExtractor {

    public static ExtractedJobData extractFields(String jsonApiUrl) {
        try {
            long jitter = 50 + (long)(Math.random() * 100);
            Thread.sleep(jitter);

            Connection.Response response = Jsoup.connect(jsonApiUrl)
                    .ignoreContentType(true)
                    .followRedirects(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .header("Accept", "application/json")
                    .timeout(6000)
                    .execute();

            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), ExtractedJobData.class);
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }
}