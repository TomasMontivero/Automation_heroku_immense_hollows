package org.example.controller;

import org.junit.internal.TextListener;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.runner.JUnitCore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.junit.platform.launcher.LauncherDiscoveryRequest;


@RestController
@RequestMapping("/oldautomation")
public class OldAPIController {


    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    @GetMapping("/health")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("OLD - Test endpoint: OK");
    }

    @GetMapping("/v1")
    public ResponseEntity<String> automationEndpoint() {
        String className = "HerokuTest";
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(className)).build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        TestExecutionSummary summary = listener.getSummary();
        long totalTests = summary.getTestsStartedCount();
        long failedTests = summary.getTestsFailedCount();
        long successfulTests = summary.getTestsSucceededCount();

        return ResponseEntity.ok("Test endpoint: OK - Total: " + totalTests + " - Failed: " + failedTests + " - Success: " + successfulTests);
    }

    @GetMapping("/v2")
    public ResponseEntity<String> automationEndpoint_v2() {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                //.selectors(selectClass(FirstUnitTest.class))
                .selectors(DiscoverySelectors.selectClass("HerokuTest.class"))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        return ResponseEntity.ok("Test endpoint: OK");
    }

    @GetMapping("/v3")
    public ResponseEntity<String> automationEndpoint_v3() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        //junit.run(FirstUnitTest.class);
        try {
            junit.run(Class.forName("HerokuTest.java"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok("Test endpoint: OK");
    }

}
