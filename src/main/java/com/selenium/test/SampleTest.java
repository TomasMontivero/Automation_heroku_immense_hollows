package com.selenium.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleTest.class)
public class SampleTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Test
    public void sample_1() {
        System.out.println("Sample test 1 running");
    }

    @Test
    public void sample_2() {
        System.out.println("Sample test 2 running");
    }

    @Test
    public void sample_3() {
        System.out.println("Sample test 3 running");
    }


}
