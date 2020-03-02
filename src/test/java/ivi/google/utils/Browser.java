package ivi.google.utils;

import ivi.google.ProtoTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/*Класс для сокрытия действий с WebDriver и для возможности взять уже созданный инстанс броузера*/
public class Browser {
    private static final String CHROME_DRIVER_PATH = ProtoTest.class.getClassLoader().getResource("chromedriver.exe").getFile();
    private static final String PROPERTY_WEB_DRIVER = "webdriver.chrome.driver";
    private static WebDriver webDriver;

    public Browser() {
        runDriver();
    }

    /*Метод возвращающий текущий инстанс WebDriver*/
    public static WebDriver getWebDriver() {
        return webDriver;
    }

    /*Создание инстанса ChromeDriver и его настройка*/
    private void runDriver() {
        System.setProperty(PROPERTY_WEB_DRIVER, CHROME_DRIVER_PATH); //Установка системных проперти с путём к драйверу в ресурсах проекта
        webDriver = new ChromeDriver();
        setupDriver();
    }

    /*Настройки WebDriver*/
    private void setupDriver() {
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /*Декоратор для webDriver.get*/
    public void openUrl(String url) {
        webDriver.get(url);
    }

    /*Отчистка кук для изолированного прохождения следующего теста в том же инстансе браузера*/
    public void clearCookies() {
        webDriver.manage().deleteAllCookies();
    }

    /*Декоратор для webDriver.quit*/
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
