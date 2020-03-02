package ivi.google.pages.search.images;

import ivi.google.utils.Browser;
import ivi.google.utils.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*Страница поиска по картинкам*/
public class GoogleImagesSearchPage extends WebElementHelper {
    private WebDriver driver = Browser.getWebDriver();

    private By toolsButton = By.cssSelector("div.ZXJQ7c");//Инструменты
    private By searchHrefResults = By.cssSelector("div.islrc > div div.fxgdke");//Ссылки в результатах поиска(можно реализовать по href в родительском элементе)


    public GoogleImagesSearchPage() {
        if (!driver.getCurrentUrl().contains("tbm=isch")) {
            throw new IllegalStateException("This is not Images search:" + driver.getCurrentUrl());
        }
    }

    /*Вызов тулбара с инструментами поиска по картинкам*/
    public GoogleImagesSearchToolToolbarElements getToolsToolbar() {
        WebElement webElement = driver.findElement(toolsButton);
        waitVisibleEnable(webElement);
        webElement.click();
        return new GoogleImagesSearchToolToolbarElements(this);
    }

    /*Список элементов полученных после поиска*/
    public List<WebElement> getSearchResultHrefList() {
        return driver.findElements(searchHrefResults);
    }
}
