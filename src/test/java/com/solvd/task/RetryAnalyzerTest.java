package com.solvd.task;


import com.zebrunner.agent.core.annotation.TestCaseKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RetryAnalyzerTest extends AbstractTest {

    @Test
    @TestCaseKey("MATEOC-73")
    public void testAlmostAlwaysFail() {
        pause(1);
        Assert.assertEquals(new Random().nextInt(15), 2);
    }

    @Test
    @TestCaseKey("MATEOC-74")
    public void testAlwaysFail() {
        Assert.fail();
    }

    @Test
    @TestCaseKey("MATEOC-75")
    public void testAddTwoPlusTwo() {
        int add = 2 + 2;
        pause(1);
        Assert.assertEquals(add, 5, "This test should always fail");
    }

}
