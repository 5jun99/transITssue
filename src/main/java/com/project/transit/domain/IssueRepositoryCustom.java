package com.project.transit.domain;

import java.util.Set;

public interface IssueRepositoryCustom {
    Set<String> findAllAccIds();

    void markAsDone(Set<String> toCompleteIds);
}
