package wiki;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.ArticlePage;
import pages.HomePage;
import pages.ReadingListPage;
import pages.SearchPage;
import templates.TestScenario;

@Epic("Wikipedia iOS App")
@Feature("Reading Lists")
public class WikipediaTest extends TestScenario {

    //Variables
    String articleName = "Artificial intelligence";
    String readingListName = "AI Reading List";

    /**
     * Scenario: Save an Article to a Reading List
     * 1. Launch the Wikipedia mobile application.
     * 2. Search for the article “Artificial Intelligence”.
     * 3. Open the article from the search results.
     * 4. Save the article.
     * 5. Add article to a reading list
     * 6. Create new reading list
     * 7. Navigate to the Reading Lists section.
     * 8. Search for the new created reading list
     * 9. Verify that the saved article is displayed in the Reading List.
     */

    @Story("Save article to a new Reading List")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Searches for an article, saves it into a newly created reading list, "
            + "and verifies it appears there. Cleans up by deleting the list afterward.")


    @Test
    public void testSaveArticleToReadingList() {

        SearchPage searchPage;
        ArticlePage articlePage;
        ReadingListPage readingListPage;


        // Step 1: Launch the Wikipedia mobile application.
        HomePage homePage = new HomePage(bot);

        // Step 2: Search for the article “Artificial Intelligence”.
        homePage.clickSearchBox();
        searchPage = new SearchPage(bot);
        searchPage.searchForArticle(articleName);

        // Step 3: Open the article from the search results.
        searchPage.openResultArticle(articleName);

        // Step 4: Save the article.
        articlePage = new ArticlePage(bot);
        articlePage.saveArticleToReadingList();

//        //Step 3 & 4 Fluent chaining:
//        searchPage.openResultArticle(articleName).saveArticleToReadingList();

        // Step 5: Add article to a reading list
        //Step 6: Create new reading list
        readingListPage = new ReadingListPage(bot);
        readingListPage.createNewReadingList(readingListName);


        // Step 7: Navigate to the Reading Lists section.
        articlePage.navigateToSearchPageFromArticlePage();
        homePage.navigateToHomePage();
        homePage.clickSavedArticles();

        // Step 8: Search for the new created reading list
        readingListPage.navigateToReadingList();
        readingListPage.searchForReadingList(readingListName);
        readingListPage.assertCreatedReadingListIsDisplayed(readingListName);

        // Step 9: Verify that the saved article is displayed in the Reading List.
        readingListPage.openReadingList(readingListName);
        readingListPage.assertThatArticleIsDisplayed(articleName);

        // Clean up: Delete the created reading list
        readingListPage.deleteReadingList();
        readingListPage.validateThatArticleIsDeleted(readingListName);

        //Navigate back to Home
        readingListPage.navigateBackToHome();

    }


}
