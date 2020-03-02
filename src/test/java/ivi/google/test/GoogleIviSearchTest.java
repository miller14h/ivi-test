package ivi.google.test;

import ivi.google.ProtoTest;
import ivi.google.pages.GoogleMainPage;
import ivi.google.pages.search.GoogleSearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleIviSearchTest extends ProtoTest {
    private static final String IVI = "ivi";
    private GoogleSearchPage googleSearchPage;

    @BeforeEach
    void before() {
        openGoogle();
        googleSearchPage = new GoogleMainPage()
                .searchByString(IVI);

    }

    @DisplayName("Поиск больших картинок по запросу")
    @Test
    void extraSizeImageTest() {
        List<WebElement> searchResult = googleSearchPage
                .clickSearchByImages()
                .getToolsToolbar()
                .searchByExtraSizeImage()
                .imageSearchPage
                .getSearchResultHrefList();
        assertTrue(searchResult.stream()
                .filter(webElement -> webElement.getText().contains("ivi.ru"))
                .count() >= 3);
    }

    @DisplayName("Отображения рейтинга приложения в Google Play")
    @Test
    void ratingGooglePlayTest() {
        boolean isMatch = googleSearchPage
                .goToPlayGoogle()
                .ratingOnGooglePlayMatchInSearch();
        assertTrue(isMatch);
    }

    @DisplayName("Наличие ссылки в wiki")
    @Test
    void wikiTest() {
        String url = googleSearchPage
                .goToWiki()
                .getUrlFromInfoBox();
        assertTrue(url.contains("https://www.ivi.ru/"));
    }

    @AfterEach
    void after() {
        clearCookies();
    }


}
