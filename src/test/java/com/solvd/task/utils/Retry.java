package com.solvd.task.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.solvd.task.utils.PropertiesLoader.PropertySource.*;

public class Retry implements IRetryAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Retry.class);
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT;
    public static final String PROPERTY_NAME = "retry_count";

    static {
        int maxRetryCount = Integer.parseInt(PropertiesLoader.getProperty(APP, PROPERTY_NAME, "0"));
        if (maxRetryCount == 0)
            LOGGER.warn("Property with name: '{}' not found in properties file. Setting the default value as: {}", PROPERTY_NAME, maxRetryCount);
        MAX_RETRY_COUNT = maxRetryCount;
    }

    @Override
    public boolean retry(final ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (this.retryCount < MAX_RETRY_COUNT) {
                this.retryCount++;
                LOGGER.info("Retrying test {} for the {} time(s).", iTestResult.getName(), retryCount);
                return true;
            }
        }
        return false;
    }
}
