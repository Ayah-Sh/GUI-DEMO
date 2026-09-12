package templates;

import driverManager.IOSDriverFactory;
import engine.platform.ActionsBot;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.time.Duration;

public abstract class TestScenario {
    protected IOSDriver driver;
    public ActionsBot bot;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        driver = IOSDriverFactory.createIOSLocalDriver();
        Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(AssertionError.class)
                .ignoring(StaleElementReferenceException.class);

        bot = new ActionsBot(wait);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
