
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.io.FileHandler.copy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class sweetForCatTest {
    private WebDriver webDriver;

    //constants
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //формат метки времени для результатов
    private static final String baseUrl = "https://yandex.ru/";
    private static final int duration = 10; //время ожидания
    private static final int timeout = 5;   //время на таймауты
    public int priceFromValue = 50;
    public int priceToValue = 150;
    public String deliveryMethod = "Доставка курьером";

    //locators
    private static final String closeAd = "//div[@class=\"modal__close\"]";
    private static final String market = "//div[text()='Маркет']";
    private static final String catalogue = "//button[@aria-label='Каталог']";
    private static final String catalogueResult = "//ul[@class='_2OFlF']";
    private static final String zootovari = "//span[text()='Зоотовары']/ancestor::a";
    private static final String zootovariLabel = "//a[text()='Зоотовары']";
    private static final String sweetForCat = "//a[text()='Для кошек']/ancestor::div[1]/ancestor::div[1]//a[text()='Лакомства']";
    private static final String sweetForCatLabel = "//h1[text()='Лакомства для кошек']";
    private static final String priceFromField = "//input[@id='glpricefrom']";
    private final String priceFromFieldAndValue = "//input[@id='glpricefrom' and @value='"+priceFromValue+"']";
    private static final String priceToField = "//input[@id='glpriceto']";
    private final String priceToFieldAndValue = "//input[@id='glpriceto' and @value='"+priceToValue+"']";
    private final String priceSetTab = "//legend[text()='Цена, ₽']/ancestor::fieldset";


    @BeforeEach
    public void setUp() throws TimeoutException {
        System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        webDriver = new FirefoxDriver();
        System.out.println(" Driver was started");
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        try{
            new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(closeAd)));
            webDriver.findElement(By.xpath(closeAd)).click();
        }
        catch(TimeoutException e){System.out.println("Рекламное окно в этот раз не появилось");}
    }


    @Test
    @Order(1)
    @DisplayName("Check if market tab exists")
    @Tag("SweetForCat")
    public void checkMarketExists() throws InterruptedException{
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(market)));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Checked if market exists successfully");
    }

    @Test
    @Order(2)
    @DisplayName("Switch to market tab")
    @Tag("SweetForCat")
    public void followMarketLink() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(market));
        webElement.click();
        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Followed market link successfully");
        TimeUnit.SECONDS.sleep(timeout);
    }

    @Test
    @Order(3)
    @DisplayName("Open catalogue")
    @Tag("SweetForCat")
    public void openCatalogue() throws InterruptedException{
        followMarketLink();
        WebElement webElement = webDriver.findElement(By.xpath(catalogue));
        webElement.click();
        //ждём появление таба с разделами каталога
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(catalogueResult)));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Opened catalogue successfully");
    }

    @Test
    @Order(4)
    @DisplayName("Find and move cursor to zootovari")
    @Tag("SweetForCat")
    public void moveCursorToZoo() throws InterruptedException{
        openCatalogue();
        WebElement webElement = webDriver.findElement(By.xpath(zootovari));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).build().perform();

        //проверяем, что появился раздел зоотоваров (после наведения курсора)
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(zootovariLabel)));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Moved cursor to zootovari successfully");
    }

    @Test
    @Order(5)
    @DisplayName("Click on sweets for cat")
    @Tag("SweetForCat")
    public void followSweetsForCat() throws InterruptedException{
        moveCursorToZoo();
        WebElement webElement = webDriver.findElement(By.xpath(sweetForCat));
        webElement.click();

        //проверяем, что перешли в нужный раздел каталога
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(sweetForCatLabel)));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Followed link sweets for cat successfully");
    }

    @Test
    @Order(6)
    @DisplayName("Setting up filters")
    @Tag("SweetForCat")
    public void setUpFilters() throws InterruptedException, IOException {
        followSweetsForCat();

        //проверяем, что поля цен присутствуют на форме
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(priceFromField)));
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(priceToField)));

        //проверяем, что поля цен заданы в соответствии с нашими настройками
        webDriver.findElement(By.xpath(priceFromField)).sendKeys(String.valueOf(priceFromValue));
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(priceFromFieldAndValue)));
        webDriver.findElement(By.xpath(priceToField)).sendKeys(String.valueOf(priceToValue));
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(priceToFieldAndValue)));

        File ScrPriceSet = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        //Getting cropped weather image
        //reading image
        BufferedImage fullImg = ImageIO.read(ScrPriceSet);

        // Get the location of element on the page
        WebElement webElement = webDriver.findElement(By.xpath(priceSetTab));
        Point point = webElement.getLocation();

        // Get width and height of the element
        int eleWidth = webElement.getSize().getWidth();
        int eleHeight = webElement.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", ScrPriceSet);
        File screenshotLocation = new File("src\\test\\resources\\screenshots\\priceset.png");
        copy(ScrPriceSet, screenshotLocation);


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Set up filters successfully");
    }



    @AfterEach
    public void tearDown(){
        System.out.println("Driver was shutdown successfully");
        webDriver.quit();
    }



}
