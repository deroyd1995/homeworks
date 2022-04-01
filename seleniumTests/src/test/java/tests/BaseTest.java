package tests;

import locators.Locators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest extends Locators {
    protected WebDriver webDriver;

    @BeforeEach
    public void setUp() throws TimeoutException {
        System.setProperty("webdriver.gecko.driver",DRIVER_SOURCE);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        webDriver = new FirefoxDriver();
        System.out.println("Driver was started");
        webDriver.get(BASE_URL);
        webDriver.manage().window().maximize();
        try{
            new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSEAD)));
            webDriver.findElement(By.xpath(CLOSEAD)).click();
        }
        catch(TimeoutException e){e.printStackTrace();};
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Driver was shutdown successfuly");
        webDriver.quit();
    }
}
