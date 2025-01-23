package com.solvd.task;


public abstract class AbstractTest {

    //helper methods
    protected void pause(long seconds) {
        long millis = seconds * 1000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
