package ivi.google.pages;

import ivi.google.utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*Страница Википедии*/
public class WikiPage {
    private WebDriver driver = Browser.getWebDriver();

    private By infoBox = By.cssSelector("table.infobox");//Таблица с информацией
    private By urlLine = By.cssSelector("a[href^=\"http\"]");//URL в таблице с информацией

    public WikiPage() {
        if (!driver.getTitle().contains("Википедия")) {
            throw new IllegalStateException("This is not Wiki:" + driver.getTitle());
        }
    }

    /*Урл из таблицы с информацией*/
    public String getUrlFromInfoBox() {
        return driver.findElement(infoBox).findElement(urlLine).getAttribute("href");
    }
}
