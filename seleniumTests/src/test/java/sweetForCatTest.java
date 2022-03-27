
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.openqa.selenium.io.FileHandler.copy;

public class sweetForCatTest {
    private WebDriver webDriver;

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private static final String baseUrl = "https://yandex.ru/";
    //locators
    private static final String closeAd = "//div[@class=\"modal__close\"]";
    //private static final String market = "//div[@class='services-new__icon services-new__icon_market']";
    private static final String market = "//div[text()='Маркет']";



    @BeforeEach
    public void setUp() throws TimeoutException {
        System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        webDriver = new FirefoxDriver();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Driver was started");
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        try{
            WebElement closeAdButton = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(closeAd)));
            webDriver.findElement(By.xpath(closeAd)).click();
        }
        catch(TimeoutException e){e.printStackTrace();};
    }

    @Test
    @DisplayName("Follow market link")
    @Tag("SweetForCat")
    public void followMarketLink() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(market));
        webElement.click();
        System.out.println("Market opened successfully");
    }



    @AfterEach
    public void tearDown(){
        System.out.println("Driver was shutdown successfuly");
        webDriver.quit();
    }



}
