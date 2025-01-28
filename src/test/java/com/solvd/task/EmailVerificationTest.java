package com.solvd.task;

import com.solvd.task.gui.pages.HomePage;
import com.solvd.task.utils.EmailVerification;
import com.solvd.task.utils.PropertiesLoader;
import com.solvd.task.utils.Retry;
import com.zebrunner.agent.core.annotation.TestCaseKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.solvd.task.utils.PropertiesLoader.PropertySource.*;

public class EmailVerificationTest extends AbstractTest {
    private final String SEND_TEST_EMAIL_PAGE = "https://sendtestemail.com/";
    private final String USER_EMAIL = PropertiesLoader.getProperty(USER, "email");
    private final EmailVerification service = new EmailVerification();
    private WebDriver driver;
    private String successfulEmailId;

    @BeforeClass
    public void setUp() {
        initializeDriver();
        sendTestEmail();
    }


    @DataProvider(name = "provideEmailVerificationData")
    public Object[][] provideEmailVerificationData() {
        return new Object[][]{
                {successfulEmailId, true}, // Positive test case
                {"fake id token", false}   // Negative test case
        };
    }

    @Test(dataProvider = "provideEmailVerificationData", retryAnalyzer = Retry.class)
    @TestCaseKey({"MATEOC-76", "MATEOC-77"})
    public void emailVerificationTest(String token, boolean expectedResult) {
        boolean isMessagePresent = service.isMessagePresent(token);
        Assert.assertEquals(isMessagePresent, expectedResult, "Unexpected result for token: " + token);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // helper methods
    private void sendTestEmail() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened!");
        homePage.typeEmail(USER_EMAIL);
        homePage.clickSendBtn();
        successfulEmailId = homePage.getSuccessfulEmailId();
    }

    private void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SEND_TEST_EMAIL_PAGE);
    }
}
