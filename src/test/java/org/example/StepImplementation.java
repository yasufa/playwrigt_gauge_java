package org.example;

import com.microsoft.playwright.*;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.example.LocatorReader.getLocator;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation extends BaseTest {


    LocatorReader locaterReader;


    @Step("<key> li elementlerden <text> degerine esit olana tikla")
    public void clickElementByTxt(String key, String text){
        try {Locator elements = getLocator(page, key);
            int count = elements.count();

            for (int i = 0; i < count; i++) {
                String elementText = elements.nth(i).innerText();
                if (elementText.equals(text)){
                    elements.nth(i).click();
                    break;
                }
            }} catch (Exception e){
            System.out.println(text + " elementi bulunamadı.");
        }
    }
    @Step("<key> elementi <text> iceriyor mu?")
    public void chechTextContains(String key, String text){
        Locator element = getLocator(page, key);
        if (!(element.innerText().contains(text))){
            Assert.fail("\nBeklenen Text: "+text + " \nElement Text: "+element.innerText());
        }
    }

    @Step("<url> urle git")
    public void goToUrl(String url) {
        page.navigate(url);
    }

    @Step("<second> sn bekle")
    public void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("<key> elemente tıkla")
    public void clickElement(String key) {
        Locator element = getLocator(page, key);
        element.click();
    }

    @Step("<key> elementi görünüyor mu?")
    public void checkElementVisible(String key) {
        try {
            Locator element = getLocator(page, key);
            if (!element.isVisible()){
                Assert.fail("Element görünür değil.");
            }
        } catch (Exception e){
            Assert.assertTrue("Element görünür değil.", false);
        }

    }

    @Step("<key> elemente <text> yaz")
    public void typeText(String key, String text) {
        Locator selector = getLocator(page, key);
        selector.fill(text);

    }

    @Step("Enter tusuna tıklanır")
    public void pressEnterKey() {
        page.keyboard().press("Enter");
    }





    @Step("Giriş başarılı olmalıdır")
    public void verifyLoginSuccess() {
        assertTrue(getLocator(page, "logoutButton").isVisible());
    }


    }




