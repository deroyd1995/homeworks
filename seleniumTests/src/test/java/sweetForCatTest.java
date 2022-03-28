
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.io.FileHandler.copy;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class sweetForCatTest {
    private WebDriver webDriver;

    //constants
    private static final String driverType = "webdriver.gecko.driver";
    private static final String driverPath = "src\\test\\resources\\geckodriver.exe";
    private static final String baseUrl = "https://yandex.ru/";
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //формат метки времени для результатов
    private static final int duration = 15; //время ожидания, сек
    private static final int timeout = 5;   //время на таймауты, сек
    public int priceFromValue = 50; // цена от,₽
    public int priceToValue = 150;  // цена до,₽
    public static final String screenShotsPath = "src\\test\\resources\\";
    public String deliveryMethod = "Доставка курьером"; // варианты: "Самовывоз", "Доставка курьером", "Любой"
    enum manufacturers {Whiskas,Sheba,Titbit,AlpenHof;}

    //locators
    private static final String closeAd = "//div[@class=\"modal__close\"]";
    private static final String market = "//div[text()='Маркет']";

    private static final String catalogue = "//button[@aria-label='Каталог']";
    private static final String catalogueResult = "//ul[@class='_2OFlF']";

    private static final String zootovari = "//span[text()='Зоотовары']/ancestor::a";
    private static final String zootovariLabel = "//a[text()='Зоотовары']";

    private static final String sweetForCat = "//a[text()='Для кошек']/ancestor::div[1]/ancestor::div[1]//a[text()='Лакомства']";
    private static final String sweetForCatLabel = "//div[@data-grabber='SearchTitle']//h1[text()='Лакомства для кошек']";

    private static final String priceFromField = "//input[@id='glpricefrom']";
    private final String priceFromFieldAndValue = "//input[@id='glpricefrom' and @value='"+priceFromValue+"']";
    private static final String priceToField = "//input[@id='glpriceto']";
    private final String priceToFieldAndValue = "//input[@id='glpriceto' and @value='"+priceToValue+"']";
    private final String priceSetTab = "//legend[text()='Цена, ₽']/ancestor::fieldset";

    private final String deliveryMethodRadioB = "//legend[text()='Способ доставки']/ancestor::fieldset[1]//span[text()='"+deliveryMethod+"']/ancestor::label[1]//input";
    private final String deliveryMethodLabel = "//legend[text()='Способ доставки']/ancestor::fieldset[1]//span[text()='"+deliveryMethod+"']";
    private final String deliveryMethodTab = "//legend[text()='Способ доставки']/ancestor::fieldset";

    private final String showAllManufacturers = "//legend[text()='Производитель']/ancestor::fieldset[1]//button[text()='Показать всё']";
    private final String searchManufacturerField = "//input[@name='Поле поиска']";
    private final String manufacturerTab = "//legend[text()='Производитель']/ancestor::div[@data-zone-name='search-filter']";

    private final String productImage = "//a[@data-node-name='picture']";
    private final String productCompare = "//div[@data-node-name='comparison']//div";
    private final String productCompareLabel = "//div[@data-node-name='comparison']//span";

    @BeforeEach
    public void setUp() throws TimeoutException {
        System.setProperty(driverType,driverPath);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        webDriver = new FirefoxDriver();
        System.out.println("Driver was started");
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        try{
            new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(closeAd)));
            webDriver.findElement(By.xpath(closeAd)).click();
        }
        catch(TimeoutException e){System.out.println("Рекламное окно в этот раз не появилось");}
    }

    //метод для создания скриншота по xpath элемента, сохраняет с именем name и по пути screenShotsPath
    public void takeScreenShotPNG(String xpath, String name) throws IOException{
        WebElement logo = webDriver.findElement(By.xpath(xpath));

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(webDriver, logo);

        ImageIO.write(screenshot.getImage(), "png", new File(screenShotsPath+name));
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

        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }

        //проверяем, что перешли в нужный раздел каталога
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(sweetForCatLabel)));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Followed link sweets for cat successfully");
    }

    @ParameterizedTest
    @EnumSource(manufacturers.class)
    @Order(6)
    @DisplayName("Setting up filters")
    @Tag("SweetForCat")
    public void setUpFilters(manufacturers manufacturer) throws InterruptedException, IOException {
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

        takeScreenShotPNG(priceSetTab,"priceset.png");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+"   set up price");

        //проверяем, что задан требуемый метод доставки (самовывоз/курьер/любой)
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(deliveryMethodLabel)));
        webDriver.findElement(By.xpath(deliveryMethodLabel)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeSelected(By.xpath(deliveryMethodRadioB)));

        takeScreenShotPNG(deliveryMethodTab,"deliverymethod.png");
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+"   set up delivery method");

        //раскрываем список производителей
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(showAllManufacturers)));
        webDriver.findElement(By.xpath(showAllManufacturers)).click();

        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(searchManufacturerField)));
        webDriver.findElement(By.xpath(searchManufacturerField)).sendKeys(String.valueOf(manufacturer));

        //локаторы для линк-лейбла и для радио-кнопки (проверка статуса)
        String manufacturerLabel = "//input[@name='Производитель "+manufacturer+"']/ancestor::label//span";
        String manufacturerRadioB = "//input[@name='Производитель "+manufacturer+"']/ancestor::label//input";

        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(manufacturerLabel)));

        //проверка на то, что курсор не остался "по умолчанию" - стрелочкой (если стрелочка - нет предложений по фильтрам)
        takeScreenShotPNG(manufacturerTab,"soloview_"+manufacturer+".png");
        WebElement webElement = webDriver.findElement(By.xpath(manufacturerLabel));
        Assertions.assertFalse(webElement.getCssValue("cursor").equalsIgnoreCase("default"),"No products for such filters by "+manufacturer);
        webDriver.findElement(By.xpath(manufacturerLabel)).click();

        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeSelected(By.xpath(manufacturerRadioB)));

        takeScreenShotPNG(manufacturerTab,"selected_"+manufacturer+".png");
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+"   set up manufacturer");

        //переход по первому товару и включение товара в список для сравнения
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(productImage)));
        webDriver.findElement(By.xpath(productImage)).click();

        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }

        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(productCompare)));
        webDriver.findElement(By.xpath(productCompare)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.textToBe(By.xpath(productCompareLabel),"В сравнении"));

        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        copy(scrFile , new File(screenShotsPath+manufacturer+"_result.png"));
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Product was added to comparison successfully");
   }



    @AfterEach
    public void tearDown(){
        webDriver.quit();
        System.out.println("Driver was shutdown successfully");
    }



}
