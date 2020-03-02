package ivi.google.pages;

import ivi.google.pages.search.GoogleSearchPage;
import ivi.google.utils.Browser;
import ivi.google.utils.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*Главная страница Google*/
public class GoogleMainPage extends WebElementHelper {
    private WebDriver driver = Browser.getWebDriver();

    private By googleSearchField = By.cssSelector("input.gsfi");//Строка поиска

    public GoogleMainPage() {
        if (!driver.getTitle().equals("Google")) {
            throw new IllegalStateException("This is not Google:" + driver.getTitle());
        }
    }

    /*Поиск в Google по заданнаму значению*/
    public GoogleSearchPage searchByString(String string) {
        WebElement webElement = driver.findElement(googleSearchField);
        waitVisibleEnable(webElement);
        webElement.sendKeys(string);
        webElement.submit();
        return new GoogleSearchPage();
    }
}
