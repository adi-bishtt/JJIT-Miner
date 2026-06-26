package com.adi.jjit.Engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteDataFetcher {

    public static List<String> fetchJobUrls(String categoryFilter) {
        List<String> resultLinks = new ArrayList<>();
        String sitemapUrl = "https://public.justjoin.com/justjoinit/sitemaps/active-jobs/part0.xml";

        String targetCategory = (categoryFilter != null) ? categoryFilter.trim().toLowerCase() : "";

        if (targetCategory.equals("c++") || targetCategory.equals("c")) {
            targetCategory = "c";
        } else if (targetCategory.equals(".net") || targetCategory.equals("net") || targetCategory.equals("c#")) {
            targetCategory = "net";
        } else if (targetCategory.equals("ai") || targetCategory.equals("ml") || targetCategory.equals("ai/ml")) {
            targetCategory = "ai";
        } else if (targetCategory.equals("ux") || targetCategory.equals("ui") || targetCategory.equals("ux/ui")) {
            targetCategory = "ux";
        }

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

                    if (!targetCategory.isEmpty()) {
                        String lowerRawUrl = rawUrl.toLowerCase();
                        if (!lowerRawUrl.endsWith("-" + targetCategory) && !lowerRawUrl.contains("-" + targetCategory + "-")) {
                            continue;
                        }
                    }

                    String[] urlParts = rawUrl.split("/job-offer/");
                    if (urlParts.length > 1) {
                        String slug = urlParts[1];
                        String apiEndpoint = "https://justjoin.it/api/candidate-api/offers/" + slug;
                        resultLinks.add(apiEndpoint);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Network Error: Failed to fetch or parse sitemap XML.");
            e.printStackTrace();
        }

        return resultLinks;
    }
}