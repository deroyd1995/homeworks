import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TimeoutException;

import static org.openqa.selenium.io.FileHandler.copy;

public class SeleniumStartExampleTest {
    private WebDriver webDriver;

    private static final String baseUrl = "https://yandex.ru/";

    //локаторы
    private static final String inputField = "//input[@aria-label='Запрос']";
    private static final String YANDEX_LOGO = "//div[@aria-label='Яндекс']";
    private static final String find_button = "//span[text()='Найти']/parent::button";
    private static final String resultList = "//ul[@aria-label='Результаты поиска']";
    private static final String WEATHER = "//div[@class='weather__header']//a[text()='Погода']";
    private static final String currentTemperature = "//div[@class='weather__temp']";
    private static final String tomorrowTemperature = "//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]//span[text()='днём']/following-sibling::span[contains(@class,'temp__value')]";
    private static final String tomorrowTemperatureCropp = "//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]";
    private static final String closeAd = "//div[@class=\"modal__close\"]";

    @BeforeEach
    public void setUp() throws TimeoutException {
        System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        webDriver = new FirefoxDriver();
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
    @Tag("Other")
    public void freeTest(){
        System.out.println("success");
    }


    @Test
    @DisplayName("Find the best motivators")
    @Tag("Weather")
    public void findBestMotivatorTest() throws InterruptedException{
        Assertions.assertTrue(webDriver.findElement(By.xpath(YANDEX_LOGO)).isDisplayed());
        webDriver.findElement(By.xpath(inputField)).sendKeys("лучшие мотиваторы");
        webDriver.findElement(By.xpath(find_button)).click();
        WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath(resultList)));
        System.out.println("Motivators found successfully");
    }

    @Test
    @DisplayName("Get today temperature")
    @Tag("Weather")
    public void findCurrentTemperatureTest(){
        String todayTemperature;
        todayTemperature = webDriver.findElement(By.xpath(currentTemperature)).getText();
        System.out.println("Today is "+todayTemperature);
    }

    @Test
    @DisplayName("Get tomorrow temperature")
    @Tag("Weather")
    public void findTomorrowTemperatureTest() throws InterruptedException, IOException{
        String TomorrowTemperature;
        WebElement webElement = webDriver.findElement(By.xpath(WEATHER));
        webElement.click();
        TimeUnit.SECONDS.sleep(5);

        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }
        TomorrowTemperature = webDriver.findElement(By.xpath(tomorrowTemperature)).getText();
        System.out.println("Tomorrow is "+TomorrowTemperature);
        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        copy(scrFile , new File("src\\test\\resources\\screenshot.png"));


        //Getting cropped weather image
        //reading image
        BufferedImage  fullImg = ImageIO.read(scrFile);

        // Get the location of element on the page
        webElement = webDriver.findElement(By.xpath(tomorrowTemperatureCropp));
        Point point = webElement.getLocation();

        // Get width and height of the element
        int eleWidth = webElement.getSize().getWidth();
        int eleHeight = webElement.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", scrFile);
        File screenshotLocation = new File("src\\test\\resources\\screenshot_cropped.png");
        copy(scrFile, screenshotLocation);

    }

    @AfterEach
    public void tearDown(){
        System.out.println("Driver was shutdown successfuly");
        webDriver.quit();
    }
}