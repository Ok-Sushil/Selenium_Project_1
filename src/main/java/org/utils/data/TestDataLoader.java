package org.utils.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestDataLoader {

    private final String jsonFilePath;
    private JsonNode testData;

    public TestDataLoader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public void loadTestData() throws IOException {
        // Resolve the relative path to an absolute path
        URL resourceUrl = getClass().getClassLoader().getResource(jsonFilePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Test data file not found: " + jsonFilePath);
        }

        File file = new File(resourceUrl.getFile());
        if (!file.exists()) {
            throw new IllegalArgumentException("Test data file does not exist: " + jsonFilePath);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        testData = objectMapper.readTree(file);
    }

    public JsonNode getTestData() {
        return testData;
    }
}
