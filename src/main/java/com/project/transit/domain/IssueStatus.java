package com.project.transit.domain;

public enum IssueStatus {
    ACTIVE("진행 중"),
    DONE("완료");

    private final String text;

    IssueStatus(String text) {
        this.text = text;
    }
}
