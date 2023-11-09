package com.springboot.models;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.ArrayList;
import java.util.List;

public class TestResultDTO {

    int totalTestCount;
    int failedTestCount;
    List<FailedTest> failureList;


    public TestResultDTO(int totalTests, int failedTests, List<FailedTest> failureList) {
        this.totalTestCount = totalTests;
        this.failedTestCount = failedTests;
        this.failureList = failureList;
    }

    public TestResultDTO(Result result) {
        this.totalTestCount = result.getRunCount();
        this.failedTestCount = result.getFailureCount();
        failureList = new ArrayList<>();
        for (Failure failure: result.getFailures()) {
            failureList.add(new FailedTest(failure.getTestHeader(), failure.getTrimmedTrace()));
        };
    }

    public int getTotalTestCount() {
        return totalTestCount;
    }

    public int getFailedTestCount() {
        return failedTestCount;
    }

    public List<FailedTest> getFailureList() {
        return failureList;
    }

    private class FailedTest {
        String testName;
        String description;

        public String getTestName() {
            return testName;
        }

        public String getDescription() {
            return description;
        }

        public FailedTest(String testName, String description) {
            this.testName = testName;
            this.description = description;
        }

        public FailedTest() {
        }
    }

}
