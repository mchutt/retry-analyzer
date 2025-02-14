package com.solvd.task.gui.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPageBase.class);
    protected WebDriver driver;

    public AbstractPageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickElement(WebElement element) {
        LOGGER.debug("clickElement method invoked");
        waitUntil(ExpectedConditions.visibilityOf(element));
        element.click();
        LOGGER.info("Element clicked");
        LOGGER.debug("clickElement method ended");
    }

    protected void sendKeysToElement(WebElement element, CharSequence keys) {
        LOGGER.debug("sendKeys method invoked");
        waitUntil(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
        LOGGER.info("Keys '{}' sent ", keys);
        LOGGER.debug("sendKeys method ended");
    }

    protected void waitUntil(ExpectedCondition<WebElement> expectedCondition) {
        LOGGER.debug("Waiting... ");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(expectedCondition);
        } catch (TimeoutException exception) {
            LOGGER.error("TimeoutException. {} ", exception.getLocalizedMessage());
        }
        LOGGER.debug("Wait ended");
    }

}
