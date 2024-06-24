package com.savenkoff.study.task2;

public enum EmployeePosition {
    Engineer("Инженер"), StreetCleaner("Дворник");

    private String title;

    EmployeePosition(String title) {
        this.title = title;
    }
}
