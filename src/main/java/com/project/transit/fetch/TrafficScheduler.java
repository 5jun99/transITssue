package com.project.transit.fetch;

import com.project.transit.config.FetchApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrafficScheduler {
    private final IssueFetchService issueFetchService;

    @Scheduled(fixedRateString = "${fetch.api.fetch-interval}")
    public void fetchAccidents() {
        issueFetchService.fetch();
    }
}
