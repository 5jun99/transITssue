package com.project.transit.fetch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrafficScheduler {
    private final IssueFetchService issueFetchService;

    @Scheduled(fixedRate = 300000)
    public void fetchAccidents() {
        issueFetchService.fetch();
    }
}
