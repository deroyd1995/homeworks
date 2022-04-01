package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YandexMarketPage extends YandexMainPage{
    public YandexMarketPage(WebDriver webDriver) {
        super(webDriver);
    }

    //LOCATORS
    private static final String CATALOGUE = "//button[@aria-label='Каталог']";
    private static final String CATALOGUE_RESULT = "//ul[@class='_2OFlF']";
    private static final String ZOOTOVARI = "//span[text()='Зоотовары']/ancestor::a";
    private static final String ZOOTOVARI_LABEL = "//a[text()='Зоотовары']";
    private static final String SWEET_FOR_CAT = "//a[text()='Для кошек']/ancestor::div[1]/ancestor::div[1]//a[text()='Лакомства']";
    private static final String SWEET_FOR_CAT_LABEL = "//div[@data-grabber='SearchTitle']//h1[text()='Лакомства для кошек']";
    private static final String PRICE_FROM_FIELD = "//input[@id='glpricefrom']";
    private static final String PRICE_TO_FIELD = "//input[@id='glpriceto']";
    public final String DELIVERY_METHOD_TAB = "//legend[text()='Способ доставки']/ancestor::fieldset";
    public final String PRICE_SET_TAB = "//legend[text()='Цена, ₽']/ancestor::fieldset";
    private final String SHOW_ALL_MANUFACTURERS = "//legend[text()='Производитель']/ancestor::fieldset[1]//button[text()='Показать всё']";
    private final String SEARCH_MANUF_FIELD = "//input[@name='Поле поиска']";
    public final String MANUFACTURERS_TAB = "//legend[text()='Производитель']/ancestor::div[@data-zone-name='search-filter']";
    private final String PRODUCT_IMAGE = "//a[@data-node-name='picture']";
    private final String PRODUCT_COMPARE = "//div[@data-node-name='comparison']//div";
    private final String PRODUCT_COMPARE_LABEL = "//div[@data-node-name='comparison']//span";
    public final String PRODUCT_FINAL_VIEW = "//div[@itemtype='https://schema.org/Product']";
    private final String TO_COMPARE_LIST = "//a[@href='/my/compare-lists']";
    public final String PRODUCTS_COMPARISON = "//div[@data-apiary-widget-id='/content/compareContent']";


    //МЕТОД ДЛЯ ПЕРЕХОДА В КАТАЛОГ
    public void openCatalogue() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(CATALOGUE));
        webElement.click();
        //ждём появление таба с разделами каталога
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(CATALOGUE_RESULT)));

        getTimeStamp("Opened catalogue successfully");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ПЕРЕМЕЩЕНИЯ КУРСОРА НА ЗООТОВАРЫ
    public void moveCursorToZooSup() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(ZOOTOVARI));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).build().perform();

        //проверяем, что появился раздел зоотоваров (после наведения курсора)
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ZOOTOVARI_LABEL)));

        getTimeStamp("Moved cursor to zootovari successfully");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ПЕРЕХОДА В РАЗДЕЛ ЛАКОМСТВ ДЛЯ КОШЕК
    public void clickOnSweetsForCat() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.xpath(SWEET_FOR_CAT));
        webElement.click();

        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }

        //проверяем, что перешли в нужный раздел каталога
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SWEET_FOR_CAT_LABEL)));

        getTimeStamp("Followed link sweets for cat successfully");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ФИЛЬТРАЦИИ ПО ЦЕНЕ
    public void filterPrice(int priceFrom,int priceTo) throws InterruptedException {
        String PRICE_FROM_FIELDANDVALUE = "//input[@id='glpricefrom' and @value='"+priceFrom+"']";
        String PRICE_TO_FIELDANDVALUE = "//input[@id='glpriceto' and @value='"+priceTo+"']";

        //проверяем, что поля цен присутствуют на форме
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_FROM_FIELD)));
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_TO_FIELD)));

        //проверяем, что поля цен заданы в соответствии с нашими настройками
        webDriver.findElement(By.xpath(PRICE_FROM_FIELD)).sendKeys(String.valueOf(priceFrom));
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_FROM_FIELDANDVALUE)));
        webDriver.findElement(By.xpath(PRICE_TO_FIELD)).sendKeys(String.valueOf(priceTo));
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_TO_FIELDANDVALUE)));

        getTimeStamp("set up price");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ФИЛЬТРАЦИИ ПО СПОСОБУ ДОСТАВКИ
    public void filterDeliveryMethod(String deliveryMethod) throws InterruptedException {
        String deliveryMethodRadioB = "//legend[text()='Способ доставки']/ancestor::fieldset[1]//span[text()='"+deliveryMethod+"']/ancestor::label[1]//input";
        String deliveryMethodLabel = "//legend[text()='Способ доставки']/ancestor::fieldset[1]//span[text()='"+deliveryMethod+"']";

        //проверяем, что задан требуемый метод доставки (самовывоз/курьер/любой)
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(deliveryMethodLabel)));
        webDriver.findElement(By.xpath(deliveryMethodLabel)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeSelected(By.xpath(deliveryMethodRadioB)));

        getTimeStamp("set up deivery method");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ФИЛЬТРАЦИИ ПО ПРОИЗВОДИТЕЛЮ
    public void filterByManufacturer(String manufacturer) throws InterruptedException {
        //локаторы для линк-лейбла и для радио-кнопки (проверка статуса)
        String manufacturerLabel = "//input[@name='Производитель "+manufacturer+"']/ancestor::label//span";
        String manufacturerRadioB = "//input[@name='Производитель "+manufacturer+"']/ancestor::label//input";

        //раскрываем список производителей
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SHOW_ALL_MANUFACTURERS)));
        webDriver.findElement(By.xpath(SHOW_ALL_MANUFACTURERS)).click();

        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_MANUF_FIELD)));
        webDriver.findElement(By.xpath(SEARCH_MANUF_FIELD)).sendKeys(String.valueOf(manufacturer));

        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(manufacturerLabel)));

       checkIfAvailable(manufacturer);

       webDriver.findElement(By.xpath(manufacturerLabel)).click();

        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeSelected(By.xpath(manufacturerRadioB)));
        getTimeStamp("set up manufacturer");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ПЕРЕХОДА ПО ТОВАРУ С ЗАДАННЫМ НОМЕРОМ В СПИСКЕ РЕЗУЛЬТАТА
    public void clickOnItem(int order) throws InterruptedException {
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("("+PRODUCT_IMAGE+")["+order+"]")));
        webDriver.findElement(By.xpath("("+PRODUCT_IMAGE+")["+order+"]")).click();
        getTimeStamp("clicked on item with order = "+order);

        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ДОБАВЛЕНИЯ ТОВАРА К СРАВНЕНИЮ
    public void addItemToComparison() throws InterruptedException {
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PRODUCT_COMPARE)));
        webDriver.findElement(By.xpath(PRODUCT_COMPARE)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.textToBe(By.xpath(PRODUCT_COMPARE_LABEL),"В сравнении"));
        getTimeStamp("item added to comparison");

        TimeUnit.SECONDS.sleep(1);
    }

    //МЕТОД ДЛЯ ПРОВЕРКИ ПО ЛЕЙБЛУ ПРОИЗВОДИТЕЛЯ, ЧТО ТАКИЕ ТОВАРЫ ПРИСУТСТВУЮТ
    public void checkIfAvailable(String manufacturer){
        //локаторы для линк-лейбла и для радио-кнопки (проверка статуса)
        String manufacturerLabel = "//input[@name='Производитель "+manufacturer+"']/ancestor::label//span";

        WebElement webElement = webDriver.findElement(By.xpath(manufacturerLabel));
        Assertions.assertFalse(webElement.getCssValue("cursor").equalsIgnoreCase("default"),"No products for such filters for "+manufacturer);
    }

    //МЕТОД ДЛЯ ПЕРЕХОДА В СПИСОК СРАВНЕНИЯ
    public void goToCompareList(){
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(TO_COMPARE_LIST)));
        webDriver.findElement(By.xpath(TO_COMPARE_LIST)).click();
    }


}
