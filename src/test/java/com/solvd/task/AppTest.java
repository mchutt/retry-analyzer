package com.solvd.task;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class AppTest extends AbstractTest {

    @Test
    public void testAlmostAlwaysFail() {
        pause(1);
        Assert.assertEquals(new Random().nextInt(15), 2);
    }

    @Test
    public void testAlwaysFail() {
        Assert.fail();
    }

    @Test
    public void testAddTwoPlusTwo() {
        int add = 2 + 2;
        pause(1);
        Assert.assertEquals(add, 5, "This test should always fail");
    }

}
