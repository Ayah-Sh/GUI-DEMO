package pages;

import engine.platform.ActionsBot;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class SearchPage {

    ActionsBot bot;

    // Locators
    private static final By searchFieldInput = AppiumBy.accessibilityId("Search Field");

    public SearchPage(ActionsBot bot) {
        this.bot = bot;
    }

    public void searchForArticle(String ArticleName) {
        bot.type(searchFieldInput, ArticleName);
    }


    public ArticlePage openResultArticle(String ArticleName) {
        bot.click(searchResultByText(ArticleName));
        return new ArticlePage(bot);
    }


    private By searchResultByText(String text) {
        return AppiumBy.accessibilityId(text);
    }
}
