package t1.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public WebDriver getWebDriver(String browserName){
        switch(browserName){
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
        return new FirefoxDriver();
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        return new ChromeDriver();
    }

}
