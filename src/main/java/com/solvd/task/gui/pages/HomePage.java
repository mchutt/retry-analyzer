package com.solvd.task.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPageBase {
    @FindBy(css = "h1")
    private WebElement pageTitle;

    @FindBy(id = "ea2t")
    private WebElement emailInput;

    @FindBy(id = "send_test_btn")
    private WebElement sendBtn;

    @FindBy(xpath = "//div[@class='success']/a")
    private WebElement successfulEmailId;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email) {
        sendKeysToElement(emailInput, email);
    }

    public void clickSendBtn() {
        clickElement(sendBtn);
    }

    public boolean isPageOpened() {
        waitUntil(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.isDisplayed();
    }

    public String getSuccessfulEmailId() {
        waitUntil(ExpectedConditions.visibilityOf(successfulEmailId));
        return successfulEmailId.getText();
    }

}
