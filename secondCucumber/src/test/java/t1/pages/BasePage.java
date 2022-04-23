package t1.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import t1.framework.Browser;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public static final Logger logger = LoggerFactory.getLogger(YandexMainPage.class);
    protected WebDriver webDriver;
    public BasePage() {
        this.webDriver = Browser.getInstance().getWebDriver();
    }
}
