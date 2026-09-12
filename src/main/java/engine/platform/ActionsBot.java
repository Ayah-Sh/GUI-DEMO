package engine.platform;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;


public class ActionsBot {


    Wait<AppiumDriver> wait;

    public ActionsBot(Wait<AppiumDriver> wait) {
        this.wait = wait;
    }


    public ActionsBot type(By locator, String text) {
        wait.until(d -> {
            System.out.println("Typing '" + text + "' into '" + locator + "'. ");
            d.findElement(locator).sendKeys(text, Keys.ENTER);
            System.out.println("Typing was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot click(By locator) {
        wait.until(d -> {
            System.out.println("Clicking '" + locator + "'. ");
            d.findElement(locator).click();
            System.out.println("Clicking was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot clickIfSelected(By locator) {
        if (isElementSelected(locator)) {
            System.out.println("Element already selected, clicking to reset: " + locator);
            click(locator);
        } else {
            System.out.println("Element not selected, skipping click: " + locator);
        }
        return this;
    }


    public ActionsBot longPress(By locator) {
        wait.until(d -> {
            System.out.println("Long pressing '" + locator + "'. ");
            WebElement element = d.findElement(locator);

            assertNotNull(((RemoteWebElement) element).getId());

            d.executeScript("mobile: touchAndHold", Map.of(
                    "elementId", ((RemoteWebElement) element).getId(),
                    "duration", 1  // seconds
            ));
            System.out.println("Long pressing was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot swipeLeft(By locator) {
        wait.until(d -> {
            System.out.println("Swiping '" + locator + "'. ");
            WebElement element = d.findElement(locator);
            assertNotNull(((RemoteWebElement) element).getId());
            d.executeScript("mobile: swipe", Map.of(
                    "element", ((RemoteWebElement) element).getId(),
                    "direction", "left"

            ));
            System.out.println("Swiping was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot assertElementISNotDisplayed(By locator) {

        wait.until(d -> {
            List<WebElement> deletedArticle = d.findElements(locator);
            System.out.println("Asserting element is not displayed: " + locator);
            Assert.assertTrue(deletedArticle.isEmpty(), "Assertion FAILED");
            System.out.println("Assertion PASSED Element is not displayed: " + locator);
            return true;
        });
        return this;
    }

    public ActionsBot assertElementISDisplayed(By locator) {
        wait.until(d -> {
            System.out.println("Asserting element is displayed: " + locator);
            Assert.assertTrue(d.findElement(locator).isDisplayed(), "Assertion FAILED");
            System.out.println("Assertion PASSED Element is displayed: " + locator);
            return true;
        });
        return this;
    }

    public ActionsBot navigateBack(By locator, String fromPage, String toPage) {
        wait.until(d -> {
            System.out.println("Navigating back from " + fromPage + " to " + toPage);
            d.findElement(locator).click();
            System.out.println("Navigating back was successful.");
            return true;
        });
        return this;
    }


    public boolean isElementSelected(By locator) {
        WebElement element = wait.until(d -> {
            WebElement e = d.findElement(locator);
            System.out.println("Found element: " + locator);
            return e;
        });

        boolean selected = element.isSelected();
        System.out.println("Element " + locator + " selected: " + selected);
        return selected;
    }
}
