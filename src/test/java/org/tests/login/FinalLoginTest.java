package org.tests.login;

import org.page.login.FinalLoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tests.utils.BaseTest;
import org.utils.data.TestDataLoader;
import org.utils.validator.TestDataValidator;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class FinalLoginTest extends BaseTest {


    private FinalLoginPage loginPage;
    private JsonNode testData;

    @BeforeClass
    @Override

    public void setup()  {
        super.setup();

        try {
            loginPage = new FinalLoginPage(driver);

            // Load test data
            TestDataLoader dataLoader = new TestDataLoader("testData/loginTestData.json");
            dataLoader.loadTestData();
            testData = dataLoader.getTestData();

            // Validate the test data
            TestDataValidator dataValidator = new TestDataValidator(testData);
            dataValidator.validateTestData();
        } catch (IOException e){
            throw new RuntimeException("failed to load data" ,e);
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        String email = testData.get("validUser").get("email").asText();
        String password = testData.get("validUser").get("password").asText();
        String loginUrl = testData.get("baseUrl").get("loginUrl").asText();

        driver.get(loginUrl);
        loginPage.login(email, password);

        boolean isLoginSuccessful = loginPage.isLoginSuccessful();
        Assert.assertTrue(isLoginSuccessful, "Login failed with valid credentials!");
        System.out.println("Login successful. Test case passed.");
    }

    @AfterClass
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
