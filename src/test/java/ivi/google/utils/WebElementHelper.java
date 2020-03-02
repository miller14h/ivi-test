package ivi.google.utils;

import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class WebElementHelper {

    /*"Умное" ожидание Visible элемента*/
    protected static void waitVisible(WebElement webElement) {
        int time = 0;
        while (!webElement.isDisplayed() && (time < 1000)) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }

    /*"Умное" ожидание Enable элемента*/
    protected static void waitEnable(WebElement webElement) {
        int time = 0;
        while (!webElement.isEnabled() && (time < 1000)) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }

    /*"Умное" ожидание Visible&Enable элемента*/
    protected static void waitVisibleEnable(WebElement webElement) {
        int time = 0;
        while ((!webElement.isEnabled() && !webElement.isDisplayed()) && (time < 1000)) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }
}
