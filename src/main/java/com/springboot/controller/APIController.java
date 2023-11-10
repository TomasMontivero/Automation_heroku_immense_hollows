package com.springboot.controller;

import com.selenium.test.HerokuTest;
import com.springboot.models.TestResultDTO;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@RequestMapping("/automation")
public class APIController {


    Logger logger = Logger.getLogger("APIController");

    @GetMapping("/health")
    public ResponseEntity<String> testEndpoint() {
        logger.info("Endpoint: /health - Job: testEndpoint");
        return ResponseEntity.ok("Test endpoint: OK");
    }


    @GetMapping("/smoke/desktop")
    public ResponseEntity<TestResultDTO> runSmokeTestDesktop() {
        logger.info("Endpoint: /smoke/desktop - Job: runSmokeTestDesktop");
        System.setProperty("webdriver_size", "desktop");
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(HerokuTest.class);
        return ResponseEntity.ok(new TestResultDTO(result));
    }

    @GetMapping("/smoke/mobile")
    public ResponseEntity<TestResultDTO> runSmokeTestMobile() {
        logger.info("Endpoint: /smoke/mobile - Job: runSmokeTestMobile");
        System.setProperty("webdriver_size", "mobile");
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(HerokuTest.class);
        return ResponseEntity.ok(new TestResultDTO(result));
    }


}
