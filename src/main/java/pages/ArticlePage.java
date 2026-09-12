package pages;

import engine.platform.ActionsBot;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ArticlePage {
    ActionsBot bot;

    // Locators
    private static final By articleSaveButton = AppiumBy.accessibilityId("Article Save Button");
    private static final By backButton = AppiumBy.accessibilityId("BackButton");

    public ArticlePage(ActionsBot bot) {
        this.bot = bot;
    }

    public ReadingListPage saveArticleToReadingList() {
        // reset state if already saved then long press
        bot.clickIfSelected(articleSaveButton);
        bot.longPress(articleSaveButton);
        return new ReadingListPage(bot);
    }

    public SearchPage navigateToSearchPageFromArticlePage() {
        bot.navigateBack(backButton, "Article Page", "Home Page");
        return new SearchPage(bot);
    }

}