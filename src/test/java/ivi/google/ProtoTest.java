package ivi.google;

import ivi.google.utils.Browser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class ProtoTest {
    private static final String CHROME_DRIVER_PATH = ProtoTest.class.getClassLoader().getResource("chromedriver.exe").getFile();
    private static final String PROPERTY_WEB_DRIVER = "webdriver.chrome.driver";
    private static final String GOOGLE_URL = "https://www.google.com/";
    private Browser browser;

    @BeforeAll
    private void beforeClass() {
        browser = new Browser();
    }

    @AfterAll
    private void afterClass() {
        browser.closeDriver();
    }

    protected void openGoogle() {
        browser.openUrl(GOOGLE_URL);
    }

    /*Отчистка кук для изолированного прохождения следующего теста в том же инстансе браузера*/
    protected void clearCookies() {
        browser.clearCookies();
    }

}
