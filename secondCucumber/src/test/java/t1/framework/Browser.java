package t1.framework;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Browser {
    public static final Logger logger = LoggerFactory.getLogger(Browser.class);
    private static Browser browser;
    private static WebDriver webDriver;
    private BrowserFactory browserFactory = new BrowserFactory();

    public Browser () {
        webDriver = browserFactory.getWebDriver("chrome");
    }

    public static Browser getInstance() {
        if (browser == null) {
            browser = new Browser();
            logger.info("webDriver был инициирован");
        }
        return browser;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public static void open(String url) {
        webDriver.get(url);
    }

    public static void quit() {
        getInstance().getWebDriver().quit();
        browser = null;
    }
}
