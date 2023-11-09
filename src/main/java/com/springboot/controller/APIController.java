package com.springboot.controller;

import com.selenium.test.HerokuTest;
import com.selenium.test.SampleTest;
import com.selenium.test.StrangerThingsTest;
import com.springboot.models.TestResultDTO;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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


    @GetMapping("/smoke")
    public ResponseEntity<TestResultDTO> runSmokeTest() {
        logger.info("Endpoint: /smoke - Job: runSmokeTest");
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(HerokuTest.class);
        return ResponseEntity.ok(new TestResultDTO(result));
    }


}
