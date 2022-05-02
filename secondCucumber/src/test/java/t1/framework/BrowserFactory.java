package t1.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BrowserFactory {
    public static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    public WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                return getChromeDriver();
            case "firefox":
                return getFireFoxDriver();
            default:
                throw new IllegalArgumentException();
        }
    }

    private WebDriver getFireFoxDriver(){
        System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
        logger.debug("Возвращаем geckodriver");
        return new FirefoxDriver();
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        logger.debug("Возвращаем chromedriver");
        return new ChromeDriver();
    }
}
