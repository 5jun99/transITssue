package com.project.transit.domain;

import com.project.transit.fetch.ApiResponseParser.IssueDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "traffic_issues")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Issue {

    private static final Map<String, IssueCategory> CATEGORY_MAP = Map.ofEntries(
            Map.entry("A01", IssueCategory.ACCIDENT),
            Map.entry("A02", IssueCategory.ACCIDENT),
            Map.entry("A03", IssueCategory.ACCIDENT),
            Map.entry("A04", IssueCategory.CONSTRUCTION),
            Map.entry("A05", IssueCategory.CONSTRUCTION),
            Map.entry("A06", IssueCategory.ACCIDENT),
            Map.entry("A07", IssueCategory.ACCIDENT),
            Map.entry("A08", IssueCategory.WEATHER),
            Map.entry("A09", IssueCategory.WEATHER),
            Map.entry("A10", IssueCategory.OTHER),
            Map.entry("A11", IssueCategory.OTHER),
            Map.entry("A12", IssueCategory.OTHER),
            Map.entry("A13", IssueCategory.OTHER)
    );

    @Id
    private String accId;

    private LocalDate occrDate;

    private LocalTime occrTime;
    private LocalDate expClrDate;
    private LocalTime expClrTime;

    @Enumerated(EnumType.STRING)
    private IssueCategory issueCategory;

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    private String linkId;
    private double x;
    private double y;
    private String accInfo;
    private String accRoadCode;

    public static Issue ofDto (IssueDto issueDto) {
        return new Issue(
                issueDto.getAccId(),
                issueDto.getOccrDate(),
                issueDto.getOccrTime(),
                issueDto.getExpClrDate(),
                issueDto.getExpClrTime(),
                CATEGORY_MAP.get(issueDto.getAccType()),
                IssueStatus.ACTIVE,
                issueDto.getLinkId(),
                issueDto.getGrs80tmX(),
                issueDto.getGrs80tmY(),
                issueDto.getAccInfo(),
                issueDto.getAccRoadCode()
        );
    }

    public void updateStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public void updateFromDto(IssueDto issueDto) {

    }
}
