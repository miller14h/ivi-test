package ivi.google.pages.search;

import ivi.google.pages.PlayGooglePage;
import ivi.google.pages.WikiPage;
import ivi.google.pages.search.images.GoogleImageSearchPage;
import ivi.google.utils.Browser;
import ivi.google.utils.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*Поисковая страница Google*/
public class GoogleSearchPage extends WebElementHelper {
    private WebDriver driver = Browser.getWebDriver();

    private By searchToolbar = By.cssSelector("div#hdtb-msb-vis");//тулбар с конкретизацией поиска
    private By searchByImagesButton = By.cssSelector("a[href*=\"tbm=isch\"]");//кнопка поиска по картинкам
    private By searchResultTable = By.cssSelector("div#search");//Таблица с результами поиска
    private By searchResults = By.cssSelector("div.g");//Результаты поиска
    private By navigateBar = By.cssSelector("div#navcnt");//Бар навигации по страницам
    private By nextButtonNavigationBarRu = By.xpath("//*[text() = 'Следующая']");//Кнопка Следующая
    private By ratingGooglePlay = By.cssSelector("div.slp");//Рейтинг в Google Play
    private By searchResultTitle = By.cssSelector("h3.LC20lb");//Заголовок у найденного результата


    public GoogleSearchPage() {
        if (!driver.getTitle().contains("Поиск в Google")) {
            throw new IllegalStateException("This is not Google search:" + driver.getTitle());
        }
    }

    /*Выбор поиска по картинкам*/
    public GoogleImageSearchPage clickSearchByImages() {
        WebElement webElement = driver.findElement(searchToolbar).findElement(searchByImagesButton);
        waitVisibleEnable(webElement);
        webElement.click();
        return new GoogleImageSearchPage();
    }

    /*Переход на страницу из поиска в Google Play*/
    public PlayGooglePage goToPlayGoogle() {
        WebElement webElement = searchResultFromHref("https://play.google.com/");
        String rating = webElement.findElement(ratingGooglePlay).getText().substring(9, 12);
        webElement.findElement(searchResultTitle).click();
        return new PlayGooglePage(rating);
    }

    public WikiPage goToWiki() {
        WebElement webElement = searchResultFromHref("https://ru.wikipedia.org/");
        webElement.findElement(searchResultTitle).click();
        return new WikiPage();
    }

    /*Поиск элемента среди результатов по подходящей ссылке*/
    private WebElement searchResultFromHref(String href) {
        for (int i = 0; i < 5; i++) {
            WebElement searchResult = getSearchResults().stream()
                    .filter(webElement -> webElement.findElement(By.tagName("a")).getAttribute("href").contains(href))
                    .findFirst()
                    .orElse(null);
            if (searchResult != null) {
                return searchResult;
            }
            clickNextPage();
        }
        throw new IllegalStateException("Cannot search element by href:" + href);
    }

    /*Переключение на следующую страницу*/
    private void clickNextPage() {
        driver.findElement(navigateBar).findElement(nextButtonNavigationBarRu).click();
    }

    /*Получение списка всех элементов поиска на странице*/
    private List<WebElement> getSearchResults() {
        return driver.findElement(searchResultTable).findElements(searchResults);
    }
}
