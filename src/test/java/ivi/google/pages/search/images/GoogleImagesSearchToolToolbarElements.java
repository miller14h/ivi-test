package ivi.google.pages.search.images;

import ivi.google.utils.Browser;
import ivi.google.utils.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**/
public class GoogleImagesSearchToolToolbarElements extends WebElementHelper {
    public GoogleImagesSearchPage imageSearchPage;
    private WebDriver driver = Browser.getWebDriver();
    private By toolsToolbar = By.cssSelector("div.xFo9P");//Инструменты в тулбаре
    private By extraSize = By.cssSelector("a[href*=\"3Al\"]");//Настройка Размеры "Большие"

    public GoogleImagesSearchToolToolbarElements(GoogleImagesSearchPage googleImagesSearchPage) {
        imageSearchPage = googleImagesSearchPage;
    }

    /*Поиск по Большим картинкам*/
    public GoogleImagesSearchToolToolbarElements searchByExtraSizeImage() {
        WebElement sizeButton = driver.findElements(toolsToolbar).get(0);
        waitVisibleEnable(sizeButton);
        sizeButton.click();
        WebElement extraSizeButton = driver.findElement(this.extraSize);
        waitVisibleEnable(extraSizeButton);
        extraSizeButton.click();
        return this;
    }
}
