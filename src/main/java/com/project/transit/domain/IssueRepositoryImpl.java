package com.project.transit.domain;

import com.querydsl.jpa.JPQLQueryFactory;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class IssueRepositoryImpl implements IssueRepositoryCustom {
    private final JPQLQueryFactory queryFactory;

    @Override
    public Set<String> findAllAccIds() {
        QIssue issue = QIssue.issue;

        return new HashSet<>(
                queryFactory
                        .selectDistinct(issue.accId)
                        .from(issue)
                        .fetch()
        );
    }

    @Override
    public void markAsDone(Set<String> toCompleteIds) {
        QIssue issue = QIssue.issue;

        queryFactory.update(issue)
                .set(issue.issueStatus, IssueStatus.DONE)
                .where(issue.accId.in(toCompleteIds))
                .execute();
    }
}
