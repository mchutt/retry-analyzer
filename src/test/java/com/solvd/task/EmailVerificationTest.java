package com.solvd.task;

import com.solvd.task.utils.EmailVerification;
import com.zebrunner.agent.core.annotation.TestCaseKey;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmailVerificationTest extends AbstractTest {

    private final EmailVerification service = new EmailVerification();

    @DataProvider(name = "emailVerificationData")
    public Object[][] emailVerificationData() {
        return new Object[][]{
                {"7c26a50713367c58e93705264ce2753a", true}, // Positive test case
                {"fake id token", false}                    // Negative test case
        };
    }

    @Test(dataProvider = "emailVerificationData")
    @TestCaseKey({"MATEOC-76", "MATEOC-77"})
    public void emailVerificationTest(String token, boolean expectedResult) {
        boolean isMessagePresent = service.isMessagePresent(token);
        Assert.assertEquals(isMessagePresent, expectedResult, "Unexpected result for token: " + token);
    }

}
