package com.project.transit.fetch;

import com.project.transit.config.FetchApiProperties;
import com.project.transit.domain.Issue;
import com.project.transit.domain.IssueRepository;
import com.project.transit.fetch.ApiResponseParser.IssueDto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class IssueFetchService {
    private final WebClient webClient;
    private final FetchApiProperties fetchApiProperties;
    private final IssueRepository issueRepository;

    @Transactional
    public void fetch() {
        String infoUrl = fetchApiProperties.getAccident().getInfoUrl();

        String response = webClient.get()
                .uri(infoUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        List<IssueDto> issueDtos = ApiResponseParser.parseIssue(response);

        Set<String> existingIds = issueRepository.findAllAccIds();

        Set<String> newIds = issueDtos.stream()
                .map(IssueDto::getAccId)
                .collect(Collectors.toSet());

        List<Issue> inserts = issueDtos.stream().filter(issueDto -> !existingIds.contains(issueDto.getAccId()))
                .map(Issue::ofDto)
                .toList();

        List<Issue> updates = issueDtos.stream().filter(issueDto -> existingIds.contains(issueDto.getAccId()))
                .map(Issue::ofDto)
                .toList();

        Set<String> toCompleteIds = existingIds.stream()
                .filter(id -> !newIds.contains(id))
                .collect(Collectors.toSet());

        issueRepository.saveAll(inserts);
        issueRepository.saveAll(updates);
        issueRepository.markAsDone(toCompleteIds);
    }
}
