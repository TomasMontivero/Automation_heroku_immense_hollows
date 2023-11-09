package com.springboot.controller;

import com.selenium.heroku.test.HerokuTest;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/automation")
public class APIController {


    @GetMapping("/health")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("-------- @GetMapping(health)");
        return ResponseEntity.ok("Test endpoint: OK");
    }

    @GetMapping("/v3")
    public ResponseEntity<String> automationEndpoint_v3() {
        System.out.println("-------- @GetMapping(v1)");
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(HerokuTest.class);
        int runCount = result.getRunCount();
        int runFailure = result.getFailureCount();
        boolean success = result.wasSuccessful();
        List<Failure> failureList = result.getFailures();

        return ResponseEntity.ok("- Success: " + success + " - Failures: " + runFailure + " - Run count: " + runCount + " - FailureList: " + failureList);
    }

}
