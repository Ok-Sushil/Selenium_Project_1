package org.utils.validator;

import com.fasterxml.jackson.databind.JsonNode;

public class TestDataValidator {

    private final JsonNode testData;

    public TestDataValidator(JsonNode testData) {
        this.testData = testData;
    }

    public void validateTestData() {
        String[] requiredKeys = {"validUser", "invalidUser", "baseUrl"};
        for (String key : requiredKeys) {
            if (!testData.has(key)) {
                throw new IllegalArgumentException("Missing required key in test data: " + key);
            }
        }
    }
}
