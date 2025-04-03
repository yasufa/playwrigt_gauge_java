package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocatorReader {
    private static JSONObject locators;

    static {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/test/resources/elements.json")));
            locators = new JSONObject(content);
        } catch (IOException e) {
            throw new RuntimeException("Locators JSON file not found", e);
        }
    }

    public static Locator getLocator(Page page, String key) {
        JSONObject locatorData = locators.getJSONObject(key);
        String type = locatorData.getString("type");
        String value = locatorData.getString("value");
        System.out.println("Element Type: "+ type + "   Element Value: " + value);

        switch (type.toLowerCase()) {
            case "css":
                return page.locator(value);
            case "xpath":
                return page.locator("xpath=" + value);
            case "id":
                return page.locator("id=" + value);
            case "name":
                return page.locator("name=" + value);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + type);
        }
    }
}
