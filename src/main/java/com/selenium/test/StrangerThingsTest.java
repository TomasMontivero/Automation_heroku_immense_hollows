package com.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleTest.class)
public class StrangerThingsTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    WebDriver driver;

    @Test
    public void sample_1() {
        System.out.println("Running test: 1");
    }

    @Test
    public void sample_2() {
        System.out.println("Running test: 2");
    }

    @Test
    public void sample_3() {
        System.out.println("Running test: 3");
        driver.get("www.google.com");
        System.out.println(driver.getCurrentUrl());
    }

    @Before
    public void setupEach() {
        driver = new ChromeDriver();
    }

    @After
    public void finishEach() {
        driver.close();
    }

}
