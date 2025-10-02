package com.project.transit.domain;

public enum IssueCategory {
    ACCIDENT("사고"),
    CONSTRUCTION("공사"),
    WEATHER("기상"),
    OTHER("기타");

    private final String text;

    IssueCategory(String text) {
        this.text = text;
    }
}
