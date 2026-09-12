package pages;

import engine.platform.ActionsBot;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ReadingListPage {
    ActionsBot bot;

    // Locators

    //create reading list button
    private static final By readingListDoneButton = AppiumBy.accessibilityId("Create a new list");

    //reading list name input field
    private static final By readingListNameInput = AppiumBy.iOSNsPredicateString("value == 'reading list title'");

    //New reading list section submit button
    private static final By newReadingListSubmitButton = AppiumBy.name("Create reading list");

    //reading list section
    private static final By readingListSection = AppiumBy.accessibilityId("Reading lists");


    //Reading List Search input field
    private static final By readingListSearchInput = AppiumBy.accessibilityId("Search Field");

    //Delete button for Article list

    private static final By deleteArticleButtonLocator = AppiumBy.accessibilityId("swipe action delete");
    //Delete button for a reading list
    private static final By deleteListButtonLocator = AppiumBy.accessibilityId("Delete");

    //back button locator
    private static final By backButton = AppiumBy.accessibilityId("BackButton");

    private static final By readingHomeButton = AppiumBy.accessibilityId("Root Tab Home Button");
    private static By newlyCreatedListLocator;


    public ReadingListPage(ActionsBot bot) {
        this.bot = bot;
    }


    public ArticlePage createNewReadingList(String readingListName) {
        bot.click(readingListDoneButton);
        bot.type(readingListNameInput, readingListName);
        bot.click(newReadingListSubmitButton);
        return new ArticlePage(bot);
    }


    public void navigateToReadingList() {
        bot.click(readingListSection);

    }

    public void assertCreatedReadingListIsDisplayed(String readingListName) {
        //locator for the created reading list
        By createdReadingListLocator = AppiumBy.accessibilityId(readingListName);
        bot.assertElementISDisplayed(createdReadingListLocator);
    }

    public void searchForReadingList(String readingListName) {
        bot.type(readingListSearchInput, readingListName);
    }



    public void openReadingList(String readingListName) {
        newlyCreatedListLocator = AppiumBy.name(readingListName);
        bot.click(newlyCreatedListLocator);
    }

    public void assertThatArticleIsDisplayed(String articleName) {
        By articleLocator = AppiumBy.accessibilityId(articleName);
        bot.assertElementISDisplayed(articleLocator);
    }


    public void deleteReadingList() {
        bot.navigateBack(backButton, "article page", "reading list page");
        bot.swipeLeft(AppiumBy.name("AI Reading List"));
//        bot.assertElementISDisplayed(deleteArticleButtonLocator);
        bot.click(deleteArticleButtonLocator);
        bot.longPress(deleteListButtonLocator);
    }


    public void validateThatArticleIsDeleted(String readingList) {
        newlyCreatedListLocator = AppiumBy.name(readingList);
        bot.assertElementISNotDisplayed(newlyCreatedListLocator);
    }


    public void navigateBackToHome(){
        bot.navigateBack(readingHomeButton,"Reading list","HomePage");
    }
}
