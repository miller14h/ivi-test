package ivi.google.pages;

import ivi.google.utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*Страница Google Play*/
public class PlayGooglePage {
    private String appRating;
    private WebDriver driver = Browser.getWebDriver();

    private By appRatingOnPlayGoogle = By.cssSelector("div.BHMmbe");//Оценка приложения в Google Play

    public PlayGooglePage(String rating) {
        if (!driver.getTitle().contains("Google Play")) {
            throw new IllegalStateException("This is not Google Play:" + driver.getTitle());
        }
        appRating = rating;
    }

    /*Рейтинг приложения совпадает с тем что было в поиске?*/
    public boolean ratingOnGooglePlayMatchInSearch() {
        return driver.findElement(appRatingOnPlayGoogle).getText().equals(appRating);
    }
}
