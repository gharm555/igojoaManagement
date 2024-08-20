package com.itwill.igojoamanagement.service;

import com.google.analytics.data.v1beta.*;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class GA4Service {

    private static final Logger logger = LoggerFactory.getLogger(GA4Service.class);

    @Value("${ga4.property.id}")
    private String propertyId;

    @Value("${ga4.service.account.key}")
    private String serviceAccountKeyPath;

    public Map<String, Object> getGA4Data() {
        logger.info("Starting to fetch GA4 data");
        try {
            GoogleCredentials credentials = getGoogleCredentials();
            BetaAnalyticsDataClient analyticsData = createAnalyticsDataClient(credentials);
            RunReportRequest request = createReportRequest();

            logger.info("Sending request to GA4 API");
            RunReportResponse response = analyticsData.runReport(request);
            logger.info("Received response from GA4 API");

            logger.debug("Full GA4 API response: {}", response);

            if (response.getRowsCount() == 0) {
                logger.warn("No data returned from GA4");
                return null;
            }

            Map<String, Object> result = processResponse(response);
            logger.info("Successfully processed GA4 data");
            return result;

        } catch (IOException e) {
            logger.error("Error reading service account key file", e);
            throw new RuntimeException("Failed to read service account key", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching GA4 data", e);
            throw new RuntimeException("Failed to fetch GA4 data", e);
        }
    }

    private GoogleCredentials getGoogleCredentials() throws IOException {
       try (FileInputStream serviceAccountStream = new FileInputStream(serviceAccountKeyPath)) {
           return ServiceAccountCredentials.fromStream(serviceAccountStream)
               .createScoped("https://www.googleapis.com/auth/analytics.readonly");
       }
   }

    private BetaAnalyticsDataClient createAnalyticsDataClient(GoogleCredentials credentials) throws IOException {
        logger.debug("Creating BetaAnalyticsDataClient");
        return BetaAnalyticsDataClient.create(
                BetaAnalyticsDataSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                        .build());
    }

    private RunReportRequest createReportRequest() {
        logger.debug("Creating RunReportRequest for property ID: {}", propertyId);
        return RunReportRequest.newBuilder()
                .setProperty("properties/" + propertyId)
                .addDimensions(Dimension.newBuilder().setName("pagePath"))
                .addMetrics(Metric.newBuilder().setName("screenPageViews"))
                .addMetrics(Metric.newBuilder().setName("averageSessionDuration"))
                .addDateRanges(DateRange.newBuilder().setStartDate("30daysAgo").setEndDate("today"))
                .build();
    }

    private Map<String, Object> processResponse(RunReportResponse response) {
        logger.debug("Processing GA4 response");
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> pageData = new ArrayList<>();
        long totalViews = 0;
        double totalDuration = 0;

        for (Row row : response.getRowsList()) {
            String pagePath = row.getDimensionValues(0).getValue();
            long pageViews = Long.parseLong(row.getMetricValues(0).getValue());
            double avgSessionDuration = Double.parseDouble(row.getMetricValues(1).getValue());

            Map<String, Object> pageInfo = new HashMap<>();
            pageInfo.put("pagePath", pagePath);
            pageInfo.put("views", pageViews);
            pageInfo.put("avgDuration", avgSessionDuration);

            pageData.add(pageInfo);
            totalViews += pageViews;
            totalDuration += avgSessionDuration * pageViews;
        }

        // Sort pages by views in descending order
        pageData.sort((a, b) -> Long.compare((Long) b.get("views"), (Long) a.get("views")));

        result.put("pageData", pageData);
        result.put("totalViews", totalViews);
        result.put("avgTotalDuration", totalViews > 0 ? totalDuration / totalViews : 0);
        result.put("mostVisitedPage", pageData.isEmpty() ? null : pageData.get(0));

        logProcessedData(result);
        return result;
    }

    private void logProcessedData(Map<String, Object> result) {
        logger.info("Processed GA4 Data:");
        logger.info("Total Views: {}", result.get("totalViews"));
        logger.info("Average Total Duration: {}", result.get("avgTotalDuration"));
        logger.info("Most Visited Page: {}", result.get("mostVisitedPage"));
        logger.info("Page Data:");
        ((List<Map<String, Object>>) result.get("pageData")).forEach(page ->
                logger.info("  Page: {}, Views: {}, Avg Duration: {}",
                        page.get("pagePath"), page.get("views"), page.get("avgDuration"))
        );
    }

}
