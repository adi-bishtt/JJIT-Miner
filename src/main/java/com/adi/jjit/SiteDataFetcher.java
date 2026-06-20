package com.adi.jjit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteDataFetcher {
    public static List<String> fetchJobUrls() {
        List<String> jsonEndpoints = new ArrayList<>();
        String sitemapUrl = "https://public.justjoin.com/justjoinit/sitemaps/active-jobs/part0.xml";

        try {
            Document doc = Jsoup.connect(sitemapUrl)
                    .parser(org.jsoup.parser.Parser.xmlParser())
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36")
                    .header("From", "adpycloud@gmail.com")
                    .header("Adi", "JJIT-Miner")
                    .timeout(20000)
                    .get();

            Elements locTags = doc.select("loc");

            for (Element loc : locTags) {
                String rawUrl = loc.text().trim();

                if (!rawUrl.isEmpty() && rawUrl.contains("/job-offer/")) {
                    String[] urlParts = rawUrl.split("/job-offer/");

                    if (urlParts.length > 1) {
                        String slug = urlParts[1];
                        String apiEndpoint = "https://justjoin.it/api/candidate-api/offers/" + slug;

                        jsonEndpoints.add(apiEndpoint);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Network Error: Failed to fetch or parse sitemap XML.");
            e.printStackTrace();
        }

        return jsonEndpoints;
    }
}
