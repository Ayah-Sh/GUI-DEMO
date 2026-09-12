package pages;

import engine.platform.ActionsBot;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePage {
    ActionsBot bot;

    // Locators

    //Search box input field
    private static final By searchBoxInput = AppiumBy.accessibilityId("Search Tab Button");

    //Home button
    private static final By homeButton = AppiumBy.accessibilityId("Home");

    //saved articles Home page button
    private static final By readingListsButton = AppiumBy.accessibilityId("Root Tab Saved Button");


    public HomePage(ActionsBot bot) {
        this.bot = bot;
    }


    public void clickSearchBox() {
        bot.assertElementISDisplayed(searchBoxInput);
        bot.click(searchBoxInput);
    }


    public ReadingListPage clickSavedArticles() {
        bot.click(readingListsButton);
        return new ReadingListPage(bot);
    }


    public void navigateToHomePage() {
        bot.assertElementISDisplayed(homeButton);
        bot.click(homeButton);
    }



}
